package com.serkox.state;

import com.serkox.entity.*;

public class AnalyseState extends State{

    @Override
    public void enter(){
        System.out.println("#######");
        System.out.println("J'analyse la situation ..." + Grid.getCapitalJoueur().getUnitCollection().size() + " " + Grid.getCapitalIa().getUnitCollection().size());
        System.out.println("IA: " + Grid.getCapitalIa().getCurrentGold() + " Joueur : "+ Grid.getCapitalJoueur().getCurrentGold());

        if(Grid.getCapitalIa().getUnitCollection().size() == 0 || Grid.getCapitalJoueur().getUnitCollection().size() > Grid.getCapitalIa().getUnitCollection().size()){

            System.out.println("J'ai besoin d'acheter une unité !");

            if(Grid.getCapitalIa().getCurrentGold() >= PNJ.getUnitGoldCost()){
                Grid.getCapitalIa().setState(new BuyUnitState());
            }else{
                System.out.println("Mais je ne peux pas pour le moment (manque d'or)");
            }

        }else{
            //VERIFIER UNITE DANS LE RADIUS DE LA CAPITAL SI OUI ON PASSE EN MODE DEFENSIVE
            if(Grid.getCapitalIa().checkUnitInRadius()){
                Grid.getCapitalIa().setState(new DefensiveState());
            }else{
                Grid.getCapitalIa().setState(new OffensiveState());
            }


            //SINON MODE ATTAQUE

            //Avantage IA
          /*  if(Grid.getCapitalIa().getHealth() > Grid.getCapitalJoueur().getHealth()){
                Grid.getCapitalIa().setState(new OffensiveState());

            //Avantage JOUEUR
            }else if(Grid.getCapitalIa().getHealth() < Grid.getCapitalJoueur().getHealth()){
                Grid.getCapitalIa().setState(new DefensiveState());
            }else{
                Grid.getCapitalIa().setState(new BuyUnitState());
            }
            */
        }
    }

    @Override
    public void update() {
    }
}
