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
    private final Button saveButton, closeButton;
    private Stage stage;
    private ChoiceBox<String> resolutionSelector, difficultySelector;
    private GameOptions gameOptions;

    public OptionsSelector(Button saveButton, Button closeButton, ChoiceBox<String> resolutionSelector, ChoiceBox<String> difficultySelector) throws IOException {
        this.saveButton = saveButton;
        this.closeButton = closeButton;
        this.resolutionSelector = resolutionSelector;
        this.difficultySelector = difficultySelector;
        entityMainModel = FileReader.entityModelFilesRead();
        gameOptions = new GameOptions();
    }

    public void init(){
        difficulty = entityMainModel.difficulty();
        saveButtonEvent();
        closeButtonEvent();
        resolutionSelect();
        difficultySelect();
    }

    private void difficultySelect(){
        difficultySelector.getItems().addAll("Easy", "Normal", "Hard", "Nightmare");
        difficultySelector.setValue(entityMainModel.difficulty().toString().substring(0, 1).toUpperCase() + entityMainModel.difficulty().toString().substring(1).toLowerCase());
    }

    private void resolutionSelect(){
        resolutionSelector.getItems().addAll("1280x720", "1920x1080", "2560x1440");
        FileReader fileReader = new FileReader();
        fileReader.gameOptionReader();
        resolutionSelector.setValue(fileReader.getGameOptions().getResolutionH() + "x" + fileReader.getGameOptions().getResolutionV());
    }

    private void saveButtonEvent(){
        saveButton.setOnAction(actionEvent -> {
            String selectedResolution = resolutionSelector.getValue();
            String selectedDifficulty = difficultySelector.getValue();
            // Ustawianie nowych opcji do zapisu, NIE PRZENOSIC TEGO!!!
            if (selectedResolution.contains("720")){
                gameOptions.setResolutionV("720");
                gameOptions.setResolutionH("1280");
            }else if (selectedResolution.contains("1080")){
                gameOptions.setResolutionV("1080");
                gameOptions.setResolutionH("1920");
            } else if (selectedResolution.contains("1440")) {
                gameOptions.setResolutionV("1440");
                gameOptions.setResolutionH("2560");
            }else {
                gameOptions.setResolutionV("720");
                gameOptions.setResolutionH("1280");
            }

            switch (selectedDifficulty){
                case "Easy" -> difficulty = Difficulty.EASY;
                case "Normal" -> difficulty = Difficulty.NORMAL;
                case "Hard" -> difficulty = Difficulty.HARD;
                case "Nightmare" -> difficulty = Difficulty.NIGHTMARE;
            }

            // Zapis zmienionych opcji do pliku json
            try {
                EntityMainModel updatedEntityMainModel = new EntityMainModel(entityMainModel.entityModel(), difficulty);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.writeValue(new File("src/main/resources/entity/entity_location.json"), updatedEntityMainModel);

                objectMapper.writeValue(new File("src/main/resources/options/game_options.json"), gameOptions);
                resolutionSelector.setValue(gameOptions.getResolutionV()+"p");
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
        });
    }

    private void closeButtonEvent(){
        closeButton.setOnAction(actionEvent -> {
            stage.close();
        });
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

