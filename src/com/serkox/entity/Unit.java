package com.serkox.entity;

import java.util.UUID;

public class Unit {

    private String id = UUID.randomUUID().toString();
    private int damage;
    private int health;
    private static int goldCost;
    private int level;
    private int speed;
    private Hexagon hexagon;
    private boolean wantMove;

    public Unit(Hexagon p_hexagon){
        this.damage = 15;
        this.health = 50;
        goldCost = 10;
        this.speed = 1;
        this.level = 1;
        this.hexagon = p_hexagon;
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

    public void move(Hexagon p_hexagon){
        this.hexagon = p_hexagon;
        Interface.setMessage("X : " + this.hexagon.getPosX() + " Y : " + this.hexagon.getPosY() + " Id : " + this.hexagon.getId());
    }

    public void attack(Hexagon hexagon){

    }

    public Hexagon getHexagon() {
        return hexagon;
    }

    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
    }

    public boolean isWantMove() {
        return wantMove;
    }

    public void setWantMove(boolean wantMove) {
        this.wantMove = wantMove;
    }
}
