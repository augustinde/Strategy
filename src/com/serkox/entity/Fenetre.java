package com.serkox.entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;

public class Fenetre extends JFrame{

    private Timer timer;

		public Fenetre() {
            this.setTitle("StrateGy");
            this.setSize(1280, 720);
            //this.add(new Grid());
            Game game = new Game();
            this.add(game);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);

            this.timer = new Timer();
            this.timer.schedule(new TimerG(), 1000, 1000);
                try {
                        this.setIconImage(ImageIO.read(getClass().getResourceAsStream("/com/serkox/textures/capital_ia.png")));
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
}
