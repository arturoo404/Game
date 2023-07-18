package com.arturoo404.game.options;

import com.arturoo404.game.file.EntityMainModel;
import com.arturoo404.game.file.FileReader;
import com.arturoo404.game.generate.difficulty.Difficulty;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OptionsSelector {

    private Difficulty difficulty;
    private final EntityMainModel entityMainModel;
    private final Button saveButton, easyButton, mediumButton, hardButton, nightmareButton;
    private Stage stage;

    public OptionsSelector(Button saveButton, Button easyButton, Button mediumButton, Button hardButton, Button nightmareButton) throws IOException {
        this.saveButton = saveButton;
        this.easyButton = easyButton;
        this.mediumButton = mediumButton;
        this.hardButton = hardButton;
        this.nightmareButton = nightmareButton;
        entityMainModel = FileReader.entityModelFilesRead();
    }

    public void init(){
        difficulty = entityMainModel.difficulty();
        lvlButtonEvent();
        saveButtonEvent();
    }

    private void lvlButtonEvent(){
        easyButton.setOnAction(a -> difficulty = Difficulty.EASY);
        mediumButton.setOnAction(a -> difficulty = Difficulty.NORMAL);
        hardButton.setOnAction(a -> difficulty = Difficulty.HARD);
        nightmareButton.setOnAction(a -> difficulty = Difficulty.NIGHTMARE);
    }

    private void saveButtonEvent(){
        saveButton.setOnAction(actionEvent -> {
            try {
                EntityMainModel updatedEntityMainModel = new EntityMainModel(entityMainModel.entityModel(), difficulty);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(new File("src/main/resources/entity/entity_location.json"), updatedEntityMainModel);
            }catch (Exception ignored){}
            stage.close();
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
