package com.example.medievalrebuild.Equipable;

import android.content.Context;

import java.io.Serializable;

public interface Equipable extends Serializable {


//    String getName();

//    public void equipItem();

    public void reduceDurability(int reduction, Context context);

    public String getName();

    public int getDamage();

    public int getDefenseRating();

    public int getDurability();



}


