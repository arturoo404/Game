package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.skills.SkillsController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyReleasedController implements EventHandler<KeyEvent> {

    private final Movement movement;

    private final SkillsController skillsController;

    private final KeyAction keyAction;
    public PlayerOnKeyReleasedController(Movement movement, Player player, KeyAction keyAction) {
        this.movement = movement;
        this.skillsController = player.getSkillsController();
        this.keyAction = keyAction;
    }

    /**
     * Handle the key released event.
     * @param keyEvent
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case W -> movement.setGoUp(false);
            case S -> movement.setGoDown(false);
            case A -> movement.setGoLeft(false);
            case D -> movement.setGoRight(false);
        }

        switch (keyEvent.getCode()){
            case SPACE -> skillsController.setBasicAttack(false);
        }
        switch (keyEvent.getCode()){
            case Q -> skillsController.sethealAbilityBoolean(false);
        }
    }
}
