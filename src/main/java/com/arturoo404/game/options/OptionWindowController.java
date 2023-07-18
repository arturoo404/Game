package com.arturoo404.game.options;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionWindowController implements Initializable {
    @FXML
    private Button saveButton, easyButton, mediumButton, hardButton, nightmareButton;
    private OptionsSelector optionsSelector;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            optionsSelector = new OptionsSelector(
                    saveButton, easyButton, mediumButton, hardButton, nightmareButton);
            optionsSelector.init();
        } catch (IOException ignored) {}
    }

    public void setStageOptionsSelector(Stage stage) {
        optionsSelector.setStage(stage);
    }
}