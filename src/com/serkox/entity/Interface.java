package com.serkox.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JPanel{

    private Texture texture;
    private Texture textureGold;
    private Texture textureHealth;
    private int health;
    private int gold;
    private int maxGold;
    private int level;

    private int healthIa;
    private static boolean addUnit;
    private static String message = "";

    public Interface(){
        super();
        addUnit = false;
        //this.texture = new Texture("interface_joueur");
        this.textureGold = new Texture("money");
        this.textureHealth = new Texture("vie");
        JButton btnBuyUnit = new JButton("Acheter une unité (70)");
        btnBuyUnit.setBounds(200,10, 150, 30);
        btnBuyUnit.setBorder(null);
        btnBuyUnit.setBackground(new Color(211, 84, 0));
        btnBuyUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addUnit) {
                    System.out.println("Ne souhaite plus placer d'unité.");
                    Interface.setMessage("Le joueur ne souhaite plus placer d'unité.");
                    addUnit = false;
                }else{
                    System.out.println("Souhaite placer une unité !");
                    Interface.setMessage("Le joueur souhaite placer une unité.");
                    addUnit = true;
                }
            }
        });

        this.add(btnBuyUnit);
        this.setBounds(0, 0, 1280,50);
        this.setBackground(new Color(135, 54, 0));
        this.setVisible(true);
        this.setLayout(null);
        this.health = Grid.getCapitalJoueur().getHealth();
        this.gold = Grid.getCapitalJoueur().getCurrentGold();
        this.maxGold = Grid.getCapitalJoueur().getMaxGold();
        this.level = Grid.getCapitalJoueur().getLevel();
        this.healthIa = Grid.getCapitalIa().getHealth();
        repaint();

    }

    public static void setMessage(String message) {
        Interface.message = message;
    }

    protected void paintComponent(Graphics g) {
        this.gold = Grid.getCapitalJoueur().getCurrentGold();

        super.paintComponent(g);
        Graphics g2d = (Graphics2D) g;

        Font font = new Font("Serif", Font.BOLD, 18);
        g2d.setFont(font);
        //g2d.drawImage(texture.getTexture(), 0,0,1280, 50, null);
        g2d.setColor(Color.white);

        g2d.drawImage(this.textureHealth.getTexture(), -20, -15, 100, 100, null);
        g2d.drawString(String.valueOf(this.health) + "/150", 30, 20);

        g2d.drawImage(this.textureGold.getTexture(), -2, 25, 70, 60, null);
        g2d.drawString(String.valueOf(this.gold) + "/" + String.valueOf(this.maxGold), 30, 45);

        g2d.drawString("Niveau :", 110, 20);
        g2d.drawString(String.valueOf(this.level), 180, 20);

        g2d.drawString("Vie IA :", 1130, 20);
        g2d.drawString(String.valueOf(this.healthIa) + "/150", 1200, 20);

        g2d.drawString(message, 420, 30);

    }

    public static boolean wantPlaceUnit(){
        return addUnit;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
