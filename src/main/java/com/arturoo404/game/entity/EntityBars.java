package com.arturoo404.game.entity;

import javafx.scene.control.ProgressBar;
import lombok.Getter;

@Getter
public class EntityBars {

    private ProgressBar healthBar;
    private final Entity entity;

    public void createProgresBar(){
        healthBar = new ProgressBar();
        healthBar.setLayoutY(entity.getRectangle().getY() - 10);
        healthBar.setLayoutX(entity.getRectangle().getX());
        healthBar.setProgress(entity.getMaxHealth() / entity.getCurrentHealth());
        healthBar.setStyle("-fx-border-width: 1px; -fx-accent: red; -fx-control-inner-background: rgba(255,255,255,0); ");
        healthBar.setMaxHeight(15);
        entity.getPane().getChildren().add(healthBar);

    }

    public EntityBars(Entity entity) {
        this.entity = entity;
    }
}
