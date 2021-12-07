package com.serkox.entity;

import java.util.TimerTask;

public class TimerG extends TimerTask {

    public void run() {
        Grid.getCapitalJoueur().addCurrentGold(5);
        Grid.getCapitalIa().addCurrentGold(5);
        //System.out.println(Grid.getCapitalJoueur().getCurrentGold());
    }

}
