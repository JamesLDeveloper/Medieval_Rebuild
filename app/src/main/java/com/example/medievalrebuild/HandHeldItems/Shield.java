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

    private double goldValue;

    private double scrapValue;

    private Player player;

    /* Constructors */
    public Shield(String name, double damage, double durability, double defenseRating, double goldValue, double scrapValue, int imageId) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
        this.defenseRating = defenseRating;
        this.goldValue = goldValue;
        this.scrapValue = scrapValue;
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
    public double getGoldValue() {
        return goldValue;
    }

    @Override
    public void setGoldValue(double goldValue) {
        this.goldValue = goldValue;
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

}