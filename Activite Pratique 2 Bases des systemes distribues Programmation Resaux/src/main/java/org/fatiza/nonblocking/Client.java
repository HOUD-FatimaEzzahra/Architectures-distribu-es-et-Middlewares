package org.fatiza.nonblocking;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        // Connexion au serveur
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 2002));

        // Création du scanner pour la lecture de l'entrée utilisateur
        Scanner scanner = new Scanner(System.in);

        // Boucle principale
        while (true) {
            // Lecture de l'entrée utilisateur
            String request = scanner.nextLine();

            // Envoi des données au serveur
            ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes());
            socketChannel.write(byteBuffer);

            // Lecture de la réponse du serveur
            ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(responseBuffer);

            // Affichage de la réponse si elle est non vide
            if (bytesRead > 0) {
                String response = new String(responseBuffer.array(), 0, bytesRead).trim();
                System.out.println("Response => " + response);
            }
        }
    }
}