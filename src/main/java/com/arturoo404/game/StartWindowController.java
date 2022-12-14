package com.arturoo404.game;

import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartWindowController {

    @FXML
    private void startGame(ActionEvent event) throws IOException, CsvException {
        Parent root = FXMLLoader.load(getClass().getResource("game-window.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
        stage.show();
        //Potrzebne do działania klawiatury
        root.requestFocus();
    }
}