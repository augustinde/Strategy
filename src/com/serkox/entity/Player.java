package com.serkox.entity;

import java.util.ArrayList;

public class Player extends Capital{

    public Player(int pId) {
        super();
        id = id;
        health = 150;
        currentGold = 0;
        maxGold = 1000;
        goldPerSec = 2;
        level = 1;
        unitCollection = new ArrayList<Unit>();

    }

    public void addUnit(Unit unit){
        this.unitCollection.add(unit);
    }

    public Unit getUnitToDeplace() {
        return this.unitToDeplace;
    }

    public void setUnitToDeplace(Unit unit) {
        this.unitToDeplace = unit;
    }
}
