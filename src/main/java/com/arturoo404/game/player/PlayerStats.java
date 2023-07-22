package com.arturoo404.game.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStats {

    private double attackSpeed = 1;
    private int maxHealth = 100;
    private int currentHealth = 20;
    private int maxMana = 100;
    private int currentMana = 40;
    private double healthRegen = 5;
    private double percentHealthRegen = (double) maxHealth / 200;
    private double manaRegen = 10;
    private double percentManaRegen = (double) maxMana / 200;
    private int armor = 0;
    private int cooldownReduction = 0;
    private int damage = 10;
    private int money = 10;
    private int currentExp = 0;
    private int nextLvlExp = 20;
    private int playerLvl = 1;
}
