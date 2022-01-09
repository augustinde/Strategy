package com.serkox.entity;

import com.serkox.state.State;
import com.serkox.state.AnalyseState;

import java.util.ArrayList;

public class PNJ extends Capital{
    private State state;

    public PNJ(int id) {
        super();
        id = id;
        health = 150;
        currentGold = 0;
        maxGold = 1000;
        goldPerSec = 2;
        level = 1;
        unitCollection = new ArrayList<Unit>();

        this.state = new AnalyseState();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    state.enter();
                    state.update();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }



    public void addUnit(Unit unit){
        this.unitCollection.add(unit);
    }

    public void setUnitToDeplace(Unit unit){
        this.unitToDeplace = unit;
    }

    public Unit getUnitToDeplace() {
        return this.unitToDeplace;
    }

    public void placeUnit(Hexagon p_hexagon){

            System.out.println("J'achète et je place une unité sur l'hexagon " + p_hexagon.getId());
            Unit unit = new Unit(p_hexagon);
            p_hexagon.setUnit(unit);
            Grid.getCapitalIa().addUnit(unit);
            Grid.getCapitalIa().setCurrentGold(Grid.getCapitalIa().getCurrentGold() - Unit.getGoldCost());
            if(Grid.getCapitalIa().getUnitToDeplace() == null){
                Grid.getCapitalIa().setUnitToDeplace(unit);
            }
            System.out.println(Grid.getCapitalIa().getUnitToDeplace());

    }

}
