package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class Player {

    private final Rectangle rectangle;
    private Movement movement;
    private SkillStats skillStats;

    private KeyCode direction;

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

    public KeyCode getDirection() {
        return direction;
    }

    public void setDirection(KeyCode direction) {
        this.direction = direction;
    }

    public void setSkillStats(SkillStats skillStats) {
        this.skillStats = skillStats;
    }

    public SkillStats getSkillStats() {
        return skillStats;
    }
}
