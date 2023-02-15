package com.clientfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {
    @FXML private TextField messageField;
    @FXML private Button sendButton;
    @FXML private Button connectButton;
    @FXML private Button disconnectButton;
    @FXML private TextArea chatArea;

    private Client client;

    @FXML
    public void initialize() {
        client = new Client();
        sendButton.setOnAction(event -> sendMessage());
        connectButton.setOnAction(event -> connect());
        disconnectButton.setOnAction(event -> disconnect());
    }

    private void sendMessage() {
        String message = messageField.getText();
        client.send(message);
        messageField.clear();
    }

    private void connect() {
        try {
            client.connect("localhost", 2001); // replace with your server's host and port
            chatArea.appendText("Connected to server\n");
        } catch (IOException e) {
            chatArea.appendText("Failed to connect to server\n");
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            client.disconnect();
            chatArea.appendText("Disconnected from server\n");
        } catch (IOException e) {
            chatArea.appendText("Failed to disconnect from server\n");
            e.printStackTrace();
        }
    }
}
