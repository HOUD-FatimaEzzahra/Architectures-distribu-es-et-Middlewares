package com.clientfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    private Client client;
    private ClientController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static void main(String[] args) {
        launch(args);
    }
}