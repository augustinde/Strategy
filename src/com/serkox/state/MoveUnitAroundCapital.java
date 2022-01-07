package com.serkox.state;

import com.serkox.entity.*;

import java.util.ArrayList;
import java.util.List;

public class MoveUnitAroundCapital extends State{

    @Override
    public void enter(){
        boolean move = false;
        for (Hexagon neighbor : Grid.getCapitalIa().getHexagon().getNeighbors()) {

            //Unité présent sur l'hexagon && L'unité peut se déplacer
            if (neighbor.getUnit() != null && neighbor.getUnit().calculDistanceUnitIa() != 0) {

                Grid.getCapitalIa().setUnitToDeplace(neighbor.getUnit());
                List<Hexagon> hexagons = neighbor.getUnit().getScope();

                Hexagon hexagonOptimal = neighbor.getUnit().getHexagon();

                for (Hexagon hexagon : hexagons) {
                    if (hexagon.getDistanceBetweenCapitalJoueur() < hexagonOptimal.getDistanceBetweenCapitalJoueur()) {
                        hexagonOptimal = hexagon;
                    }
                }

                neighbor.getUnit().pathIa(hexagonOptimal);
                neighbor.getUnit().moveToDestinationIa(hexagonOptimal);

                Grid.getCapitalIa().setState(new AnalyseState());
                move = true;
                break;

            }
        }

        if(!move) {
            System.out.println("Aucune possibilité");
            Grid.getCapitalIa().setState(new AnalyseState());
        }
    }


    @Override
    public void update() {
    }


}
