package com.serkox.state;

import com.serkox.entity.Grid;

public class OffensiveState extends State {
    @Override
    public void enter() {
        System.out.println("ATTAQUE CAPITAL");
        Grid.getCapitalIa().setState(new AnalyseState());
    }

    @Override
    public void update() {

    }
}
