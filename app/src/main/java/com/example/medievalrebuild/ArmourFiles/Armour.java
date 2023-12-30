package com.example.medievalrebuild.ArmourFiles;

import com.example.medievalrebuild.Equipable.Equipable;

import java.io.Serializable;

public class Armour implements Equipable, Serializable {

    /* Class Variables */

    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private final String name;
    private int durability;
    private int defenseRating;

    private int damage;

    /* Constructors */
    public Armour(String name, int durability, int defenseRating) {
        this.name = name;
        this.durability = durability;
        this.defenseRating = defenseRating;
        this.damage = 0;
    }

    /* Instance Methods */
    @Override
    public void reduceDurability(int reduction) {
        if (durability > 0) {
            durability -= reduction;
            if (durability <= 0) {
                durability = 0;
                System.out.println("Your " + name + " has been rendered useless. It cannot be repaired and must be sold for scraps.");
                defenseRating = 0;
            }
        }
    }

    public String getName(){
        return this.name;
    }


    public void repairArmour(int amount) {
        durability += amount;
        if (durability > 100) {
            durability = 100;
        }
    }

    /* Getters & Setters */
    @Override
    public int getDefenseRating() {
        return (int) (defenseRating * (durability / 100.0)) ;
    }

    @Override
    public String toString() {

        if (name.equalsIgnoreCase("none")) {

            return name + "\n";

        }
        return name + ". \nDurability: " + durability + ", Defense Rating: " + defenseRating + "\n";
    }
@Override
    public int getDurability() {
        return this.durability;
    }


    @Override
    public int getDamage(){
    return damage;
}



}