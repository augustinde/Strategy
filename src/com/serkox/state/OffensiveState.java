package com.serkox.state;

import com.serkox.entity.Grid;

public class OffensiveState extends State {
    @Override
    public void enter() {
        System.out.println("ATTAQUE CAPITAL");

        //Récupérer l'unité la plus éloigné de la capital
        if(Grid.getCapitalIa().getUnitCollection().size() > 0) {
            Grid.getCapitalIa().getUnitFarFromCapital();
            if (Grid.getCapitalJoueur().getHexagon().getDistanceCapital() == 1) {
                Grid.getCapitalIa().setState(new AttackCapitalState());
            } else {

                Grid.getCapitalIa().getUnitToDeplace().pathToCapitalIa(Grid.getCapitalJoueur().getHexagon());
                Grid.getCapitalIa().getUnitToDeplace().moveToCapitalIa(Grid.getCapitalJoueur().getHexagon());
                Grid.getCapitalIa().setState(new AnalyseState());
            }
        }else{
            Grid.getCapitalIa().setState(new AnalyseState());
        }


    }

    @Override
    public void update() {

    }
}
