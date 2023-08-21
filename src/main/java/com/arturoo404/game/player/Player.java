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

    private final Rectangle playerShape;

    private final Rectangle entityHitBox;
    private Movement movement;
    private PlayerStats skillStats;
    private KeyCode direction;
    private AnchorPane anchorPane;
    private KeyAction keyAction;
    public Player(Rectangle playerShape, Rectangle entityHitBox, AnchorPane anchorPane, KeyAction keyAction) {
        this.playerShape = playerShape;
        this.entityHitBox = entityHitBox;
        this.anchorPane = anchorPane;
        this.keyAction = keyAction;
    }
}
