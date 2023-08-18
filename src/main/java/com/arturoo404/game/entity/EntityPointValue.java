package com.arturoo404.game.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntityPointValue {

    private int dx;
    private int dy;
    private double value;
    private int xMove;
    private int yMove;
}
