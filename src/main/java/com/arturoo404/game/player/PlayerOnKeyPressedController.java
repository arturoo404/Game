package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyPressedController implements EventHandler<KeyEvent> {

    private final Movement movement;

    public PlayerOnKeyPressedController(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> movement.setGoUp(true);
            case S -> movement.setGoDown(true);
            case A -> movement.setGoLeft(true);
            case D -> movement.setGoRight(true);
        }
    }
}
