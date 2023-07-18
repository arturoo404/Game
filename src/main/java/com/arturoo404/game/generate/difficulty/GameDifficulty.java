package com.arturoo404.game.generate.difficulty;

public class GameDifficulty {

    public static Integer healthChanger(int health, Difficulty difficulty){
        switch (difficulty){
            case EASY -> {return (int) (health * 0.6);}
            case NORMAL -> {return health;}
            case HARD -> {return (int) (health * 1.4);}
            case NIGHTMARE -> {return health * 2;}
        }
        return health;
    }

    public static Integer damageChanger(int damage, Difficulty difficulty){
        switch (difficulty){
            case EASY -> {return (int) (damage * 0.5);}
            case NORMAL -> {return  damage;}
            case HARD -> {return (int) (damage * 1.5);}
            case NIGHTMARE -> {return damage * 2;}
        }
        return damage;
    }

    public static Integer defenceChanger(int defence, Difficulty difficulty){
        switch (difficulty){
            case EASY -> {return (int) (defence * 0.6);}
            case NORMAL -> {return defence;}
            case HARD -> {return (int) (defence * 1.3);}
            case NIGHTMARE -> {return (int) (defence * 1.8);}
        }
        return defence;
    }
}
