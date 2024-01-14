package com.example.medievalrebuild.Equipable;

import android.content.Context;

import java.io.Serializable;

public interface Equipable extends Serializable {


//    String getName();

//    public void equipItem();

    public void reduceDurability(double reduction, Context context);

    public String getName();

    public int getImageId();

    public double getDamage();

    public double getDefenseRating();

    public double getDurability();

    public double getGoldValue();

    public double getScrapValue();

    public void setGoldValue(double goldValue);

    public void setScrapValue(double scrapValue);



}


