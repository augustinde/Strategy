package com.serkox.state;

import com.serkox.entity.Grid;

public class AttackCapitalState extends State {

    @Override
    public void enter() {
        System.out.println("############ATTAQUE CAPITAL##########");
        System.out.println("Unite qui attaque : " + Grid.getCapitalIa().getUnitToDeplace().getId());
        System.out.println("Capital a attaquer : " + Grid.getCapitalJoueur().getHexagon().getId());
        Grid.getCapitalIa().getUnitToDeplace().attack(Grid.getCapitalJoueur().getHexagon());
        Grid.getCapitalIa().setState(new AnalyseState());
    }

    @Override
    public void update() {

    }
}
