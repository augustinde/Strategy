package com.serkox.entity;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    public Game(){
        super();
        this.add(new Grid());
        this.add(new Interface());
        this.setLayout(null);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Thread.sleep(33);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }
}
