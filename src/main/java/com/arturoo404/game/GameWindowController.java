package com.arturoo404.game;

import com.arturoo404.game.generate.MapGenerator;
import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.Player;
import com.arturoo404.game.player.PlayerOnKeyPressedController;
import com.arturoo404.game.player.PlayerOnKeyReleasedController;
import com.opencsv.exceptions.CsvException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {

    @FXML
    private AnchorPane pane;
    private List<Rectangle> init;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGame();

        Rectangle playerShape = new Rectangle(0,300,60, 60);
        playerShape.setFill(Color.RED);
        pane.getChildren().add(playerShape);
        Player player = new Player(playerShape);
        Movement movement = new Movement(player, init);
        movement.init();
        player.setMovement(movement);

        pane.setOnKeyPressed(new PlayerOnKeyPressedController(movement));
        pane.setOnKeyReleased(new PlayerOnKeyReleasedController(movement));
        pane.requestFocus();
    }

    private void initGame(){
        MapGenerator mapGenerator = new MapGenerator(pane);
        try {
            init = mapGenerator.init();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
