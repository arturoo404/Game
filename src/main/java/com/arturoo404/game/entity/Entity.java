package com.arturoo404.game.entity;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity {

    private int posX;
    private int posY;
    private double width;
    private double height;
    private EntityType entityType;
    private double maxHealth;
    private double currentHealth;
    private double damage;
    private int speed;
    private int range;
    private double attackSpeed;
    private int detectionRange;
    private DetectionStatus detectionStatus;
    private int detectionTimerDefaultValue;
    private int detectionTimer;
    private int armor;
    private Rectangle rectangle;
    private Circle circle;
    private LivingEntities livingEntities;
    private EntityDirection direction;
    private EntityMovementAnimation entityMovementAnimation;
    private EntityAiValue aiValue;
    private boolean attack = false;
    private boolean attackAnimation = false;
    private EntityBars entityBars;
    private AnchorPane pane;

    public double calculateAttackSpeedTime(){
        return 1000 / this.attackSpeed / 2;
    }
}
