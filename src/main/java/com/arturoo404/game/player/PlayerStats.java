package com.arturoo404.game.player;

public class PlayerStats {

    private double attackSpeed = 1;
    private int maxHealth = 100;
    private int currentHealth = 20;
    private int maxMana = 100;
    private int currentMana = 40;


    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxMana() {return maxMana;}

    public void setMaxMana(int maxMana) {this.maxMana = maxMana; }

    public int getCurrentMana() {return currentMana;}

    public void setCurrentMana(int currentMana) {this.currentMana = currentMana; }
}
