package com.serkox.state;

import com.serkox.entity.Grid;
import com.serkox.entity.Hexagon;

public class DefensiveState extends State {
    @Override
    public void enter() {
        System.out.println("DEFEND CAPITAL");

        //si la distance entre l'hexagone prioritaire et l'unité est = 1 on attack, sinon on se déplace
        System.out.println("test : " + Grid.getCapitalIa().getUnitToDeplace());
        if(Grid.getCapitalIa().getUnitToDeplace().getHexagon().getDistancePriorityHexagon() == 1){
            Grid.getCapitalIa().setState(new AttackUnit());
        }else{
            //Récupérer l'unité la plus proche de l'hexagon prioritaire puis la déplacer vers l'hexagon
            Grid.getCapitalIa().getUnitNearbyPriorityHexagon();
            Grid.getCapitalIa().getUnitToDeplace().pathToHexagonPriorityIa(Grid.getCapitalIa().getHexagonPriority());
            Grid.getCapitalIa().getUnitToDeplace().moveToDestinationPriorityHexagonIa(Grid.getCapitalIa().getHexagonPriority());

            Grid.getCapitalIa().setState(new AnalyseState());
            System.out.println("test : " + Grid.getCapitalIa().getUnitToDeplace());


        }











/*
            if(Grid.getCapitalIa().getUnitToDeplace().getHexagon().getDistancePriorityHexagon() > 1) {

                for (Hexagon hexagon : Grid.getCapitalIa().getHexagonsInRadius()) {
                    //Récupération de l'hexagon prioritaire (dans cet état, l'hexagon comportant une unité du joueur sera prioritaire)

                    if (hexagon.getPriority() > 10) {
                        Grid.getCapitalIa().setHexagonPriority(hexagon);
                    }

                }
            }else{
                Grid.getCapitalIa().setState(new AttackUnit());
            }
*/


            /*
            VERIFIER EN PREMIER SI UN HEXA EST PRIORITAIRE PUIS ENSUITE SA DISTANCE
             */







    }

    @Override
    public void update() {

    }
}
