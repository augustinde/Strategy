package com.serkox.state;

import com.serkox.entity.*;

public class BuyUnitState extends State{

    private Hexagon hexagon;

    @Override
    public void enter(){
        System.out.println("Je veux acheter une unité ! :D");

        for(Hexagon neighbor : Grid.getCapitalIa().getHexagon().getNeighbors()){

            if(neighbor.getUnit() == null){
                System.out.println("Rien ici " + neighbor.getId());
                this.hexagon = neighbor;
                break;
            }
        }

        if(this.hexagon != null){
            Grid.getCapitalIa().placeUnit(this.hexagon);
            Grid.getCapitalIa().setState(new AnalyseState());
        }else{
            Grid.getCapitalIa().setState(new MoveUnitAroundCapital());
        }

    }

    @Override
    public void update() {
    }
}
