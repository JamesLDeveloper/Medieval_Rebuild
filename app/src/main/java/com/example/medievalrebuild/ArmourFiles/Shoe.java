package com.example.medievalrebuild.ArmourFiles;

import java.io.Serializable;

public class Shoe extends Armour implements Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Constructors */
    public Shoe(String name, double durability, double defenseRating, double goldSellValue, double scrapValue, int imageId) {
        super(name, durability, defenseRating, goldSellValue, scrapValue, imageId);
    }
}