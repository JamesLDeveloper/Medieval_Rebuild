package com.example.medievalrebuild.Enemies;

import java.io.Serializable;

public class Enemy implements Serializable {

    private final String name;
    private int damage;
    private int health;


    public Enemy(String name, int health, int damage){

        this.name = name;
        this.damage = damage;
        this.health = health;


    }

    public String getEnemyName(){
        return this.name;
    }

    public int getEnemyDamage(){
        return this.damage;

    }

    public void enemyTakeDamage(double damageTaken){
        double damage = damageTaken;
        this.health -= damageTaken;
    }

    public int getEnemyHealth(){
        return this.health;

    }

}
