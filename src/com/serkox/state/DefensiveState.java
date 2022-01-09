package com.serkox.state;

import com.serkox.entity.Grid;

public class DefensiveState extends State {
    @Override
    public void enter() {
        System.out.println("DEFEND CAPITAL");

        //si la distance entre l'hexagone prioritaire et l'unité est = 1 on attack, sinon on se déplace
        //System.out.println("Hexagon prioritaire : " + Grid.getCapitalIa().getHexagonPriority().getId());

        Grid.getCapitalIa().getUnitNearbyPriorityHexagon();

        if(Grid.getCapitalIa().getHexagonPriority().getDistancePriorityHexagon() == 1){
            Grid.getCapitalIa().setState(new AttackUnitState());

        }else{
            Grid.getCapitalIa().getUnitToDeplace().pathToHexagonPriorityIa(Grid.getCapitalIa().getHexagonPriority());
            Grid.getCapitalIa().getUnitToDeplace().moveToDestinationPriorityHexagonIa(Grid.getCapitalIa().getHexagonPriority());
            Grid.getCapitalIa().setState(new AnalyseState());

        }

    }

    @Override
    public void update() {

    }
}
