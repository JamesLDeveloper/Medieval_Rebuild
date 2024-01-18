package com.example.medievalrebuild.ArmourFiles;

import android.app.AlertDialog;
import android.content.Context;

import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.MainActivity;

import java.io.Serializable;

public class Armour implements Equipable, Serializable {

    /* Class Variables */

    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private final String armourName;
    private double durability;
    private double defenseRating;

    private double damage;

    private int imageId;

    private double goldSellValue;

    private double scrapValue;

    private double goldPurchaseCost;

    /* Constructors */
    public Armour(String name, double durability, double defenseRating, double goldSellValue, double scrapValue, int imageId) {
        this.armourName = name;
        this.durability = durability;
        this.defenseRating = defenseRating;
        this.goldSellValue = goldSellValue;
        this.goldPurchaseCost = goldSellValue * 1.2;
        this.scrapValue = scrapValue;
        this.imageId = imageId;
        this.damage = 0;
    }

    /* Instance Methods */
    @Override
    public void reduceDurability(double reduction, Context context) {
        if (durability > 0) {
            durability -= reduction;
            if (durability <= 0) {
                durability = 0;
                System.out.println("Your " + armourName + " has been rendered useless. It cannot be repaired and must be sold for scraps.");


                AlertDialog.Builder itemsScrappedDialogBuilder = new AlertDialog.Builder(context);
                itemsScrappedDialogBuilder.setCancelable(true);
                itemsScrappedDialogBuilder.setMessage("Your " + armourName + " has been rendered useless. It cannot be repaired and must be sold for scraps.");

                itemsScrappedDialogBuilder.create().show();


                defenseRating = 0;
            }
        }
    }

    public String getName(){
        return this.armourName;
    }


    public void repairArmour(double amount) {
        durability += amount;
        if (durability > 100) {
            durability = 100;
        }
    }

    /* Getters & Setters */
    @Override
    public double getDefenseRating() {
        return (double) (defenseRating * (durability / 100.0)) ;
    }

    @Override
    public String toString() {

        if (armourName.equalsIgnoreCase("none")) {

            return armourName + "\n";

        }
        return armourName + ". \nDurability: " + durability + ", Defense Rating: " + defenseRating + "\n";
    }
@Override
    public double getDurability() {
        return this.durability;
    }


    @Override public double getGoldSellValue(){return this.goldSellValue;}

    @Override public double getScrapValue(){return this.scrapValue;}

    @Override public void setGoldSellValue(double goldSellValue){
        this.goldSellValue = goldSellValue;
    }

    @Override public void setScrapValue(double scrapValue){
    this.scrapValue = scrapValue;
    }

    @Override
    public double getDamage(){
    return damage;
}

@Override
    public int getImageId(){
        return imageId;
}



}