package com.example.medievalrebuild.Weapons;

import com.example.medievalrebuild.Equipable.Equipable;

import java.io.Serializable;

public class Weapon implements Equipable, Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private final String name;
    private final int damage;

    /* Constructors */
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
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
    public int getDamage() {
        return damage;
    }

    @Override
    public void reduceDurability(int reduction){

    }

    @Override
    public int getDefenseRating(){
        return 0;
    }

    public int getDurability(){
        return 0;
    }

}