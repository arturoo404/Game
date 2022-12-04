package com.arturoo404.game;

import com.arturoo404.game.file.CsvFileReader;
import com.arturoo404.game.generate.MapGenerator;
import com.opencsv.exceptions.CsvException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {

    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MapGenerator mapGenerator = new MapGenerator(pane);
        List<Rectangle> init;
        try {
            init = mapGenerator.init();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        System.out.println(init.size());
    }
}
