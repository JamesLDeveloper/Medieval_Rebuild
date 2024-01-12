package com.example.medievalrebuild.Enemies;

import java.io.Serializable;
import java.util.ArrayList;

public class Enemy implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private int damage;
    private int health;

    private int imageId;

    private boolean original;

//    private static ArrayList<Enemy> enemiesCreated = new ArrayList<>();

    private static final ArrayList<Enemy> enemiesOriginalStats = new ArrayList<>();


    public Enemy(String name, int health, int damage, boolean original, int imageId) {

        if (original) {
            this.name = name;
         } else {
            this.name = name + "NewGame";
        }

        this.damage = damage;
        this.health = health;
        this.original = original;
        this.imageId = imageId;

        enemiesOriginalStats.add(this);
//        enemiesCreated.add(this);
//        enemiesOriginalStats.add(clone(original));

        if (original) {
            new Enemy(this.name, this.health, this.damage, false, this.imageId);
        }

    }

//    public Enemy(String name, int health, int damage, boolean original) {
//        this.damage = damage;
//        this.health = health;
//
//        // Check if it's an original enemy
//        if (original) {
//            this.name = name;
//            this.original = original;  // Add this line to set the 'original' field
//            // Add the original enemy to the list
//            enemiesOriginalStats.add(this);
//
//            // Create a copy with the adjusted name and add it to the list
//            new Enemy(name + "NewGame", this.health, this.damage, false);
//        } else {
//            // If not original, modify the name and add to the list
//            this.name = name + "NewGame";
//            this.original = false;  // Since it's a modified copy, set 'original' to false
//            //enemiesOriginalStats.add(this);
//        }
//    }















//    public static ArrayList<Enemy> getAllEnemies() {
//        return enemiesCreated;
//    }

    public static ArrayList<Enemy> getEnemiesOriginalStats() {
        return enemiesOriginalStats;
    }

    public String getEnemyName() {
        return this.name;
    }

    public int getEnemyDamage() {
        return this.damage;

    }

    public void enemyTakeDamage(int damage) {
        this.health -= damage;
        //     return (this.health);
    }

    public int getEnemyHealth() {
        return this.health;

    }

    public int getImageId(){
        return this.imageId;
    }

    public void setImageId(int imageIdSetter) {
        this.imageId = imageIdSetter;
    }

    public boolean getIsOriginal(){
        return original;
    }

    public void updateEnemy(Enemy newEnemy) {
        this.health = newEnemy.getEnemyHealth();
        System.out.println("Enemy: "+ this.name + " health has been set to " + this.health);
        this.damage = newEnemy.getEnemyDamage();
        System.out.println("Enemy: "+ this.name + " damage has been set to " + this.damage);
    }

//    public static Enemy getOriginalEnemy(Enemy enemyName){
//        int index = enemiesCreated.indexOf(enemyName);
//        return enemiesCreated.get(index);
//    }

    public Enemy getOriginalEnemyStats(Enemy enemyName) {
        int index = enemiesOriginalStats.indexOf(enemyName);
        return enemiesOriginalStats.get(index);
    }


    @Override
    public String toString() {
        return "Name: " + getEnemyName() + ". Health: " + getEnemyHealth() + ". Damage: " + getEnemyDamage() + "." + " Original?: " + original + ".";

    }

    public String enemyStats(){
        return "Name: " + getEnemyName() + ". \nHealth: " + getEnemyHealth() + ". \nDamage: " + getEnemyDamage() + ".";
    }



//    public Enemy clone(boolean isOriginal) {
//
//        if (isOriginal) {
//           return new Enemy(this.name, this.health, this.damage, false);
//        } else {
//             return null;
//        }
//    }

}
