package com.serkox.entity;

import java.util.TimerTask;

public class TimerG extends TimerTask {

    public void run() {
        Grid.getCapitalJoueur().addCurrentGold(10);
        Grid.getCapitalIa().addCurrentGold(10);
        //System.out.println(Grid.getCapitalJoueur().getCurrentGold());
    }

}
