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

    public double getGoldSellValue();

    public double getScrapValue();

    public void setGoldSellValue(double goldSellValue);

    public void setScrapValue(double scrapValue);

    public double getGoldPurchaseCost();

    public void setGoldPurchaseCost(double goldPurchaseCost);




}


