package com.example.medievalrebuild.Game;

import android.app.AlertDialog;

import com.example.medievalrebuild.ArmourFiles.*;
import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.HandHeldItems.Shield;
import com.example.medievalrebuild.MainActivity;
import com.example.medievalrebuild.MyApplication;
import com.example.medievalrebuild.R;
import com.example.medievalrebuild.HandHeldItems.Weapon;

import java.io.Serializable;

public class Player implements Serializable {

    /* Class Variables */
    private static final long serialVersionUID = 1L;

    /* Instance Variables */

    //MedievalGame game;
    private final String name;
    private double health;

    private transient MainActivity mainActivity;

    private Equipable currentWeapon;

    private Equipable shield;
    private Equipable chestArmour;
    private Equipable helmet;
    private Equipable shirt;
    private Equipable trouser;
    private Equipable shoe;

    private double strength;

    private double maxHealth;

    private double playerGold;

    private double accuracy;

    private double speed;

    private double reactions;

    private double intelligence;

    private String progress;

    //implement a max health variable?

    /* Constructors */
    public Player(String name, MainActivity mainActivity) {
        this.name = name;
        this.currentWeapon = new Weapon("Rusty Short Sword", 3, 5, 1, 1, R.drawable.rustysword);
        this.health = 100;
        this.maxHealth = 100;
        this.strength = 5;
        this.playerGold = 500;
        this.accuracy = 5;
        this.speed = 5;
        this.reactions = 5;
        this.intelligence = 5;
        this.shield = new Shield("Shield: None", 0, 0,0, 0,0, 1, R.drawable.none);
        this.chestArmour = new ChestArmour("Chest Armour: None", 0, 0,0,0, R.drawable.none);
        this.helmet = new Helmet("Helmet: None", 0, 0,0,0, R.drawable.none);
        this.shirt = new Shirt("Shirt: None", 0, 0, 0,0, R.drawable.none);
        this.trouser = new Trouser("Trousers: None", 0, 0,0,0, R.drawable.none);
        this.shoe = new Shoe("Shoes: None", 0, 0,0,0, R.drawable.none);
        this.mainActivity = mainActivity;
        this.progress = "level1";
    }


    /* Instance Methods */
    public void takeDamage(double enemyAttack) {
        double damage = enemyAttack - (getDefenseRating() / 5.0);
        this.health -= damage;

        if (this.health <= this.maxHealth * 0.3){
            if (mainActivity != null && mainActivity.getPlayer() != null) {
                mainActivity.lowHealthMessage();
            }

        }

        if (this.health <= 0) {
            System.out.println("You have succumbed to the enemy, better luck next time.");

            mainActivity = MyApplication.getMainActivityInstance();
            //System.exit(1);

            if (mainActivity == null){
                System.out.println("mainActivity = null");
            } else {
                System.out.println("mainActivity != null");
            }


            if (mainActivity != null){
                mainActivity.chooseNewOrLoad();
            }


        } else {
            System.out.println("Current Health: " + this.health);
        }
    }

    public void heal(double healthToAdd) {
        this.health += healthToAdd;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }




    public double getMaxHealth(){
        return this.maxHealth;
    }

    public void setMaxHealth(double newMaxHealth){
        this.maxHealth = newMaxHealth;
    }

    //could change to if this.health > max health.

    /* Getters & Setters */
    public String getName() {
        return name;
    }

    public double getCurrentWeaponDamage(){

        return currentWeapon.getDamage() * this.strength;
    }

    public String getCurrentWeaponName(){

        return currentWeapon.getName();
    }

    public String getCurrentWeapon() {
        return "Currently wielding: \n" +
                currentWeapon.getName() +
                ". \nThis weapon does " +
                currentWeapon.getDamage() +
                " damage.\n";
    }

    public void setCurrentWeapon(Equipable currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public double getHealth() {
        return this.health;
    }

    public double getStrength(){
        return this.strength;
    }

    public double getPlayerGold(){
        return this.playerGold;
    }

    public void addStrength(double strengthToAdd){
        this.strength += strengthToAdd;
    }

    public void subtractStrength(double strengthToSubtract)
    {
        this.strength -= strengthToSubtract;
    }
    public void addPlayerGold(double addGold){
        this.playerGold += addGold;
    }

    public void subtractGold(double subtractGold){
        this.playerGold -= subtractGold;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getSpeed() {
        return speed;
    }

    public double getReactions() {
        return reactions;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public void setReactions(double reactions) {
        this.reactions = reactions;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDefenseRating() {
        return
                chestArmour.getDefenseRating() +
                helmet.getDefenseRating() +
                shirt.getDefenseRating() +
                trouser.getDefenseRating() +
                shoe.getDefenseRating();
    }

    public Equipable getWeapon(){ return currentWeapon;}

    public Equipable getShield(){ return shield;}

    public void setShield(Equipable shield){
        this.shield = shield;
    }

    public Equipable getChestArmour() {        return chestArmour;    }
    public void setChestArmour(Equipable chestArmour) {
        this.chestArmour = chestArmour;
    }


    public Equipable getHelmet() {
        return helmet;
    }


    public void setHelmet(Equipable helmet) {
        this.helmet = helmet;
    }


    public Equipable getShirt() {
        return shirt;
    }

    public void setShirt(Equipable shirt) {
        this.shirt = shirt;
    }

    public Equipable getTrouser() {
        return trouser;
    }

    public void setTrouser(Equipable trouser) {
        this.trouser = trouser;
    }

    public Equipable getShoe() {
        return shoe;
    }

    public void setShoe(Equipable shoe) {
        this.shoe = shoe;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void equipItem(Equipable item) {

    }

    @Override
    public String toString() {
        return "\nCurrent Player: \n" +
                "Name: " + name + "\n\n" +
                "Health: " + getHealth() + "\n\n" +
                "Strength: " + getStrength() + "\n\n" +
                "Gold: " + getPlayerGold() + "\n\n" +
                getCurrentWeapon() +
                "\nEquipped Items: \nArmour: " + chestArmour +
                "\nHelmet: " + helmet +
                "\nShirt: " + shirt +
                "\nTrousers: " + trouser +
                "\nShoes: " + shoe +
                "\nLevel: " + progress;
    }
}