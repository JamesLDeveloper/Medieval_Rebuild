package com.example.medievalrebuild.Weapons;

import java.io.Serializable;

public class Weapon implements Serializable {

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

    public int getDamage() {
        return damage;
    }
}