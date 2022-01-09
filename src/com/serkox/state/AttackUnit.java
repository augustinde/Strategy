package com.serkox.state;

import com.serkox.entity.Grid;

public class AttackUnit extends State {

    @Override
    public void enter() {
        if(Grid.getCapitalIa().getHexagonPriority().getUnit() != null) {
            System.out.println("############ATTAQUE UNITE##########");
            System.out.println("Unite qui attaque : " + Grid.getCapitalIa().getUnitToDeplace().getId());
            System.out.println("Unite a attaquer : " + Grid.getCapitalIa().getHexagonPriority().getUnit().getId());
            Grid.getCapitalIa().getUnitToDeplace().attack(Grid.getCapitalIa().getHexagonPriority());
            Grid.getCapitalIa().setState(new AnalyseState());
        }else{
            Grid.getCapitalIa().setState(new AnalyseState());
        }
    }

    @Override
    public void update() {

    }
}
