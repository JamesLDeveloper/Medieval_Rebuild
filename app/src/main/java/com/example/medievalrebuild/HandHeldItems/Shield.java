package com.example.medievalrebuild.HandHeldItems;

import android.content.Context;

import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.Game.Player;

import java.io.Serializable;

public class Shield implements Equipable, Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private final String name;
    private final double damage;

    private final int weaponImageId;

    private double durability;

    private double defenseRating;

    private double goldSellValue;

    private double scrapValue;

    private double goldPurchaseCost;

    private int handsNeeded;

    private Player player;

    /* Constructors */
    public Shield(String name, double damage, double durability, double defenseRating, double goldSellValue, double scrapValue, int handsNeeded, int imageId) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
        this.defenseRating = defenseRating;
        this.goldSellValue = goldSellValue;
        this.scrapValue = scrapValue;
        this.goldPurchaseCost = goldSellValue * 1.2;
        this.handsNeeded = handsNeeded;
        this.weaponImageId = imageId;
    }

    // implement accuracy variable?
    // implement critical damage variable?
    // implement damage over time variable?
    // implement healing immunity variable?

    /* Getters & Setters */

    public String getName() {
        return name;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public void reduceDurability(double reduction, Context context){

    }

    @Override
    public int getImageId(){
        return weaponImageId;
    }


    @Override
    public double getDefenseRating(){
        return 0;
    }

    @Override
    public double getGoldSellValue() {
        return goldSellValue;
    }

    @Override
    public void setGoldSellValue(double goldSellValue) {
        this.goldSellValue = goldSellValue;
    }

    @Override
    public double getScrapValue() {
        return scrapValue;
    }

    @Override
    public void setScrapValue(double scrapValue) {
        this.scrapValue = scrapValue;
    }

    @Override
    public String toString() {

        if (name.equalsIgnoreCase("none")) {

            return name + "\n";

        }
        return name + ".\nDamage: " + damage + "\nDurability: " + durability + ", Defense Rating: " + defenseRating + "\n";
    }

    public double getDurability(){
        return 0;
    }

    @Override
    public double getGoldPurchaseCost(){
        return this.goldPurchaseCost;
    }

    @Override
    public void setGoldPurchaseCost(double goldPurchaseCost){
        this.goldPurchaseCost = goldPurchaseCost;
    }

}