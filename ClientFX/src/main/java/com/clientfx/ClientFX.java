package com.clientfx;

import com.clientfx.ClientController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
        Parent root = loader.load();
        ClientController controller = loader.getController();
        controller.initialize();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


}