package com.serkox.state;

import com.serkox.entity.Grid;

public class AttackUnit extends State {

    @Override
    public void enter() {
        System.out.println("############ATTAQUE UNITE##########");
        Grid.getCapitalIa().setState(new AnalyseState());
    }

    @Override
    public void update() {

    }
}
