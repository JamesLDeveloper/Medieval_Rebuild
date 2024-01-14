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

    private double goldValue;

    private double scrapValue;

    private Player player;

    /* Constructors */
    public Weapon(String name, double damage, double goldValue, double scrapValue, int imageId) {
        this.name = name;
        this.damage = damage;
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
    public int getImageId(){
        return weaponImageId;
    }


    @Override
    public double getDefenseRating(){
        return 0;
    }

    public double getDurability(){
        return 0;
    }

}