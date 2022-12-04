package com.arturoo404.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartWindowController {

    private Stage stage;
    private Scene scene;

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game-window.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
}