package com.arturoo404.game.player;

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

    public double getHealthRegen() {return healthRegen;}

    public void setHealthRegen(double healthRegen) {this.healthRegen = healthRegen; }

    public double getPercentHealthRegen() {return percentHealthRegen;}

    public void setPercentHealthRegen(double percentHealthRegen) {this.percentHealthRegen = percentHealthRegen; }

    public double getManaRegen() {return manaRegen;}

    public void setManaRegen(double manaRegen) {this.manaRegen = manaRegen; }

    public double getPercentManaRegen() {return percentManaRegen;}

    public void setPercentManaRegen(double percentManaRegen) {this.percentManaRegen = percentManaRegen; }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getCooldownReduction() {
        return cooldownReduction;
    }

    public void setCooldownReduction(int cooldownReduction) {
        this.cooldownReduction = cooldownReduction;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getNextLvlExp() {
        return nextLvlExp;
    }

    public void setNextLvlExp(int nextLvlExp) {
        this.nextLvlExp = nextLvlExp;
    }

    public int getPlayerLvl() {
        return playerLvl;
    }

    public void setPlayerLvl(int playerLvl) {
        this.playerLvl = playerLvl;
    }
}
