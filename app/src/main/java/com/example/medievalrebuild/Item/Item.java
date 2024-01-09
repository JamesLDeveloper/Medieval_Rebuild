package com.example.medievalrebuild.Item;

import java.io.Serializable;

public interface Item extends Serializable {

    public String getItemName();

    public double getDamage();

    public void setDamage(double damage);

    public void setHealAmount(double healAmount);

    public double getHealAmount();

    public int getStrengthAmount();

    public void setStrengthAmount(int strengthAmount);

    public void setCriticalChance(double criticalChance);

    public double getCriticalChance();

    public void setDodgeChance(double dodgeChance);

    public double getDodgeChance();

    public int getMaxHealth();

    public void setMaxHealth(int maxHealthAmount);

    public boolean getUseOnAcquisition();

    public  void setUseOnAcquisition(boolean useOnPickup);

    }




