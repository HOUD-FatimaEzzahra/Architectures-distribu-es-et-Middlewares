package org.fatiza;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int port = 2001;
        Socket socket = new Socket(serverAddress, port);
        InputStream is=socket.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader input = new BufferedReader(isr);
        OutputStream os=socket.getOutputStream();
        OutputStreamWriter osr=new OutputStreamWriter(os);
        PrintWriter output = new PrintWriter(osr, true);
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Connected!");
        while (true) {
            String message = consoleInput.readLine();
            output.println(message);
            if (message.equals("exit")) {
                break;
            }
            String response = input.readLine();
            System.out.println(response);
        }
        socket.close();
    }
}