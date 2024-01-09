package com.example.medievalrebuild.Game;

import com.example.medievalrebuild.ArmourFiles.*;
import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.MainActivity;
import com.example.medievalrebuild.Weapons.Weapon;
import com.example.medievalrebuild.MyAlertDialog;

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

    private Equipable armour;
    private Equipable helmet;
    private Equipable shirt;
    private Equipable trouser;
    private Equipable shoe;

    private int strength;

    private int maxHealth;

    private int playerGold;

    private String progress;

    //implement a max health variable?

    /* Constructors */
    public Player(String name, MainActivity mainActivity) {
        this.name = name;
        this.currentWeapon = new Weapon("Rusty Short Sword", 3);
        this.health = 100;
        this.maxHealth = 100;
        this.strength = 5;
        this.playerGold = 100;
        this.armour = new Armour("None", 0, 0);
        this.helmet = new Helmet("None", 0, 0);
        this.shirt = new Shirt("None", 0, 0);
        this.trouser = new Trouser("None", 0, 0);
        this.shoe = new Shoe("None", 0, 0);
        this.mainActivity = mainActivity;
        this.progress = "level1";
    }


    /* Instance Methods */
    public void takeDamage(double enemyAttack) {
        double damage = enemyAttack - (getDefenseRating() / 5.0);
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("You have succumbed to the enemy, better luck next time.");
            //System.exit(1);

            if (mainActivity != null){
                mainActivity.chooseNewOrLoad();
            } else {

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


    public int getMaxHealth(){
        return this.maxHealth;
    }

    public void setMaxHealth(int newMaxHealth){
        this.maxHealth = newMaxHealth;
    }

    //could change to if this.health > max health.

    /* Getters & Setters */
    public String getName() {
        return name;
    }

    public int getCurrentWeaponDamage(){

        return currentWeapon.getDamage();
    }

    public String getCurrentWeaponName(){

        return currentWeapon.getName();
    }

    public String getCurrentWeapon() {
        return "Currently wielding: " +
                currentWeapon.getName() +
                ". \n This weapon does " +
                currentWeapon.getDamage() +
                " damage.\n";
    }

    public void setCurrentWeapon(Equipable currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public double getHealth() {
        return this.health;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getPlayerGold(){
        return this.playerGold;
    }

    public void addStrength(int strengthToAdd){
        this.strength += strengthToAdd;
    }

    public void subtractStrength(int strengthToSubtract)
    {
        this.strength -= strengthToSubtract;
    }
    public void addPlayerGold(int addGold){
        this.playerGold += addGold;
    }

    public void subtractGold(int subtractGold){
        this.playerGold -= subtractGold;
    }


    public int getDefenseRating() {
        return
                armour.getDefenseRating() +
                helmet.getDefenseRating() +
                shirt.getDefenseRating() +
                trouser.getDefenseRating() +
                shoe.getDefenseRating();
    }

    public Equipable getHelmet() {
        return helmet;
    }

    public Equipable getArmour() {        return armour;    }
    public void setHelmet(Equipable helmet) {
        this.helmet = helmet;
    }

    public void setArmour(Equipable armour) {
        this.armour = armour;
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
                "Name: " + name + "\n" +
                "Health: " + getHealth() + "\n" +
                "Strength: " + getStrength() + "\n" +
                "Gold: " + getPlayerGold() + "\n" +
                getCurrentWeapon() +
                "Equipped Items: \nArmour: " + armour +
                "Helmet: " + helmet +
                "Shirt: " + shirt +
                "Trousers: " + trouser +
                "Shoes: " + shoe +
                "Level: " + progress;
    }
}