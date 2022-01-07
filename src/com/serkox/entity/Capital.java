package com.serkox.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Capital {

    protected int id;
    protected int health;
    protected int level;
    protected int currentGold;
    protected int maxGold;
    protected int goldPerSec;
    protected ArrayList<Unit> unitCollection;

    private Texture texture;
    private static int unitGoldCost;

    private List<Hexagon> hexagonsInRadius;

    private Hexagon hexagon;

    /**
     * Détermine l'hexagone prioritaire que la capitale va vouloir rejoindre
     */
    private Hexagon hexagonPriority;

    private Unit unitPriority;

    /**
     * Détermine l'unité que je veux déplacer
     */
    protected Unit unitToDeplace;

    public Capital() {
        unitGoldCost = 100;
        this.hexagonsInRadius = new ArrayList<Hexagon>();
        unitToDeplace = null;

    }

    public ArrayList<Unit> getUnitCollection() {
        return unitCollection;
    }


    public void setUnitCollection(ArrayList<Unit> unitCollection) {
        this.unitCollection = unitCollection;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int pCurrentGold) {
        currentGold = pCurrentGold;
    }

    public void addCurrentGold(int gold){
        if(currentGold < this.maxGold)
            currentGold += gold;
    }

    public int getGoldPerSec() {
        return goldPerSec;
    }

    public void setGoldPerSec(int goldPerSec) {
        this.goldPerSec = goldPerSec;
    }

    public int getMaxGold() {
        return maxGold;
    }

    public void setMaxGold(int maxGold) {
        this.maxGold = maxGold;
    }


    public static int getUnitGoldCost() {
        return unitGoldCost;
    }

    public void setUnitGoldCost(int unitGoldCost) {
        Capital.unitGoldCost = unitGoldCost;
    }

    public Hexagon getHexagon() {
        return hexagon;
    }

    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
    }

    /**
        Récupère les hexagons qui sont dans le radius de la capital
     */
    public void calculHexagonInRadius(){

        //Grid.resetTextureHexagons();
        Grid.resetViewPriorityHexagons();

        Hexagon hexagonDepart = this.getHexagon();

        hexagonDepart.setViewRadius(true);
        hexagonDepart.setDistanceRadius(0);

        List<Hexagon> file = new ArrayList<Hexagon>();
        file.add(hexagonDepart);

        while (file.size() != 0){

            Hexagon hexagon = file.get(0);
            List<Hexagon> neighbors = hexagon.getNeighbors();

            for (Hexagon neighbor : neighbors) {

                if (neighbor != null) {
                    if (!neighbor.getViewRadius() && !neighbor.isObstacle() && !neighbor.isContainCapital()) {
                        neighbor.setViewRadius(true);
                        neighbor.setDistanceRadius(hexagon.getDistanceRadius() + 1);
                        file.add(neighbor);

                        //Si l'hexagon courant est à distance il est ajouté au scope de l'unité
                        if(neighbor.getDistanceRadius() <= 5){
                            this.hexagonsInRadius.add(neighbor);
                        }

                    }

                }
            }
            file.remove(0);
        }

    }

    /**
     * Remets à 10 la valeur de priorité des hexagones dans le radius de la capitale de l'ia
     */
    public void resetPriorityHexagonInRadius(){
        for(Hexagon hexagon : this.hexagonsInRadius){
            hexagon.setPriority(10);
        }
    }

    /**
     * Vérifie la présence d'unité dans le radius de la capital, si une unité appartient au joueur, l'hexagon gagne 5 points de priorité
     * @return boolean
     */
    public boolean checkUnitInRadius(){

        this.resetPriorityHexagonInRadius();
        this.calculHexagonInRadius();
        this.hexagonPriority = this.hexagonsInRadius.get(0);

        for(Hexagon hexagon : this.hexagonsInRadius){
            if(hexagon.getUnit() !=  null){

                if(Grid.getCapitalJoueur().getUnitCollection().contains(hexagon.getUnit())) {
                    hexagon.setPriority(hexagon.getPriority()+5);

                }else if(Grid.getCapitalIa().getUnitCollection().contains(hexagon.getUnit())){
                    hexagon.setPriority(10);

                }else{
                    hexagon.setPriority(hexagon.getPriority()-5);
                }

            }
            if(hexagon.getPriority() > hexagonPriority.getPriority()){
                this.hexagonPriority = hexagon;
            }
        }

        if(hexagonPriority.getPriority() > 10){
            System.out.println("Une unité se trouve prêts de la capital de l'IA");
            return true;
        }else{
            System.out.println("Aucune unité prêts de la capital de l'IA");
            return false;
        }

    }

    public Hexagon getHexagonPriority() {
        return hexagonPriority;
    }

    public void setHexagonPriority(Hexagon hexagonPriority) {
        this.hexagonPriority = hexagonPriority;
    }

    public List<Hexagon> getHexagonsInRadius() {
        return hexagonsInRadius;
    }

    public void setHexagonsInRadius(List<Hexagon> hexagonsInRadius) {
        this.hexagonsInRadius = hexagonsInRadius;
    }

    public void getUnitNearbyPriorityHexagon(){

        Unit unitNearbyPriorityHexagon;

        unitNearbyPriorityHexagon = this.unitCollection.get(0);
        unitNearbyPriorityHexagon.calculDistanceBetweenUnitAndHexagonPriorityIa();

        for(Unit unit : Grid.getCapitalIa().getUnitCollection()){

            unit.calculDistanceBetweenUnitAndHexagonPriorityIa();
            System.out.println("Unite : " + unit.getId() + " Distance : " + unit.getHexagon().getDistancePriorityHexagon());
            if(unit.getHexagon().getDistancePriorityHexagon() < unitNearbyPriorityHexagon.getHexagon().getDistancePriorityHexagon()){
                unitNearbyPriorityHexagon = unit;
            }

        }
        Grid.getCapitalIa().setUnitToDeplace(unitNearbyPriorityHexagon);
    }

    protected abstract void setUnitToDeplace(Unit unit);

    protected abstract void addUnit(Unit unit);

    public Unit getUnitPriority() {
        return unitPriority;
    }

    public void setUnitPriority(Unit unitPriority) {
        this.unitPriority = unitPriority;
    }

    protected abstract Unit getUnitToDeplace();
}