package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.skills.SkillsController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyPressedController implements EventHandler<KeyEvent> {

    private final Movement movement;

    private final SkillsController skillsController;

    public PlayerOnKeyPressedController(Movement movement, SkillsController skillsController) {
        this.movement = movement;
        this.skillsController = skillsController;
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
    }
}
