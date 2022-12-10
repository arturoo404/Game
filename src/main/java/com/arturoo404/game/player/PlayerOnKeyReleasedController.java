package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyReleasedController implements EventHandler<KeyEvent> {

    private final Movement movement;

    public PlayerOnKeyReleasedController(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case W -> movement.setGoUp(false);
            case S -> movement.setGoDown(false);
            case A -> movement.setGoLeft(false);
            case D -> movement.setGoRight(false);
        }
    }
}
