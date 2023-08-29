package com.arturoo404.game.utils;

public class StatsUtils {

    public static int calcDamageAfterReduction(int entityDamage, int armor){
        return entityDamage * (1 - (armor / (armor + 100)));
    }
}
