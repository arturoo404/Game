package com.arturoo404.game.generate.difficulty;

import java.util.concurrent.ThreadLocalRandom;

public class GameDifficulty {
    private static final double multi = ThreadLocalRandom.current().nextDouble(0.7, 1.3);

    public static Integer healthChanger(int health, Difficulty difficulty){
        switch (difficulty){
            case EASY -> {return (int) ((health * 0.6) * multi);}
            case NORMAL -> {return (int) (health * multi);}
            case HARD -> {return (int) ((health * 1.4) * multi);}
            case NIGHTMARE -> {return (int) ((health * 2) * multi);}
        }
        return health;
    }

    public static Integer damageChanger(int damage, Difficulty difficulty){
        switch (difficulty){
            case EASY -> {return (int) ((damage * 0.5) * multi);}
            case NORMAL -> {return (int) (damage * multi);}
            case HARD -> {return (int) ((damage * 1.5) * multi);}
            case NIGHTMARE -> {return (int) ((damage * 2) * multi);}
        }
        return damage;
    }

    public static Integer defenceChanger(int defence, Difficulty difficulty){
        switch (difficulty){
            case EASY -> {return (int) ((defence * 0.6) * multi);}
            case NORMAL -> {return (int) (defence * multi);}
            case HARD -> {return (int) ((defence * 1.3) * multi);}
            case NIGHTMARE -> {return (int) ((defence * 1.8) * multi);}
        }
        return defence;
    }
}

