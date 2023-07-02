package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Movement getMovement() {
        return movement;
    }

    public KeyCode getDirection() {
        return direction;
    }

    public void setDirection(KeyCode direction) {
        this.direction = direction;
    }

    public void setSkillStats(PlayerStats skillStats) {
        this.skillStats = skillStats;
    }

    public PlayerStats getSkillStats() {
        return skillStats;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}
