package com.arturoo404.game.file;

public class EntityModelFile {
    private EntityPosition[] entityPositions;

    public EntityPosition[] getEntityPosition() {
        return entityPositions;
    }

    public void setEntityPositions(EntityPosition[] entityPositions) {
        this.entityPositions = entityPositions;
    }

    public EntityModelFile(EntityPosition[] entityPositionFile) {
        this.entityPositions = entityPositionFile;
    }

    public EntityModelFile() {
    }
}
