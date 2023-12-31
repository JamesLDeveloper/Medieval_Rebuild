package com.example.medievalrebuild.Enemies;

import java.io.Serializable;
import java.util.ArrayList;

public class Enemy implements Serializable {

    private final String name;
    private int damage;
    private int health;

//    private static ArrayList<Enemy> enemiesCreated = new ArrayList<>();

     private static final ArrayList<Enemy> enemiesOriginalStats = new ArrayList<>();


    public Enemy(String name, int health, int damage){

        this.name = name;
        this.damage = damage;
        this.health = health;
        enemiesOriginalStats.add(this);
//        enemiesCreated.add(this);
//        enemiesCreatedFinal.add(clone());
    }

//    public static ArrayList<Enemy> getAllEnemies() {
//        return enemiesCreated;
//    }

    public static ArrayList<Enemy> getEnemiesOriginalStats() {
        return enemiesOriginalStats;
    }

    public String getEnemyName(){
        return this.name;
    }

    public int getEnemyDamage(){
        return this.damage;

    }

    public void enemyTakeDamage(int damage){
        this.health -= damage;
   //     return (this.health);
    }

    public int getEnemyHealth(){
        return this.health;

    }

    public void updateEnemy(Enemy newEnemy){
        this.health = newEnemy.getEnemyHealth();
        this.damage = newEnemy.getEnemyDamage();
    }

//    public static Enemy getOriginalEnemy(Enemy enemyName){
//        int index = enemiesCreated.indexOf(enemyName);
//        return enemiesCreated.get(index);
//    }

    public Enemy getOriginalEnemyStats(Enemy enemyName){
        int index = enemiesOriginalStats.indexOf(enemyName);
        return enemiesOriginalStats.get(index);
    }


    @Override
   public String toString(){
        return "Name: " + getEnemyName() + ". Health: " + getEnemyHealth() + ". Damage: " + getEnemyDamage() + ".";
    }

//    public Enemy clone() {
//        return new Enemy(this.name, this.health, this.damage);
//    }

}
