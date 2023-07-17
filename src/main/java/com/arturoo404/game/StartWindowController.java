package com.arturoo404.game;

import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StartWindowController {

    private Stage optionsStage;

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
    @FXML
    private void openOptions(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("option-window.fxml"));
        Parent root = loader.load();
        OptionWindowController controller = loader.getController();

        Stage optionsStage = new Stage();
        optionsStage.initModality(Modality.WINDOW_MODAL);
        optionsStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root, 500, 400);
        optionsStage.initStyle(StageStyle.UNDECORATED);
        optionsStage.setScene(scene);
        optionsStage.show();

        controller.setOptionsStage(optionsStage);
        //Potrzebne do działania klawiatury
        root.requestFocus();
    }
}