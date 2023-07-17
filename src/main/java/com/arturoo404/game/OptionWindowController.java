package com.arturoo404.game;

import com.arturoo404.game.file.EntityMainModel;
import com.arturoo404.game.file.FileReader;
import com.arturoo404.game.player.Difficulty;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OptionWindowController {
    private Stage optionsStage;
    private final FileReader fileReader = new FileReader();
    private String difficulty;
    public void setOptionsStage(Stage optionsStage) {
        this.optionsStage = optionsStage;
    }


    @FXML
    private void selectEasy(ActionEvent event){
        difficulty = "EASY";
    }
    @FXML
    private void selectMedium(ActionEvent event){
        difficulty = "NORMAL";
    }
    @FXML
    private void selectHard(ActionEvent event){
        difficulty = "HARD";
    }
    @FXML
    private void selectNightmare(ActionEvent event){
        difficulty = "NIGHTMARE";
    }
    @FXML
    private void closeWindow(ActionEvent event) throws IOException {
        EntityMainModel entityMainModel = FileReader.entityModelFilesRead();
        EntityMainModel updatedEntityMainModel = new EntityMainModel(entityMainModel.entityModel(), Difficulty.valueOf(difficulty));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/entity/entity_location.json"), updatedEntityMainModel);

        optionsStage.close();
    }

}