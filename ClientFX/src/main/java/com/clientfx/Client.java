package com.clientfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        public void connect(String host, int port) throws IOException, IOException {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        public void disconnect() throws IOException {
            socket.close();
        }
        public void send(String message) {
            out.println(message);
        }

        public String receive() throws IOException {
            return in.readLine();
        }
}