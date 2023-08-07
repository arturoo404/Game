package com.arturoo404.game.options;

import com.arturoo404.game.file.EntityMainModel;
import com.arturoo404.game.file.FileReader;
import com.arturoo404.game.file.GameOptions;
import com.arturoo404.game.generate.difficulty.Difficulty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OptionsSelector {

    private Difficulty difficulty;
    private final EntityMainModel entityMainModel;
    private final Button saveButton, easyButton, mediumButton, hardButton, nightmareButton, closeButton;
    private Stage stage;
    private ChoiceBox<String> resolutionSelector;
    private GameOptions gameOptions;

    public OptionsSelector(Button saveButton, Button easyButton, Button mediumButton, Button hardButton, Button nightmareButton, Button closeButton, ChoiceBox<String> resolutionSelector) throws IOException {
        this.saveButton = saveButton;
        this.easyButton = easyButton;
        this.mediumButton = mediumButton;
        this.hardButton = hardButton;
        this.nightmareButton = nightmareButton;
        this.closeButton = closeButton;
        this.resolutionSelector = resolutionSelector;
        entityMainModel = FileReader.entityModelFilesRead();
        gameOptions = new GameOptions();
    }

    public void init(){
        difficulty = entityMainModel.difficulty();
        lvlButtonEvent();
        saveButtonEvent();
        closeButtonEvent();
        resolutionSelect();
    }

    private void lvlButtonEvent(){
        easyButton.setOnAction(a -> difficulty = Difficulty.EASY);
        mediumButton.setOnAction(a -> difficulty = Difficulty.NORMAL);
        hardButton.setOnAction(a -> difficulty = Difficulty.HARD);
        nightmareButton.setOnAction(a -> difficulty = Difficulty.NIGHTMARE);
    }

    private void resolutionSelect(){
        resolutionSelector.getItems().addAll("720p", "1080p", "1440p");
        resolutionSelector.setValue("1080p");
        // Trzeba zrobic szczytywanie z jsona, nie dzialalo getResolutionV to popierdolilem narazie i domyslnie jest 1080px
    }

    private void saveButtonEvent(){
        saveButton.setOnAction(actionEvent -> {
            String selectedResolution = resolutionSelector.getValue();
            switch (selectedResolution){
                case "720p":
                    gameOptions.setResolutionV("720");
                    gameOptions.setResolutionH("1280");
                    break;
                case  "1080p":
                    gameOptions.setResolutionV("1080");
                    gameOptions.setResolutionH("1920");
                    break;
                case  "1440p":
                    gameOptions.setResolutionV("1440");
                    gameOptions.setResolutionH("2560");
                    break;
            }
            try {
                EntityMainModel updatedEntityMainModel = new EntityMainModel(entityMainModel.entityModel(), difficulty);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.writeValue(new File("src/main/resources/entity/entity_location.json"), updatedEntityMainModel);

                objectMapper.writeValue(new File("src/main/resources/com/arturoo404/game/game_options.json"), gameOptions);
                resolutionSelector.setValue(gameOptions.getResolutionV()+"p");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
            System.out.println(difficulty);
        });
    }

    private void closeButtonEvent(){
        closeButton.setOnAction(actionEvent -> {
            stage.close();
            System.out.println(difficulty);
        });
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

