package com.example.medievalrebuild.Equipable;

import java.io.Serializable;

public interface Equipable extends Serializable {

//    String getName();

//    public void equipItem();

    public void reduceDurability(int reduction);

    public String getName();

    public int getDamage();

    public int getDefenseRating();

    public int getDurability();



}


