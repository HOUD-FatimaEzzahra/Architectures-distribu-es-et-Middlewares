package org.fatiza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost"; // Adresse du serveur
        int port = 2001; // Port du serveur
        Socket socket = new Socket(serverAddress, port); // Création de la socket

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Flux de lecture
        PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // Flux d'écriture

        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in)); // Flux de lecture de la console

        System.out.println("Connected to server");

        while (true) {
            String message = consoleInput.readLine(); // Lecture du message depuis la console
            output.println(message); // Envoi du message au serveur

            if (message.equals("exit")) {
                break; // Si l'utilisateur a saisi "exit", on sort de la boucle
            }

            String response = input.readLine(); // Lecture de la réponse du serveur
            System.out.println(response); // Affichage de la réponse du serveur
        }

        socket.close(); // Fermeture de la socket
    }
}