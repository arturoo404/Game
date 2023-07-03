package com.arturoo404.game.generate;

import com.arturoo404.game.entity.LivingEntities;
import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.file.EntityModel;
import com.arturoo404.game.file.EntityPosition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GenerateEntityModel {

    private final AnchorPane pane;

    public GenerateEntityModel(AnchorPane pane) {
        this.pane = pane;
    }

    public List<Wolf> generateWolfModel(EntityModel entityModel, LivingEntities livingEntities){
        List<Wolf> wolfList = new ArrayList<>();
        for (EntityPosition model : entityModel.entityPositions()) {
            Wolf wolf = generateWolf(entityModel, model, livingEntities);
            wolfList.add(wolf);
        }
        return wolfList;
    }

    private Wolf generateWolf(EntityModel entityModel, EntityPosition entityPosition, LivingEntities livingEntities){
        Wolf wolf = new Wolf();
        wolf.setAttackSpeed(entityModel.width());
        wolf.setEntityType(entityModel.entityType());
        wolf.setHealth(entityModel.health());
        wolf.setDamage(entityModel.damage());
        wolf.setSpeed(entityModel.speed());
        wolf.setRange(entityModel.range());
        wolf.setDetectionRange(entityModel.detectionRange());
        wolf.setDefence(entityModel.defence());
        wolf.setRectangle(new Rectangle(entityPosition.posX(), entityPosition.posY(), entityModel.width(), entityModel.height()));
        wolf.setLivingEntities(livingEntities);
        pane.getChildren().add(wolf.getRectangle());
        return wolf;
    }
}
