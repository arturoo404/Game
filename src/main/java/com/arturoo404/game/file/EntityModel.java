package com.arturoo404.game.file;

import com.arturoo404.game.entity.EntityType;

public record EntityModel(int width, int height, EntityType entityType, int health, double damage,
                          int speed, EntityPosition[] entityPositions, int range, double attackSpeed,
                          int detectionRange, int defence, int detectionTimer) {

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public EntityType entityType() {
        return entityType;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public double damage() {
        return damage;
    }

    @Override
    public int speed() {
        return speed;
    }

    @Override
    public EntityPosition[] entityPositions() {
        return entityPositions;
    }

    @Override
    public int range() {
        return range;
    }

    @Override
    public double attackSpeed() {
        return attackSpeed;
    }

    @Override
    public int detectionRange() {
        return detectionRange;
    }

    @Override
    public int defence() {
        return defence;
    }
}
