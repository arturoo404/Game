package com.arturoo404.game;

import com.arturoo404.game.generate.EntityGenerator;
import com.arturoo404.game.generate.MapGenerator;
import com.arturoo404.game.player.*;
import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.skills.SkillsController;
import com.opencsv.exceptions.CsvException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {

    @FXML
    private AnchorPane pane;
    private List<Rectangle> init;


    /**
     * Initializes the game window.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGame();

        Rectangle playerShape = new Rectangle(300,300,80, 120);

        Image image = new Image(getClass().getResourceAsStream("/txt/player/player.png"));
        PixelReader reader = image.getPixelReader();
        WritableImage newImage = new WritableImage(reader, 320, 481, 80, 120);
        playerShape.setFill(new ImagePattern(newImage));

        pane.getChildren().add(playerShape);
        Player player = new Player(playerShape, this.pane);

        player.setSkillStats(new PlayerStats());
        Movement movement = new Movement(player, init);
        movement.init();
        player.setMovement(movement);
        SkillsController skillsController = new SkillsController(player);
        skillsController.init();
        pane.setOnKeyPressed(new PlayerOnKeyPressedController(movement, skillsController));
        pane.setOnKeyReleased(new PlayerOnKeyReleasedController(movement, skillsController));
        pane.requestFocus();

    }

    /**
     * Initializes the map.
     */
    private void initGame(){
        MapGenerator mapGenerator = new MapGenerator(pane);
        EntityGenerator entityGenerator = new EntityGenerator(pane);
        try {
            init = mapGenerator.init();
            entityGenerator.init();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
