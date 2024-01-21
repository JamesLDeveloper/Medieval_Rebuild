package com.example.medievalrebuild.HandHeldItems;

import android.content.Context;

import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.Game.Player;

import java.io.Serializable;

public class Weapon implements Equipable, Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private final String name;
    private final double damage;

    private final int weaponImageId;

    private double goldSellValue;

    private double scrapValue;

    private double purchaseCost;

    private int handsNeeded;

    private Player player;

    /* Constructors */
    public Weapon(String name, double damage, double goldSellValue, double scrapValue, int handsNeeded, int imageId) {
        this.name = name;
        this.damage = damage;
        this.goldSellValue = goldSellValue;
        this.scrapValue = scrapValue;
        this.purchaseCost = goldSellValue * 1.2;
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
    public int getImageId(){
        return weaponImageId;
    }


    @Override
    public double getDefenseRating(){
        return 0;
    }

    @Override
    public double getDurability(){
        return 0;
    }

    @Override
    public double getGoldPurchaseCost(){
        return purchaseCost;
    }

    @Override
    public void setGoldPurchaseCost(double purchaseCost){
        this.purchaseCost = purchaseCost;
    }

    @Override
    public String toString() {

        if (name.equalsIgnoreCase("none")) {

            return name + "\n";

        }
        return name + ".\nDamage: " + damage + "\nHands Needed: " + handsNeeded;
    }


}