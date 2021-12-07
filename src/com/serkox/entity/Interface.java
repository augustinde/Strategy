package com.serkox.entity;

import com.sun.jdi.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JPanel{

    private Texture background;
    private int health;
    private int gold;
    private int maxGold;
    private int level;

    private int healthIa;
    private static boolean addUnit;

    public Interface(){
        super();
        this.addUnit = false;

        JButton btnBuyUnit = new JButton("Acheter une unité (70)");
        btnBuyUnit.setBounds(700,0, 150, 50);
        btnBuyUnit.setBorder(null);
        btnBuyUnit.setBackground(Color.red);
        btnBuyUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(addUnit) {
                    System.out.println("Ne souhaite plus placer d'unité.");
                    addUnit = false;
                }else{
                    System.out.println("Souhaite placer une unité !");
                    addUnit = true;
                }
            }
        });
        this.add(btnBuyUnit);

        this.setBounds(0, 0, 1280,50);
        this.setBackground(Color.gray);
        this.setVisible(true);
        this.setLayout(null);
        this.health = Grid.getCapitalJoueur().getHealth();
        this.gold = Grid.getCapitalJoueur().getCurrentGold();
        this.maxGold = Grid.getCapitalJoueur().getMaxGold();
        this.level = Grid.getCapitalJoueur().getLevel();
        this.healthIa = Grid.getCapitalIa().getHealth();
        repaint();

    }

    protected void paintComponent(Graphics g) {
        this.gold = Grid.getCapitalJoueur().getCurrentGold();

        super.paintComponent(g);
        Graphics g2d = (Graphics2D) g;

        Font font = new Font("Serif", Font.BOLD, 18);
        g2d.setFont(font);

        g2d.drawString("Vie :", 10, 20);
        g2d.drawString(String.valueOf(this.health) + "/150", 50, 20);
        g2d.drawString("Or :", 120, 20);
        g2d.drawString(String.valueOf(this.gold) + "/" + String.valueOf(this.maxGold), 160, 20);
        g2d.drawString("Niveau :", 250, 20);
        g2d.drawString(String.valueOf(this.level), 320, 20);
        g2d.drawString("Vie IA :", 500, 20);
        g2d.drawString(String.valueOf(this.healthIa) + "/150", 570, 20);


    }

    public static boolean wantPlaceUnit(){
        return addUnit;
    }
}
