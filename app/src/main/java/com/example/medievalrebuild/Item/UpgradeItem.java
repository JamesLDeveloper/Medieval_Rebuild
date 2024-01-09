package com.example.medievalrebuild.Item;

import java.io.Serializable;

public class UpgradeItem implements Item, Serializable {

    private static final long serialVersionUID = 1L;

    private final String itemName;

    private double damage;

    private double dodgeChance;

    private double criticalChance;

    private double healAmount;

    private int strengthAmount;

    private int maxHealth;

    private boolean useOnAcquisition;

    public UpgradeItem(String itemName, int damage, double dodgeChance, double criticalChance, int healAmount, int strengthAmount, int maxHealth, boolean useOnAcquisition){
        this.itemName = itemName;
        this.damage = damage;
        this.dodgeChance = dodgeChance;
        this.criticalChance = criticalChance;
        this.healAmount = healAmount;
        this.strengthAmount = strengthAmount;
        this.maxHealth = maxHealth;
        this.useOnAcquisition = useOnAcquisition;
    }

    @Override
    public boolean getUseOnAcquisition(){
        return this.useOnAcquisition;
    }

    @Override
    public void setUseOnAcquisition(boolean useOnPickup){
        this.useOnAcquisition = useOnPickup;
    }

    @Override
    public String getItemName(){
        return this.itemName;
    }

    @Override
    public double getDamage(){
        return damage;
    }

    @Override
    public void setDamage(double damage){
        this.damage = damage;
    }

    @Override
    public void setHealAmount(double healAmount){
        this.healAmount = healAmount;
    }

    @Override
    public double getHealAmount(){
        return healAmount;
    }

    @Override public int getStrengthAmount() {return strengthAmount;}

    @Override public void setStrengthAmount(int strengthAmount){
        this.strengthAmount = strengthAmount;
    }

    @Override
    public void setCriticalChance(double criticalChance){
        this.criticalChance = criticalChance;
    }

    @Override
    public double getCriticalChance(){
        return criticalChance;
    }

    @Override
    public void setDodgeChance(double dodgeChance){
        this.dodgeChance = dodgeChance;
    }

    @Override
    public double getDodgeChance(){
        return this.dodgeChance;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
