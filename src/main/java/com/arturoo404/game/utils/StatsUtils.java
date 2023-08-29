package com.arturoo404.game.utils;

public class StatsUtils {

    public static int calcDamageAfterReduction(double entityDamage, double armor){
        return (int) (entityDamage * (1 - (armor / (armor + 100))));
    }
}
