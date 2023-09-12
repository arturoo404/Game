package com.arturoo404.game.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerMastery {

    private int intelligence = 1;
    private int vigor = 1;
    private int mind = 1;
    private int dexterity = 1;
    private int arcane = 1;
    /*private int luck = 1; Potencjalne mastery pod lepsze dropy etc...
    private int focus = 1; Potencjalne mastery pod kryty */
    private int intelligenceValue = 3;
    private int vigorValue = 12;
    private int mindValue = 8;
    private double dexterityValue = 0.05;
    private double arcaneValue = 4;
}
