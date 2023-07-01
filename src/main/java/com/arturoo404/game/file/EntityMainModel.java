package com.arturoo404.game.file;

public record EntityMainModel(EntityModel[] entityModel) {
    @Override
    public EntityModel[] entityModel() {
        return entityModel;
    }
}
