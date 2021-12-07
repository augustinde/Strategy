package com.serkox.entity;

import java.util.ArrayList;
import java.util.TimerTask;

public class Capital {

    private int id;
    private int health;
    private int level;
    private static int currentGold;
    private int maxGold;
    private int goldPerSec;
    private ArrayList<Unit> unitCollection;
    private Texture texture;

    public Capital(int id) {
        this.id = id;
        this.health = 150;
        currentGold = 0;
        this.maxGold = 1000;
        this.goldPerSec = 2;
        this.level = 1;
        this.unitCollection = new ArrayList<Unit>();
    }

    public ArrayList<Unit> getUnitCollection() {
        return unitCollection;
    }

    public void addUnit(Unit unit){
        this.unitCollection.add(unit);
    }

    public void setUnitCollection(ArrayList<Unit> unitCollection) {
        this.unitCollection = unitCollection;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int getCurrentGold() {
        return currentGold;
    }

    public static void setCurrentGold(int pCurrentGold) {
        currentGold = pCurrentGold;
    }

    public void addCurrentGold(int gold){
        if(this.currentGold < this.maxGold)
            this.currentGold += gold;
    }

    public int getGoldPerSec() {
        return goldPerSec;
    }

    public void setGoldPerSec(int goldPerSec) {
        this.goldPerSec = goldPerSec;
    }

    public int getMaxGold() {
        return maxGold;
    }

    public void setMaxGold(int maxGold) {
        this.maxGold = maxGold;
    }
}