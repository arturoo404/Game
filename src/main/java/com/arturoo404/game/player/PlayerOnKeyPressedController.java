package com.arturoo404.game.player;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyPressedController implements EventHandler<KeyEvent> {

    private final Player player;

    public PlayerOnKeyPressedController(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> player.setGoUp(true);
            case S -> player.setGoDown(true);
            case A -> player.setGoLeft(true);
            case D -> player.setGoRight(true);
        }
    }
}
