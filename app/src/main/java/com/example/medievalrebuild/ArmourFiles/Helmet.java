package com.example.medievalrebuild.ArmourFiles;

import java.io.Serializable;

public class Helmet extends Armour implements Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Constructors */
    public Helmet(String name, double durability, double defenseRating, double goldValue, double scrapValue, int imageId) {
        super(name, durability, defenseRating, goldValue, scrapValue, imageId);
    }
}