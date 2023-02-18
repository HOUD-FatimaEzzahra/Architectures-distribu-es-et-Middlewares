package org.fatiza.blocking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiThreadBlockingChatServer extends Thread {
    private List<Conversation> conversations = new ArrayList<>();
    int clientsCount = 0;

    public static void main(String[] args) {
        new MultiThreadBlockingChatServer().start();
    }

    public void start() {
        System.out.println("The server is started using port 2001");
        try (ServerSocket serverSocket = new ServerSocket(2001)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ++clientsCount;
                Conversation conversation = new Conversation(socket, clientsCount);
                conversations.add(conversation);
                conversation.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class Conversation extends Thread {
        private int clientId;
        private Socket socket;

        public Conversation(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
                System.out.println("New connection from Client n°" + clientId + " IP= " + socket.getRemoteSocketAddress());
                pw.println("\t Welcome you are the client n°:" + clientId);
                String request;
                String message = "";
                List<Integer> ids = new ArrayList<>();
                while ((request = br.readLine()) != null) {
                    if (request.contains("=>")) {
                        ids = new ArrayList<>();
                        String[] items = request.split("=>");
                        String clients = items[0];
                        message = items[1];
                        if (clients.contains(",")) {
                            ids = List.of(clients.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
                        } else {
                            ids.add(Integer.parseInt(clients));
                        }
                    } else {
                        message = request;
                        ids = conversations.stream().map(c -> c.clientId).collect(Collectors.toList());
                    }
                    System.out.println("\t New Request => " + request + " from " + socket.getRemoteSocketAddress());
                    broadcastMessage(message, socket, ids);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void broadcastMessage(String message, Socket from, List<Integer> clientIds) {
            for (Conversation conversation : conversations) {
                Socket socket = conversation.socket;
                if ((socket != from) && clientIds.contains(conversation.clientId)) {
                    try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {
                        printWriter.println(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}