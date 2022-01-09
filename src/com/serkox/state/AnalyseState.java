package com.serkox.state;

import com.serkox.entity.*;

public class AnalyseState extends State{

    @Override
    public void enter(){

        if(Grid.getCapitalIa().getHealth() > 0 && Grid.getCapitalJoueur().getHealth() > 0) {
            System.out.println("#######");
            System.out.println("J'analyse la situation ..." + Grid.getCapitalIa().getUnitCollection().size());

            if (Grid.getCapitalIa().getUnitCollection().size() == 0 || Grid.getCapitalJoueur().getUnitCollection().size() > Grid.getCapitalIa().getUnitCollection().size()) {

                System.out.println("J'ai besoin d'acheter une unité !");

                if (Grid.getCapitalIa().getCurrentGold() >= PNJ.getUnitGoldCost()) {
                    Grid.getCapitalIa().setState(new BuyUnitState());
                } else {
                    System.out.println("Mais je ne peux pas pour le moment (manque d'or)");
                }

            } else {
                //VERIFIER UNITE DANS LE RADIUS DE LA CAPITAL SI OUI ON PASSE EN MODE DEFENSIVE
                if (Grid.getCapitalIa().checkUnitInRadius()) {
                    if(Grid.getCapitalIa().getHealth() > 0 && Grid.getCapitalIa().getUnitCollection().size() > 0){
                        Grid.getCapitalIa().setState(new DefensiveState());
                    }
                } else {
                    if(Grid.getCapitalJoueur().getHealth() > 0 && Grid.getCapitalIa().getUnitCollection().size() > 0){
                        Grid.getCapitalIa().setState(new OffensiveState());

                    }
                }
            }
        }else{
            System.out.println("Le jeu est finit !");
        }
    }

    @Override
    public void update() {
    }
}
