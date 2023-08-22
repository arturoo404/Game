package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.skills.SkillsController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyPressedController implements EventHandler<KeyEvent> {

    private final Movement movement;

    private final SkillsController skillsController;

    private final KeyAction keyAction;

    public PlayerOnKeyPressedController(Movement movement, SkillsController skillsController, KeyAction keyAction) {
        this.movement = movement;
        this.skillsController = skillsController;
        this.keyAction = keyAction;
    }

    /**
     * Handle the key pressed event
     * @param keyEvent
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> movement.setGoUp(true);
            case S -> movement.setGoDown(true);
            case A -> movement.setGoLeft(true);
            case D -> movement.setGoRight(true);
        }

        switch (keyEvent.getCode()) {
            case SPACE -> skillsController.setBasicAttack(true);
        }

        switch (keyEvent.getCode()){
            case I -> keyAction.setOpenInventory(!keyAction.getKeyPress().get());
        }
    }
}
