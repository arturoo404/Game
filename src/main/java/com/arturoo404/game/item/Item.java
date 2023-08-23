package com.arturoo404.game.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private String name = "N/A";
    private String description = "N/A";
    private double attackSpeed = 0;
    private int armor = 0;
    private int cooldownReduction = 0;
    private int damage = 0;
    private int health = 0;
    private int mana = 0;
    private int manaRegen = 0;
    private int hpRegen = 0;
    private int lvlRequirement = 0;
    private int marketPrice = 0;
    private ItemTier tier = ItemTier.COMMON;
    private ItemSet itemSet = ItemSet.NONE;
    private String txtPath = "txt.txt";
    private ItemType itemType = ItemType.INGREDIENTS;
}
