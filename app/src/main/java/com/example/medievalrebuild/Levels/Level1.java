package com.example.medievalrebuild.Levels;//
//
//import java.util.Scanner;
//import java.util.Objects;
//import java.io.Serializable;
//import java.io.*;
//import java.io.IOException;
//
//
//public class Level1 extends Game.MedievalGame implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    Game.MedievalGame game;
//
//    Game.Player player;
//
//    private Enemies.Enemy enemy;
//
//    private String progress;
//
//    private   String chest;
//    private  String chestTwo;
//
//    private String doorOne;
//    private   String zombie;
//
//    private String enemy2;
//
//    private String enemy3;
//    private  int zombieHealth = 4;
//    private  int zombieDamage = 2;
//
//    Scanner console = new Scanner(System.in);
//
//    Enemies.Enemy zombieKing = new Enemies.Enemy("Zombie King", 20, 20);
//
//    Enemies.BossEnemy loki = new Enemies.BossEnemy("Loki God Of Mischeif", 35,25, 4);
//
//
//
//
//
//
//public void level1Start() {
//    game.addDelay(2000);
//    System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
//    String chest = console.next().toLowerCase();
//
//    while (true) {
//        if (chest.equalsIgnoreCase("y")) {
//            System.out.println("You open the chest to find a helmet. You put it on.");
////                ArmourFiles.Helmet helmet = new ArmourFiles.Helmet("none", 1, 0);
//            ArmourFiles.Helmet platedHelmet = new ArmourFiles.Helmet("Plated ArmourFiles.Helmet", 5, 8);
//            /* helmet = platedHelmet; */
//                /* to use non-static methods from a static method (e.g. main) you must call the objects (e.g.game.player), then call the method (e.g. setHelmet)
//                then call the argument for the method (e.g. platedHelmet which in this case is an object of the non-static ArmourFiles.Helmet class */
//            game.player.setHelmet(platedHelmet);
//            System.out.println(game.player);
//            game.addDelay(2000);
//            System.out.println("\nYou discover a Zombie. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
//            game.zombie = console.next().toLowerCase();
//            break;
//        } else if (chest.equalsIgnoreCase("n")) {
//            System.out.println("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");
//            ArmourFiles.Shoe leatherboots = new ArmourFiles.Shoe("Leather Boots", 10, 10);
//            game.player.setShoe(leatherboots);
//            System.out.println(game.player);
//            System.out.println("\nYou discover a Zombie. The Zombie has " + game.zombieHealth + " health and " + game.zombieDamage + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
//            game.zombie = console.next().toLowerCase();
//            break;
//
//        } else if (chest.equalsIgnoreCase("s")) {
//            game.save();
//            System.out.println("We just saved your game...");
//            break;
//        } else if (chest.equalsIgnoreCase("x")) {
//            System.exit(0);
//            break;
//        } else {
//
//            System.out.println("Please try again, your options are y or n to open the chest.");
//            chest = console.next().toLowerCase();
//        }
//    }
//
//    while (true) {
//        if (game.zombie.equalsIgnoreCase("y")) {
//            System.out.println("\nYou attack the zombie");
//
////                ArmourFiles.Helmet helmet = new ArmourFiles.Helmet("none", 1, 0);
////                ArmourFiles.Helmet platedHelmet = new ArmourFiles.Helmet("Plated ArmourFiles.Helmet", 5, 8);
//            /* helmet = platedHelmet; */
//                /* to use non-static methods from a static method (e.g. main) you must call the objects (e.g.game.player), then call the method (e.g. setHelmet)
//                then call the argument for the method (e.g. platedHelmet which in this case is an object of the non-static ArmourFiles.Helmet class */
//            int currentWeaponDamage = game.player.getCurrentWeaponDamage();
//            while (true) {
//                if (currentWeaponDamage >= game.zombieHealth) {
////                System.out.println(game.player);
//                    game.addDelay(2000);
//                    game.zombieHealth -= currentWeaponDamage;
//                    System.out.println("You have killed the Zombie and taken no damage.");
//                    Weapons.Weapon longSword = new Weapons.Weapon("Long Sword", 12);
//                    game.player.setCurrentWeapon(longSword);
//                    System.out.println("The Zombie was carrying a Long Sword which you claim as your own.");
//                    System.out.println(game.player);
//                    game.save();
//                    System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
//                    game.chestTwo = console.next().toLowerCase();
////                        zombie.equalsIgnoreCase("s");
////                        System.exit(1);
//                    break;
////                        System.out.println("\nYou discover a Door. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
////                String zombie = console.next().toLowerCase();
//                } else {
//                    game.addDelay(2000);
//                    System.out.println("\nYou have damaged the Zombie");
//                    game.zombieHealth -= currentWeaponDamage;
//                    System.out.println("The Zombie now has " + game.zombieHealth + " health.");
//                    System.out.println("\nThe Zombie has attacked you with " + game.zombieDamage + " damage.");
//                    game.player.takeDamage(game.zombieDamage);
////                        break;
//
//                }
////                    break;
//            }
//            break;
//        } else if (game.zombie.equalsIgnoreCase("n")) {
//            System.out.println("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
//            game.player.takeDamage(game.zombieDamage * 1.5);
//            System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
//            game.chestTwo = console.next().toLowerCase();
//            break;
//
//        } else if (game.zombie.equalsIgnoreCase("s")) {
//            game.save();
//            System.out.println("We just saved your game...");
//            break;
//        } else if (game.zombie.equalsIgnoreCase("x")) {
//            System.exit(0);
//            break;
//        } else {
//
//            System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
//            game.zombie = console.next().toLowerCase();
//        }
//    }
//
//
//    while (true) {
//        if (game.chestTwo.equalsIgnoreCase("y")) {
//            game.addDelay(2000);
//            System.out.println("You open the chest to find some chain mail. You put it on.");
////                ArmourFiles.Helmet helmet = new ArmourFiles.Helmet("none", 1, 0);
//            ArmourFiles.Armour chainMail = new ArmourFiles.Armour("Chain Mail", 10, 10);
//            /* helmet = platedHelmet; */
//                /* to use non-static methods from a static method (e.g. main) you must call the objects (e.g.game.player), then call the method (e.g. setHelmet)
//                then call the argument for the method (e.g. platedHelmet which in this case is an object of the non-static ArmourFiles.Helmet class */
//            game.player.setArmour(chainMail);
//            System.out.println(game.player);
//            game.addDelay(2000);
//            System.out.println("\nYou discover a " + game.zombieKing.getEnemyName() + " .The Zombie King has " + game.zombieKing.getEnemyHealth() + " health and " + game.zombieKing.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
//            game.enemy2 = console.next().toLowerCase();
//            break;
//        } else if (game.chestTwo.equalsIgnoreCase("n")) {
//            System.out.println("You choose not to open the chest. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
//            ArmourFiles.Armour platedArmor = new ArmourFiles.Armour("Plated Armor", 7, 6);
//            game.player.setArmour(platedArmor);
//            System.out.println(game.player);
//
//            System.out.println("\nYou discover a " + game.zombieKing.getEnemyName() + " .The Zombie King has " + game.zombieKing.getEnemyHealth() + " health and " + game.zombieKing.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
//            game.enemy2 = console.next().toLowerCase();
//            break;
//
//        } else if (game.chestTwo.equalsIgnoreCase("s")) {
//            game.save();
//            System.out.println("We just saved your game...");
//            break;
//        } else if (game.chestTwo.equalsIgnoreCase("x")) {
//            System.exit(0);
//            break;
//        } else {
//
//            System.out.println("Please try again, your options are y or n to open the chest.");
//            game.chestTwo = console.next().toLowerCase();
//        }
//    }
//
//
//    while (true) {
//        if (game.enemy2.equalsIgnoreCase("y")) {
//            System.out.println("\nYou attack the " + game.zombieKing.getEnemyName());
//
////                ArmourFiles.Helmet helmet = new ArmourFiles.Helmet("none", 1, 0);
////                ArmourFiles.Helmet platedHelmet = new ArmourFiles.Helmet("Plated ArmourFiles.Helmet", 5, 8);
//            /* helmet = platedHelmet; */
//                /* to use non-static methods from a static method (e.g. main) you must call the objects (e.g.game.player), then call the method (e.g. setHelmet)
//                then call the argument for the method (e.g. platedHelmet which in this case is an object of the non-static ArmourFiles.Helmet class */
//            int currentWeaponDamage = game.player.getCurrentWeaponDamage();
//
//            int zombieKingHealth = game.zombieKing.getEnemyHealth();
//            while (true) {
//
//                if (currentWeaponDamage >= zombieKingHealth) {
////                System.out.println(game.player);
//                    game.addDelay(2000);
//                    zombieKingHealth -= currentWeaponDamage;
//                    System.out.println("You have killed the " + game.zombieKing.getEnemyName() + " and taken no damage.");
//                    System.out.println("The " + game.zombieKing.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of chain mail trousers.");
//                    ArmourFiles.Trouser chainMailTrousers = new ArmourFiles.Trouser("Chain Mail Trousers", 10, 10);
//                    game.player.setTrouser(chainMailTrousers);
//                    System.out.println(game.player);
//                    game.save();
//                    System.out.println("\nYou discover a door. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
//                    game.doorOne = console.next().toLowerCase();
////                        zombie.equalsIgnoreCase("s");
////                        System.exit(1);
//                    break;
////                        System.out.println("\nYou discover a Door. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
////                String zombie = console.next().toLowerCase();
//                } else {
//                    game.addDelay(2000);
//                    System.out.println("\nYou have damaged the " + game.zombieKing.getEnemyName());
//                    zombieKingHealth -= currentWeaponDamage;
//                    System.out.println("The " + game.zombieKing.getEnemyName() + " now has " + zombieKingHealth + " health.");
//                    System.out.println("\nThe Zombie has attacked you with " + game.zombieKing.getEnemyDamage() + " damage.");
//                    game.player.takeDamage(game.zombieKing.getEnemyDamage());
////                        break;
//
//                }
////                    break;
//            }
//            break;
//        } else if (game.enemy2.equalsIgnoreCase("n")) {
//            System.out.println("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
//            game.player.takeDamage(game.zombieKing.getEnemyDamage() / 2);
//            System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
//            game.doorOne = console.next().toLowerCase();
//            break;
//
//        } else if (game.enemy2.equalsIgnoreCase("s")) {
//            game.save();
//            System.out.println("We just saved your game...");
//            break;
//        } else if (game.enemy2.equalsIgnoreCase("x")) {
//            System.exit(0);
//            break;
//        } else {
//
//            System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
//            game.enemy2 = console.next().toLowerCase();
//        }
//    }
//
//    while (true) {
//        if (game.doorOne.equalsIgnoreCase("y")) {
//            game.addDelay(2000);
//            System.out.println("You open the door to find some a leather jacket. You put it on.");
////                ArmourFiles.Helmet helmet = new ArmourFiles.Helmet("none", 1, 0);
//            ArmourFiles.Shirt leatherJacket = new ArmourFiles.Shirt("Leather Jacket", 3, 3);
//            /* helmet = platedHelmet; */
//                /* to use non-static methods from a static method (e.g. main) you must call the objects (e.g.game.player), then call the method (e.g. setHelmet)
//                then call the argument for the method (e.g. platedHelmet which in this case is an object of the non-static ArmourFiles.Helmet class */
//            game.player.setShirt(leatherJacket);
//            System.out.println(game.player);
//            game.addDelay(2000);
//            System.out.println("\nYou discover " + game.loki.getEnemyName() + " .He has " + game.loki.getEnemyHealth() + " health and " + game.loki.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
//            game.enemy3 = console.next().toLowerCase();
//            break;
//        } else if (game.doorOne.equalsIgnoreCase("n")) {
//            System.out.println("You choose not to open the door. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
//            ArmourFiles.Armour platedArmor = new ArmourFiles.Armour("Plated Armor", 7, 6);
//            game.player.setArmour(platedArmor);
//            System.out.println(game.player);
//
//            System.out.println("\nYou discover " + game.loki.getEnemyName() + " . He has " + game.loki.getEnemyHealth() + " health and " + game.loki.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
//            game.enemy3 = console.next().toLowerCase();
//            break;
//
//        } else if (game.doorOne.equalsIgnoreCase("s")) {
//            game.save();
//            System.out.println("We just saved your game...");
//            break;
//        } else if (game.doorOne.equalsIgnoreCase("x")) {
//            System.exit(0);
//            break;
//        } else {
//
//            System.out.println("Please try again, your options are y or n to open the chest.");
//            game.doorOne = console.next().toLowerCase();
//        }
//    }
//
//    while (true) {
//        if (game.enemy3.equalsIgnoreCase("y")) {
//            System.out.println("\nYou attack " + game.loki.getEnemyName());
//
////                ArmourFiles.Helmet helmet = new ArmourFiles.Helmet("none", 1, 0);
////                ArmourFiles.Helmet platedHelmet = new ArmourFiles.Helmet("Plated ArmourFiles.Helmet", 5, 8);
//            /* helmet = platedHelmet; */
//                /* to use non-static methods from a static method (e.g. main) you must call the objects (e.g.game.player), then call the method (e.g. setHelmet)
//                then call the argument for the method (e.g. platedHelmet which in this case is an object of the non-static ArmourFiles.Helmet class */
//            int currentWeaponDamage = game.player.getCurrentWeaponDamage();
//
//            int lokiHealth = game.loki.getEnemyHealth();
//            while (true) {
//
//                if (currentWeaponDamage >= lokiHealth) {
////                System.out.println(game.player);
//                    game.addDelay(2000);
//                    lokiHealth -= currentWeaponDamage;
//                    System.out.println("You have killed " + game.loki.getEnemyName() + " and taken no damage.");
//                    ArmourFiles.Trouser chainMailTrousers = new ArmourFiles.Trouser("Chain Mail Trousers", 10, 10);
//                    Weapons.Weapon flamingSword = new Weapons.Weapon("Flaming Sword", 24);
//                    System.out.println(game.loki.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of " + chainMailTrousers.getArmourName() + "and a " + flamingSword.getName() + ".");
//                    game.player.setCurrentWeapon(flamingSword);
//                    game.player.setTrouser(chainMailTrousers);
//                    System.out.println(game.player);
//                    game.save();
////                        System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
////                        game.chestThree = console.next().toLowerCase();
////                        zombie.equalsIgnoreCase("s");
//                    System.exit(1);
//                    break;
////                        System.out.println("\nYou discover a Door. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
////                String zombie = console.next().toLowerCase();
//                } else {
//                    game.addDelay(2000);
//                    System.out.println("\nYou have damaged the " + game.loki.getEnemyName());
//                    lokiHealth -= currentWeaponDamage;
//                    System.out.println("The " + game.loki.getEnemyName() + " now has " + lokiHealth + " health.");
//                    System.out.println("\n" + game.loki.getEnemyName() + "has attacked you with " + game.loki.getEnemyDamage() + " damage.");
//                    game.player.getArmour().reduceDurability(game.loki.getReduceDurability());
//                    game.player.getShirt().reduceDurability(game.loki.getReduceDurability());
//                    game.player.getShoe().reduceDurability(game.loki.getReduceDurability());
//                    game.player.getHelmet().reduceDurability(game.loki.getReduceDurability());
//                    game.player.getTrouser().reduceDurability(game.loki.getReduceDurability());
//                    System.out.println(game.player);
//                    game.player.takeDamage(game.loki.getEnemyDamage());
////                        break;
//
//                }
////                    break;
//            }
//            break;
//        } else if (game.enemy3.equalsIgnoreCase("n")) {
//            System.out.println("You choose not to attack. " + game.loki.getEnemyName() + " attacks you in the back as you run away.");
//            game.player.takeDamage(game.loki.getEnemyDamage() / 2);
//            System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
//            game.doorOne = console.next().toLowerCase();
//            break;
//
//        } else if (game.enemy3.equalsIgnoreCase("s")) {
//            game.save();
//            System.out.println("We just saved your game...");
//            break;
//        } else if (game.enemy3.equalsIgnoreCase("x")) {
//            System.exit(0);
//            break;
//        } else {
//
//            System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
//            game.enemy2 = console.next().toLowerCase();
//        }
//    }
//
//}
//
//}
