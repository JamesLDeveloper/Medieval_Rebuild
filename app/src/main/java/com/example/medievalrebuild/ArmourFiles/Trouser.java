package com.example.medievalrebuild.ArmourFiles;

import java.io.Serializable;

public class Trouser extends Armour implements Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Constructors */
    public Trouser(String name, int durability, int defenseRating) {
        super(name, durability, defenseRating);
    }
}