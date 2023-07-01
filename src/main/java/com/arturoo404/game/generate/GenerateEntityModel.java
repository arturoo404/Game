package com.arturoo404.game.generate;

import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.file.EntityModel;
import com.arturoo404.game.file.EntityPosition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GenerateEntityModel {

    private AnchorPane pane;

    public GenerateEntityModel(AnchorPane pane) {
        this.pane = pane;
    }

    public List<Wolf> generateWolfModel(EntityModel entityModel){
        List<Wolf> wolfList = new ArrayList<>();
        for (EntityPosition model : entityModel.entityPositions()) {
            Wolf wolf = generateWolf(entityModel, model);
            wolfList.add(wolf);
        }
        return wolfList;
    }

    private Wolf generateWolf(EntityModel entityModel, EntityPosition entityPosition){
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
        pane.getChildren().add(wolf.getRectangle());
        return wolf;
    }
}
