package com.arturoo404.game;

import com.arturoo404.game.options.OptionWindowController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartWindowController implements Initializable {

    @FXML
    private Button start, options;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initButtons();
    }

    private void initButtons(){
        start.setOnAction(actionEvent -> {
            try {
                openGameWindow(actionEvent);
            } catch (IOException | InterruptedException ignored){}
        });

        options.setOnAction(actionEvent -> {
            try {
                openOptions(actionEvent);
            } catch (IOException ignored) {}
        });
    }
    private void openGameWindow(ActionEvent actionEvent) throws IOException, InterruptedException {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1500), ((Node) actionEvent.getSource()).getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(actionEvent1 -> {
            try {
                Parent  root = FXMLLoader.load(getClass().getResource("game-window.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1920, 1080);
                stage.setMinWidth(1920);
                stage.setMaxWidth(1920);
                stage.setMinHeight(1080);
                stage.setMaxHeight(1080);
                stage.setScene(scene);
                root.requestFocus();
                FadeTransition fadein = new FadeTransition(Duration.seconds(2), scene.getRoot());
                fadein.setFromValue(0.0);
                fadein.setToValue(1.0);
                fadein.play();
            } catch (IOException ignored) {}
        });
        fadeOut.play();
    }

    private void openOptions(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("option-window.fxml"));
        Parent root = loader.load();
        OptionWindowController controller = loader.getController();
        Stage optionsStage = new Stage();
        controller.setStageOptionsSelector(optionsStage);
        optionsStage.initModality(Modality.WINDOW_MODAL);
        optionsStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root, 500, 400);
        optionsStage.initStyle(StageStyle.UNDECORATED);
        optionsStage.setScene(scene);
        optionsStage.show();
    }
}