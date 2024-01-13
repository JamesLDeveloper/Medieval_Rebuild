package com.example.medievalrebuild.ArmourFiles;

import java.io.Serializable;

public class Shirt extends Armour implements Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Constructors */
    public Shirt(String name, double durability, double defenseRating, int imageId) {
        super(name, durability, defenseRating, imageId);
    }
}