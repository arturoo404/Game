package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import javafx.scene.shape.Rectangle;

public class Player {

    private final Rectangle rectangle;
    private Movement movement;

    public Player(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Movement getMovement() {
        return movement;
    }
}
