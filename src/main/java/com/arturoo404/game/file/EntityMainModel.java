package com.arturoo404.game.file;

import com.arturoo404.game.generate.difficulty.Difficulty;

public record EntityMainModel(EntityModel[] entityModel, Difficulty difficulty) {

    @Override
    public EntityModel[] entityModel() {
        return entityModel;
    }
}
