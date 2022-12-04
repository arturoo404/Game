package com.arturoo404.game.player;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyReleasedController implements EventHandler<KeyEvent> {

    private final Player player;

    public PlayerOnKeyReleasedController(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case W -> player.setGoUp(false);
            case S -> player.setGoDown(false);
            case A -> player.setGoLeft(false);
            case D -> player.setGoRight(false);
        }
    }
}
