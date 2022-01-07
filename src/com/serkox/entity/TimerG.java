package com.serkox.entity;

import java.util.TimerTask;

public class TimerG extends TimerTask {

    public void run() {
        Grid.getCapitalJoueur().addCurrentGold(50);
        Grid.getCapitalIa().addCurrentGold(50);
        //System.out.println(Grid.getCapitalJoueur().getCurrentGold());
    }

}
