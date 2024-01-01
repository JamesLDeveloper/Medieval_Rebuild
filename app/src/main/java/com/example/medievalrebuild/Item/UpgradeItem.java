package com.example.medievalrebuild.Item;

import java.io.Serializable;

public class UpgradeItem implements Item, Serializable {

    private static final long serialVersionUID = 1L;

    private final String itemName;

    private double damage;

    private double dodgeChance;

    private double criticalChance;

    private double healAmount;

    public UpgradeItem(String itemName, int damage, double dodgeChance, double criticalChance, int healAmount){
        this.itemName = itemName;
        this.damage = damage;
        this.dodgeChance = dodgeChance;
        this.criticalChance = criticalChance;
        this.healAmount = healAmount;
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

}
