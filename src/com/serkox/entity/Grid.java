package com.serkox.entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {

    private ArrayList<Hexagon> hexagons;

    private Texture grass;
    private Texture water;
    private Texture grass_dark;
    private Texture textureCapitalIa;
    private Texture textureCapitalJoueur;
    private Texture infantry;

    private static Capital capitalJoueur;
    private static Capital capitalIa;

    private int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,2,1,1,1,1,1,1,1,1,1,0},
            {0,1,2,1,1,1,1,1,1,1,2,2,1,0},
            {0,10,1,2,2,1,1,2,2,1,1,1,0},
            {0,1,1,1,1,1,1,2,1,2,1,1,1,0},
            {0,1,1,1,1,1,2,1,1,1,1,2,0},
            {0,1,1,2,1,2,1,1,1,1,2,2,1,0},
            {0,1,1,1,1,1,1,1,1,1,20,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0}

    };


    public Grid(){
        super();

        this.setBounds(0, 50, 1280, 670);
        this.setVisible(true);
        this.setLayout(null);

        this.grass = new Texture("grass");
        this.grass_dark = new Texture("grass_dark");
        this.water = new Texture("water");
        this.textureCapitalIa = new Texture("capital_ia");
        this.textureCapitalJoueur = new Texture("capital_joueur");
        this.infantry = new Texture("infantry");
        capitalJoueur = new Capital(1);
        capitalIa = new Capital(2);
        createGrid();
        System.out.println(this.hexagons.size());
        //this.lineList.forEach(itemLine -> itemLine.getHexagonList().forEach(m -> this.add(m)));
        this.hexagons.forEach(item -> this.add(item));
        addNeighbors();
    }

    public void createGrid(){
        //lineList = new ArrayList<Line>();
        hexagons = new ArrayList<Hexagon>();

        int y1=0,y2=0,y3=0,y4=0,y5=0,y6=0;
        int x1=0,x2=0,x3=0,x4=0,x5=0,x6=0;
        int lg = 100;
        int id = 0;

        for(int i = 0; i<this.map.length; i++) {
            //this.lineList.add(new Line());

            y1 = ((lg-25)*i);
            y2 = y1+50;
            y3 = ((lg-25)*i)+75;
            y4 = y2;
            y5 = y1;
            y6 = (lg-25)*i-25;

            for (int j = 0; j < this.map[i].length; j++) {

                if(i%2 == 0) {
                    x1 = (lg*j)-50;//-50 en plus
                    x2 = x1;
                    x3 = 50+(lg*j);
                    x4 = x2+100;
                    x5 = x4;
                    x6 = x3;
                }else {
                    x1 = (lg*j);//+50 en moins
                    x2 = x1;
                    x3 = 100+(lg*j);
                    x4 = x2+100;
                    x5 = x4;
                    x6 = x3;
                }
                Hexagon hexagon = new Hexagon(id);
                id +=1;
                hexagon.setPosX(x1);
                hexagon.setPosY(y6);
                if(this.map[i][j] == 1 || this.map[i][j] == 10 ||this.map[i][j] == 20) {
                    hexagon.setBounds(hexagon.getPosX()+5, hexagon.getPosY()+5 , 90, 90);
                }


                if(this.map[i][j] == 1){
                    hexagon.setObstacle(false);
                }else if(this.map[i][j] == 2){
                    hexagon.setObstacle(true);
                }else{
                    hexagon.setObstacle(true);
                }

                this.hexagons.add(hexagon);
                System.out.println("X : " + hexagon.getPosX());
                System.out.println("Y : " + hexagon.getPosY());
            }
        }

        System.out.println("Hexagon : " + this.hexagons.size());
        System.out.println("Grille créé !");
    }

    public ArrayList<Hexagon> getHexagons() {
        return hexagons;
    }

    public void setHexagons(ArrayList<Hexagon> hexagons) {
        this.hexagons = hexagons;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2d = (Graphics2D) g;
        int cpt = 0;

        for(int i = 0; i<this.map.length; i++){
            //System.out.println("Ligne : " + i + " Taille : " + this.map.length);

            for(int j = 0; j<this.map[i].length; j++) {
                Hexagon hexagon = this.hexagons.get(cpt);
                //System.out.println("J : " + j + " X : " + hexagon.getPosY());
                cpt++;

                if(this.map[i][j] != 0){

                    g2d.setColor(Color.black);

                    //Choix de la texture
                    Texture texture;

                    if(this.map[i][j] == 1){
                        texture = this.grass;
                    }else if(this.map[i][j] == 2){
                        texture = this.water;
                    }else{
                        texture = this.grass;
                    }

                    g2d.drawImage(texture.getTexture(), hexagon.getPosX(), hexagon.getPosY(), 100, 100, null);

                    /*if(hexagon.getNeighbors().get(0) != null){
                        g2d.setColor(Color.red);
                        g2d.fillRect(hexagon.getPosX(), hexagon.getPosY(), 10, 10);
                    }*/

                    //Ajout des capitales
                    if(this.map[i][j] == 10){
                        g2d.drawImage(this.textureCapitalJoueur.getTexture(), hexagon.getPosX(),hexagon.getPosY()-25, 100, 100, null);
                        hexagon.setContainCapital(true);
                    }else if(this.map[i][j] == 20){
                        g2d.drawImage(this.textureCapitalIa.getTexture(), hexagon.getPosX(),hexagon.getPosY()-25, 100, 100, null);
                        hexagon.setContainCapital(true);
                    }else{
                        hexagon.setContainCapital(false);
                    }

                    //Affichage des unités
                    if(hexagon.getUnit() != null) {
                        g2d.drawImage(this.infantry.getTexture(), hexagon.getPosX(), hexagon.getPosY(), 100, 100, null);
                    }
                }else {
                    g2d.drawImage(this.grass_dark.getTexture(), hexagon.getPosX(), hexagon.getPosY(), 100, 100, null);
                }
                g2d.drawString(String.valueOf(hexagon.getId()), hexagon.getPosX()+40, hexagon.getPosY()+40);
            }
        }

    }

    public static Capital getCapitalJoueur() {
        return capitalJoueur;
    }

    public static Capital getCapitalIa() {
        return capitalIa;
    }

    public void addNeighbors(){
        int cpt = 0;

        for(int i = 0; i<this.map.length; i++){

            for(int j = 0; j<this.map[i].length; j++){

                Hexagon currentHexagon = hexagons.get(cpt);
                System.out.println("Id " + currentHexagon.getId());
                ArrayList<Hexagon> ng = new ArrayList<Hexagon>();
                int v1, v2;
                Hexagon voisin1;
                Hexagon voisin2;

                if(i>=1){
                    v1 = currentHexagon.getId() - 13;
                    v2 = currentHexagon.getId() - 14;
                    voisin1 = hexagons.get(v1);
                    voisin2 = hexagons.get(v2);
                    ng.add(voisin1);
                    ng.add(voisin2);
                    System.out.println("Voisin 1 : " + voisin1.getId());
                    System.out.println("Voisin 2 : " + voisin2.getId());
                }

                cpt++;
                //ng.forEach(e -> System.out.println(e.getId()));
                currentHexagon.setNeighbors(ng);
                for(int k = 0; k<currentHexagon.getNeighbors().size(); k++){
                    System.out.println("Neig : " + currentHexagon.getNeighbors().get(k).getId());
                }
            }


        }


        /*for(int i = 0; i < lineList.size(); i++){
            for(int j = 0; j < lineList.get(i).getHexagonList().size(); j++){
                Hexagon currentHexagon = lineList.get(i).getHexagonList().get(j);
                ArrayList<Hexagon> ng = new ArrayList<Hexagon>();

                //if()

                /*
                if(i >= 1){
                    //Nord ouest => i-1 | j
                    //ng.add(lineList.get(i-1).getHexagonList().get(j));
                   // System.out.println("Id voisin " + lineList.get(i-1).getHexagonList().get(j).getId());
                    //Nord est => i-1 | j+1

                }else{
                    ng.add(null);
                }

                if(i <= lineList.size()-1) {
                    ng.add(lineList.get(i).getHexagonList().get(j));

                }else{
                    ng.add(null);
                }
                currentHexagon.setNeighbors(ng);
*/

              /*  if(j >= 1){
                    //Ouest => i | j-1
                    currentHexagon.getNeighbors().add(lineList.get(i).getHexagonList().get(j-1));
                    //Est => i | j+1
                    currentHexagon.getNeighbors().add(lineList.get(i).getHexagonList().get(j+1));
                }
                if(i <= lineList.size()-1){
                    //Sud ouest => i+1 | j
                    currentHexagon.getNeighbors().add(lineList.get(i+1).getHexagonList().get(j));
                    //Sud est => i+1 | j+1
                    currentHexagon.getNeighbors().add(lineList.get(i+1).getHexagonList().get(j+1));
                }*/
          //  }
        //}

    }
}