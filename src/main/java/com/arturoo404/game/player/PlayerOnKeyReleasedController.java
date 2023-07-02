package com.arturoo404.game.player;

import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.skills.SkillsController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerOnKeyReleasedController implements EventHandler<KeyEvent> {

    private final Movement movement;

    private final SkillsController skillsController;
    public PlayerOnKeyReleasedController(Movement movement, SkillsController skillsController) {
        this.movement = movement;
        this.skillsController = skillsController;
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
    }
}
