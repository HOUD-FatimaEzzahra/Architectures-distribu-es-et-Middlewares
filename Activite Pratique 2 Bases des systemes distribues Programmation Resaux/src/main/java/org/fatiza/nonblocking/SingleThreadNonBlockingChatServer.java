package org.fatiza.nonblocking;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SingleThreadNonBlockingChatServer {

    public static void main(String[] args) throws Exception {
        // Initialisation du Selector et du ServerSocketChannel
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 2002));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // Boucle principale
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {
                    handleAccept(key, selector);
                } else if (key.isReadable()) {
                    handleRead(key);
                }

                keyIterator.remove();
            }
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws Exception {
        // Acceptation de la connexion et enregistrement du SocketChannel pour lecture
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("New Connection Accepted");
    }

    private static void handleRead(SelectionKey key) throws Exception {
        // Lecture des données envoyées par le client
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel.read(buffer);
        String request = new String(buffer.array()).trim();

        System.out.println("Received Message => " + request);

        // Si le client envoie "exit", fermer la connexion
        if (request.equals("exit")) {
            socketChannel.close();
            System.out.println("Connection Closed");
        }
    }
}
