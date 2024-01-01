package com.example.medievalrebuild.Enemies;

import java.io.Serializable;

public class BossEnemy extends Enemy implements Serializable {

    private static final long serialVersionUID = 1L;

    int reduceDurability;


    public BossEnemy(String name, int health, int damage, boolean original, int reduceDurability) {

        super(name, health, damage, original);

        this.reduceDurability = reduceDurability;

    }

    public int getReduceDurability(){

        return this.reduceDurability;

    }

    @Override
    public void updateEnemy(Enemy newEnemy) {
        super.updateEnemy(newEnemy);
        if (newEnemy instanceof BossEnemy) {
            this.reduceDurability = ((BossEnemy) newEnemy).reduceDurability;
        }
    }

    @Override
    public String toString() {
        return "Name: " + getEnemyName() + ". Health: " + getEnemyHealth() + ". Damage: " + getEnemyDamage() + ". Reduce Durability: " + getReduceDurability() + "." + "Original?: " + getIsOriginal() + ".";
    }


}
