package com.arturoo404.game.file;

import com.arturoo404.game.entity.EntityType;

public class EntityPosition {
    private int posX;
    private int posY;
    private EntityType entityType;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityPosition(int posX, int posY, EntityType entityType) {
        this.posX = posX;
        this.posY = posY;
        this.entityType = entityType;
    }

    public EntityPosition() {
    }
}
