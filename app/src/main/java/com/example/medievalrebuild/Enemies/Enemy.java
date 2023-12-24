package com.example.medievalrebuild.Enemies;

import java.io.Serializable;
import java.util.ArrayList;

public class Enemy implements Serializable {

    private final String name;
    private int damage;
    private int health;

    private static ArrayList<Enemy> enemiesCreated = new ArrayList<>();


    public Enemy(String name, int health, int damage){

        this.name = name;
        this.damage = damage;
        this.health = health;
        enemiesCreated.add(this);
    }

    public static ArrayList<Enemy> getAllEnemies() {
        return enemiesCreated;
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

}
