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
    private float signumY;
    private float signumX;


    public void setSignumY(int signumY) {
        this.signumY = Math.signum(signumY);
    }

    public void setSignumX(int signumX) {
        this.signumX = Math.signum(signumX);
    }
}
