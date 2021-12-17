package com.serkox.entity;

import java.util.*;

public class Unit {

    private String id = UUID.randomUUID().toString();
    private int damage;
    private int health;
    private static int goldCost;
    private int level;
    private int speed;
    private Hexagon hexagon;
    private boolean wantMove;
    private List<Hexagon> path;
    private int radius;

    public Unit(Hexagon p_hexagon){
        this.damage = 15;
        this.health = 50;
        goldCost = 10;
        this.speed = 1;
        this.level = 1;
        this.hexagon = p_hexagon;
        this.path = new ArrayList<Hexagon>();
        this.radius = 20;
    }

    public int getLevel() {
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
    }

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
            }
        });
        thread.start();

    }

    public void move(Hexagon p_hexagon){
        Hexagon hexagonDestination = p_hexagon;

        if(hexagonDestination.getDistance() <= this.radius && hexagonDestination.getUnit() == null && hexagonDestination.getDistance() != 0){

            Hexagon hexagonDepart = getHexagon();
            hexagonDepart.setUnit(null);

            setHexagon(hexagonDestination);
            hexagonDestination.setUnit(this);

        }
    }

    public void attack(Hexagon hexagon){

    }

    /**
     * Calcul de la distance par rapport aux hexagons
     */
    public void calculDistance(){

        Grid.resetTextureHexagons();
        Grid.resetViewHexagons();

        Hexagon hexagonDepart = this.getHexagon();

        hexagonDepart.setView(true);
        hexagonDepart.setDistance(0);

        List<Hexagon> file = new ArrayList<Hexagon>();
        file.add(hexagonDepart);

        while (file.size() != 0){

            Hexagon hexagon = file.get(0);
            List<Hexagon> neighbors = hexagon.getNeighbors();

            for (Hexagon neighbor : neighbors) {

                if (neighbor != null) {
                    if (!neighbor.isView() && !neighbor.isObstacle() && !neighbor.isContainCapital()) {
                        neighbor.setView(true);
                        neighbor.setDistance(hexagon.getDistance() + 1);
                        System.out.println(neighbor.getId() + " : " + neighbor.getDistance());
                        file.add(neighbor);
                    }
                }
            }
            file.remove(0);

        }

    }

    public void path(Hexagon p_hexagon){
        if(this.path.size() != 0){
            resetPath();
        }

        Hexagon currentHexagon = getHexagon();
        Hexagon hexagonDestination = p_hexagon;

        this.path.add(hexagonDestination);

        while (hexagonDestination.getDistance() != currentHexagon.getDistance()){

            int distanceDestination = hexagonDestination.getDistance();

            for(Hexagon neighbors : hexagonDestination.getNeighbors()){
                if(neighbors != null){
                    if(neighbors.getDistance() == (distanceDestination-1) && hexagonDestination.getUnit() == null) {
                        this.path.add(neighbors);
                        hexagonDestination = neighbors;
                        break;
                    }
                }
            }

        }

        this.path.remove(path.size()-1);
        System.out.println(this.path.size());
        if(this.path.size() <= this.radius){
            for(Hexagon hexagonPath : this.path){
                hexagonPath.setTexture(Grid.getGrass_path());
            }
        }
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
}
