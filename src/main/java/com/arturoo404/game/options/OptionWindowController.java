package com.arturoo404.game.options;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionWindowController implements Initializable {
    @FXML
    private Button saveButton, closeButton;
    @FXML
    private OptionsSelector optionsSelector;
    @FXML
    private ChoiceBox resolutionSelector, difficultySelector;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            optionsSelector = new OptionsSelector(
                    saveButton, closeButton, resolutionSelector, difficultySelector);
            optionsSelector.init();
        } catch (IOException ignored) {}
    }

    public void setStageOptionsSelector(Stage stage) {
        optionsSelector.setStage(stage);
    }
}