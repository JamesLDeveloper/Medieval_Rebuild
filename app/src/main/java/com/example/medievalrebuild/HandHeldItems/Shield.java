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

    private Player player;

    /* Constructors */
    public Shield(String name, double damage, double durability, double defenseRating, int imageId) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
        this.defenseRating = defenseRating;
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