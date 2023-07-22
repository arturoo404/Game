package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private final Rectangle rectangle;
    private Movement movement;
    private PlayerStats skillStats;
    private KeyCode direction;
    private AnchorPane anchorPane;
    public Player(Rectangle rectangle, AnchorPane anchorPane) {
        this.rectangle = rectangle;
        this.anchorPane = anchorPane;
    }
}
