package com.arturoo404.game.player;

import com.arturoo404.game.player.gui.PlayerGuiController;
import com.arturoo404.game.player.movement.Movement;
import com.arturoo404.game.player.skills.SkillsController;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private final Rectangle playerShape;
    private final Rectangle entityHitBox;
    private Movement movement;
    private PlayerStats skillStats;
    private KeyCode direction;
    private AnchorPane anchorPane;
    private KeyAction keyAction;
    private SkillsController skillsController;
    private PlayerGuiController playerGuiController;
    private PlayerExperiences playerExperiences;
    private PlayerMoney playerMoney;
    private Level level;

    public Player(Rectangle playerShape, Rectangle entityHitBox, AnchorPane anchorPane, KeyAction keyAction) {
        playerExperiences = new PlayerExperiences();
        playerMoney = new PlayerMoney();
        level = new Level(this);
        keyAction.init(this);
        this.playerShape = playerShape;
        this.entityHitBox = entityHitBox;
        this.anchorPane = anchorPane;
        this.keyAction = keyAction;
        level.init();
    }

    public void setSkillsController(SkillsController skillsController) {
        this.skillsController = skillsController;
    }
}
