package com.arturoo404.game.generate;

import com.arturoo404.game.entity.LivingEntities;
import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.file.EntityModel;
import com.arturoo404.game.file.EntityPosition;
import com.arturoo404.game.generate.difficulty.Difficulty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.arturoo404.game.generate.difficulty.GameDifficulty.*;


public class GenerateEntityModel {

    private final AnchorPane pane;

    public GenerateEntityModel(AnchorPane pane) {
        this.pane = pane;
    }

    public List<Wolf> generateWolfModel(EntityModel entityModel, LivingEntities livingEntities, Difficulty difficulty){
        List<Wolf> wolfList = new ArrayList<>();
        for (EntityPosition model : entityModel.entityPositions()) {
            Wolf wolf = generateWolf(entityModel, model, livingEntities, difficulty);
            wolfList.add(wolf);
        }
        return wolfList;
    }

    private Wolf generateWolf(EntityModel entityModel, EntityPosition entityPosition, LivingEntities livingEntities, Difficulty difficulty){
        Wolf wolf = new Wolf();
        wolf.setAttackSpeed(entityModel.width());
        wolf.setEntityType(entityModel.entityType());
        wolf.setHealth(healthChanger(entityModel.health(),difficulty));
        wolf.setDamage(damageChanger((int) entityModel.damage(),difficulty));
        wolf.setSpeed(entityModel.speed());
        wolf.setRange(entityModel.range());
        wolf.setDetectionRange(entityModel.detectionRange());
        wolf.setDefence(defenceChanger(entityModel.defence(),difficulty));
        wolf.setRectangle(new Rectangle(entityPosition.posX(), entityPosition.posY(), entityModel.width(), entityModel.height()));
        wolf.setLivingEntities(livingEntities);
        pane.getChildren().add(wolf.getRectangle());
        return wolf;
    }
}
