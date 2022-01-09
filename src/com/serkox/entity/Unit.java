package com.serkox.entity;

import javax.swing.*;
import java.util.*;

public class Unit {

    private String id = UUID.randomUUID().toString();
    private int damage;
    private int health;
    private static int goldCost;
    //private int level;
    //private int speed;
    private Hexagon hexagon;
    private boolean wantMove;
    private List<Hexagon> path;
    private int radius;
    private boolean deplace;
    private int priorityLevel;
    private List<Hexagon> scope;

    public Unit(Hexagon p_hexagon){
        this.damage = 15;
        this.health = 50;
        goldCost = 100;
       // this.speed = 1;
        //this.level = 1;
        this.hexagon = p_hexagon;
        this.path = new ArrayList<Hexagon>();
        this.radius = 3;
        this.deplace = false;
        this.priorityLevel = 10;
        this.scope = new ArrayList<Hexagon>();
    }

   /* public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }*/

    public static int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        goldCost = goldCost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getId() {
        return id;
    }

    public void moveToDestination(Hexagon p_hexagon){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = Grid.getCapitalJoueur().getUnitToDeplace().getPath().size()-1; i>=0; i--){
                    Grid.getCapitalJoueur().getUnitToDeplace().move(Grid.getCapitalJoueur().getUnitToDeplace().getPath().get(i));
                    Grid.getCapitalJoueur().getUnitToDeplace().move(Grid.getCapitalJoueur().getUnitToDeplace().getPath().remove(i));
                    try {
                        Thread.sleep(675);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Grid.getCapitalJoueur().getUnitToDeplace().setDeplace(false);
                Grid.getCapitalJoueur().getUnitToDeplace().setWantMove(false);
                Grid.getCapitalJoueur().setUnitToDeplace(null);
            }
        });
        thread.start();
    }

    public void moveToDestinationIa(Hexagon p_hexagon){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = Grid.getCapitalIa().getUnitToDeplace().getPath().size()-1; i>=0; i--){
                    Grid.getCapitalIa().getUnitToDeplace().moveIa(Grid.getCapitalIa().getUnitToDeplace().getPath().get(i));
                    Grid.getCapitalIa().getUnitToDeplace().moveIa(Grid.getCapitalIa().getUnitToDeplace().getPath().remove(i));
                    try {
                        Thread.sleep(675);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();

    }

    public void moveToCapitalIa(Hexagon p_hexagon){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = Grid.getCapitalIa().getUnitToDeplace().getPath().size()-1; i>=0; i--){
                    Grid.getCapitalIa().getUnitToDeplace().moveCapitalIa(Grid.getCapitalIa().getUnitToDeplace().getPath().get(i));
                    Grid.getCapitalIa().getUnitToDeplace().moveCapitalIa(Grid.getCapitalIa().getUnitToDeplace().getPath().remove(i));
                    try {
                        Thread.sleep(675);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();

    }

    public void moveToDestinationPriorityHexagonIa(Hexagon p_hexagon){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = Grid.getCapitalIa().getUnitToDeplace().getPath().size()-1; i>=0; i--){
                    Grid.getCapitalIa().getUnitToDeplace().moveToPriorityHexagonIa(Grid.getCapitalIa().getUnitToDeplace().getPath().get(i));
                    Grid.getCapitalIa().getUnitToDeplace().moveToPriorityHexagonIa(Grid.getCapitalIa().getUnitToDeplace().getPath().remove(i));
                    try {
                        Thread.sleep(675);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();

    }



    public void move(Hexagon p_hexagon){
        Hexagon hexagonDestination = p_hexagon;

        if(hexagonDestination.getDistancePlayer() <= this.radius && hexagonDestination.getUnit() == null && hexagonDestination.getDistancePlayer() != 0){

            Hexagon hexagonDepart = getHexagon();
            hexagonDepart.setUnit(null);

            setHexagon(hexagonDestination);
            hexagonDestination.setUnit(this);

        }
    }

    public void moveIa(Hexagon p_hexagon){
        Hexagon hexagonDestination = p_hexagon;

        if(hexagonDestination.getDistanceIa() <= this.radius && hexagonDestination.getUnit() == null && hexagonDestination.getDistanceIa() != 0){

            Hexagon hexagonDepart = getHexagon();
            hexagonDepart.setUnit(null);

            setHexagon(hexagonDestination);
            hexagonDestination.setUnit(this);

        }
    }

    public void moveCapitalIa(Hexagon p_hexagon){
        Hexagon hexagonDestination = p_hexagon;

        if(hexagonDestination.getDistanceCapital() <= this.radius && hexagonDestination.getUnit() == null && hexagonDestination.getDistanceCapital() != 0 && !hexagonDestination.isContainCapital()){

            Hexagon hexagonDepart = getHexagon();
            hexagonDepart.setUnit(null);

            setHexagon(hexagonDestination);
            hexagonDestination.setUnit(this);

        }
    }


    public void moveToPriorityHexagonIa(Hexagon p_hexagon){
        Hexagon hexagonDestination = p_hexagon;

        if(hexagonDestination.getDistancePriorityHexagon() <= this.radius && hexagonDestination.getUnit() == null && hexagonDestination.getDistancePriorityHexagon() != 0){

            Hexagon hexagonDepart = getHexagon();
            hexagonDepart.setUnit(null);

            setHexagon(hexagonDestination);
            hexagonDestination.setUnit(this);

        }
    }

    public void attack(Hexagon hexagon){

        if(hexagon.isContainCapital()){

            if(hexagon.getCapital() == Grid.getCapitalIa()){
                if(Grid.getCapitalIa().getHealth() <= 0){
                    Grid.setGame(false);
                }else{
                    Grid.getCapitalIa().setHealth(Grid.getCapitalIa().getHealth() - Grid.getCapitalJoueur().getUnitToDeplace().getDamage());
                }
            }else{
                if(Grid.getCapitalJoueur().getHealth() <= 0){
                    Grid.setGame(false);
                }else{
                    Grid.getCapitalJoueur().setHealth(Grid.getCapitalJoueur().getHealth() - Grid.getCapitalIa().getUnitToDeplace().getDamage());
                }
            }


        }else{

            if(Grid.getCapitalIa().getUnitCollection().contains(hexagon.getUnit())){
                hexagon.getUnit().setHealth(hexagon.getUnit().getHealth() - Grid.getCapitalJoueur().getUnitToDeplace().getDamage());

                if(hexagon.getUnit().getHealth() <= 0){
                    Grid.getCapitalIa().getUnitCollection().remove(hexagon.getUnit());
                    hexagon.setUnit(null);
                }
            }else{
                hexagon.getUnit().setHealth(hexagon.getUnit().getHealth() - Grid.getCapitalIa().getUnitToDeplace().getDamage());

                if(hexagon.getUnit().getHealth() <= 0){
                    Grid.getCapitalJoueur().getUnitCollection().remove(hexagon.getUnit());
                    hexagon.setUnit(null);
                }
            }

        }

    }

    /**
     * Calcul de la distance par rapport aux hexagons
     */
    public void calculDistance(){
        Grid.resetViewHexagons();

        Hexagon hexagonDepart = getHexagon();

        hexagonDepart.setViewPlayer(true);
        hexagonDepart.setDistancePlayer(0);

        List<Hexagon> file = new ArrayList<Hexagon>();
        file.add(hexagonDepart);

        while (file.size() != 0){

            Hexagon hexagon = file.get(0);
            List<Hexagon> neighbors = hexagon.getNeighbors();

            for (Hexagon neighbor : neighbors) {

                if (neighbor != null) {
                    if (!neighbor.getViewPlayer() && !neighbor.isObstacle() && !neighbor.isContainCapital() && neighbor.getUnit() == null) {
                        neighbor.setViewPlayer(true);
                        neighbor.setDistancePlayer(hexagon.getDistancePlayer() + 1);
                        file.add(neighbor);
                    }

                }
            }
            file.remove(0);
        }
    }

    //Calcul la distance pour l'unité de l'IA
    public int calculDistanceUnitIa(){

        Grid.resetViewIaHexagons();

        Hexagon hexagonDepart = this.getHexagon();

        hexagonDepart.setViewIa(true);
        hexagonDepart.setDistanceIa(0);

        List<Hexagon> file = new ArrayList<Hexagon>();
        file.add(hexagonDepart);

        while (file.size() != 0){

            Hexagon hexagon = file.get(0);
            List<Hexagon> neighbors = hexagon.getNeighbors();

            for (Hexagon neighbor : neighbors) {

                if (neighbor != null) {
                    if (!neighbor.getViewIa() && !neighbor.isObstacle() && !neighbor.isContainCapital() && neighbor.getUnit() == null) {
                        neighbor.setViewIa(true);
                        neighbor.setDistanceIa(hexagon.getDistanceIa() + 1);
                        file.add(neighbor);

                        //Si l'hexagon courant est à distance il est ajouté au scope de l'unité
                        if(neighbor.getDistanceIa() <= this.radius){
                            this.scope.add(neighbor);
                        }
                    }

                }
            }
            file.remove(0);
        }

        //System.out.println("Nb d'hexagons possible : " + this.scope.size());
        return this.scope.size();
    }

    //Calcul de la distance entre l'unité et l'hexagon prioritaire de l'IA
    public void calculDistanceBetweenUnitAndHexagonPriorityIa(){

        Grid.resetViewPriorityHexagons();

        Hexagon hexagonDepart = this.getHexagon();

        hexagonDepart.setViewPriorityHexagon(true);
        hexagonDepart.setDistancePriorityHexagon(0);

        List<Hexagon> file = new ArrayList<Hexagon>();
        file.add(hexagonDepart);

        while (file.size() != 0){

            Hexagon hexagon = file.get(0);
            List<Hexagon> neighbors = hexagon.getNeighbors();

            for (Hexagon neighbor : neighbors) {

                if (neighbor != null) {
                    if (!neighbor.getViewPriorityHexagon() && !neighbor.isObstacle() && !neighbor.isContainCapital()) {
                        /**
                         * L'unité appartient au joueur
                         */
                        if(neighbor.getUnit() == null || (neighbor.getUnit() != null && Grid.getCapitalJoueur().getUnitCollection().contains(neighbor.getUnit()))){

                            neighbor.setViewPriorityHexagon(true);
                            neighbor.setDistancePriorityHexagon(hexagon.getDistancePriorityHexagon() + 1);
                            file.add(neighbor);

                        }
                    }
                }
            }
            file.remove(0);
        }
    }

    public void calculDistanceBetweenUnitAndHexagonCapitalJoueur(){

        Grid.resetViewCapitalIaHexagons();

        Hexagon hexagonDepart = this.getHexagon();

        hexagonDepart.setViewCapital(true);
        hexagonDepart.setDistanceCapital(0);

        List<Hexagon> file = new ArrayList<Hexagon>();
        file.add(hexagonDepart);

        while (file.size() != 0){
            Hexagon hexagon = file.get(0);
            List<Hexagon> neighbors = hexagon.getNeighbors();

            for (Hexagon neighbor : neighbors) {

                if (neighbor != null) {
                    if (!neighbor.getViewCapital() && !neighbor.isObstacle()) {

                        neighbor.setViewCapital(true);
                        neighbor.setDistanceCapital(hexagon.getDistanceCapital() + 1);
                        file.add(neighbor);

                    }
                }
            }
            file.remove(0);
        }
    }

    //Path pour unité joueur
    public void path(Hexagon p_hexagon){
        if(this.path.size() != 0){
            resetPath();
        }

        Hexagon currentHexagon = this.getHexagon();
        Hexagon hexagonDestination = p_hexagon;

        this.path.add(hexagonDestination);

        while (hexagonDestination.getDistancePlayer() != currentHexagon.getDistancePlayer()){

            int distanceDestination = hexagonDestination.getDistancePlayer();

            for(Hexagon neighbor : hexagonDestination.getNeighbors()){

                if(neighbor != null){

                    if(neighbor.getDistancePlayer() == (distanceDestination-1) && hexagonDestination.getUnit() == null) {
                        this.path.add(neighbor);
                        hexagonDestination = neighbor;
                        break;
                    }
                }
            }

        }

        this.path.remove(path.size()-1);

        if(this.path.size() <= this.radius){
            for(Hexagon hexagonPath : this.path){
                hexagonPath.setTexture(Grid.getGrass_path());
            }
        }
    }

    //Path pour unité IA
    public void pathIa(Hexagon p_hexagon){
        if(this.path.size() != 0){
            this.path.clear();
        }

        Hexagon currentHexagon = getHexagon();
        Hexagon hexagonDestination = p_hexagon;

        this.path.add(hexagonDestination);

        while (hexagonDestination.getDistanceIa() != currentHexagon.getDistanceIa()){

            int distanceDestination = hexagonDestination.getDistanceIa();

            for(Hexagon neighbor : hexagonDestination.getNeighbors()){
                if(neighbor != null){
                    if(neighbor.getDistanceIa() == (distanceDestination-1) && hexagonDestination.getUnit() == null) {
                        this.path.add(neighbor);
                        hexagonDestination = neighbor;
                        break;
                    }
                }
            }

        }


        this.path.remove(path.size()-1);

    }

    public void pathToCapitalIa(Hexagon p_hexagon){
        if(this.path.size() != 0){
            this.path.clear();
        }

        Hexagon currentHexagon = getHexagon();
        Hexagon hexagonDestination = p_hexagon;

        this.path.add(hexagonDestination);

        while (hexagonDestination.getDistanceCapital() != currentHexagon.getDistanceCapital()){


            int distanceDestination = hexagonDestination.getDistanceCapital();

            for(Hexagon neighbor : hexagonDestination.getNeighbors()){

                if(neighbor != null){
                    if(neighbor.getDistanceCapital() == (distanceDestination-1) && hexagonDestination.getUnit() == null && !neighbor.isContainCapital()) {
                        this.path.add(neighbor);
                        hexagonDestination = neighbor;
                        break;
                    }
                }

            }

        }

        this.path.remove(path.size()-1);

    }



    //Path pour unité IA vers l'hexagon prioritaire
    public void pathToHexagonPriorityIa(Hexagon p_hexagon){
        if(this.path.size() != 0){
            this.path.clear();
        }

        Hexagon currentHexagon = this.getHexagon();
        Hexagon hexagonDestination = p_hexagon;
       // System.out.println("Hexa current : " + currentHexagon.getId() + " Hexa destination : " + hexagonDestination.getId());
        this.path.add(hexagonDestination);

        while (hexagonDestination.getDistancePriorityHexagon() != currentHexagon.getDistancePriorityHexagon()){

            int distanceDestination = hexagonDestination.getDistancePriorityHexagon();

            for(Hexagon neighbor : hexagonDestination.getNeighbors()){

                if(neighbor != null){
                    //System.out.println("Hexa : " + neighbor.getId() + " Distance : " + neighbor.getDistancePriorityHexagon());

                    if(neighbor.getDistancePriorityHexagon() == (distanceDestination-1) && (hexagonDestination.getUnit() == null || (hexagonDestination.getUnit() != null && Grid.getCapitalJoueur().getUnitCollection().contains(hexagonDestination.getUnit())))) {
                        this.path.add(neighbor);
                        hexagonDestination = neighbor;
                        break;
                    }

                }
            }

        }

        this.path.remove(path.size()-1);

    }

    public void resetPath(){
        Grid.resetTextureHexagons();
        this.path.clear();
    }

    public Hexagon getHexagon() {
        return hexagon;
    }

    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
    }

    public boolean isWantMove() {
        return wantMove;
    }

    public void setWantMove(boolean wantMove) {
        this.wantMove = wantMove;
    }

    public List<Hexagon> getPath() {
        return path;
    }

    public void setPath(List<Hexagon> path) {
        this.path = path;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isDeplace() {
        return deplace;
    }

    public void setDeplace(boolean deplace) {
        this.deplace = deplace;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public List<Hexagon> getScope() {
        return scope;
    }

    public void setScope(List<Hexagon> scope) {
        this.scope = scope;
    }
}
