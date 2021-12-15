package com.serkox.entity;

import java.util.UUID;

public class Unit {

    private String id = UUID.randomUUID().toString();
    private int damage;
    private int health;
    private static int goldCost;
    private int level;
    private int speed;

    public Unit(){
        this.damage = 15;
        this.health = 50;
        goldCost = 10;
        this.speed = 1;
        this.level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        goldCost = goldCost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getId() {
        return id;
    }

    public void move(Hexagon hexagon){

    }

    public void attack(Hexagon hexagon){

    }
}
