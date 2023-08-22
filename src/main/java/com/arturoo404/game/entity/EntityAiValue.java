package com.arturoo404.game.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntityAiValue {

    private boolean xMoveRepeating;
    private boolean yMoveRepeating;
    private int repeating = 0;
    private int dx;
    private int dy;
    private double signumY;
    private double signumX;
    private EntityDirection detectionDirection = EntityDirection.LEFT;


    public void setSignumY(double signumY) {
        this.signumY = Math.signum(signumY);
    }

    public void setSignumX(double signumX) {
        this.signumX = Math.signum(signumX);
    }
}
