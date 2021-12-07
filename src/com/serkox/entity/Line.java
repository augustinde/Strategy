package com.serkox.entity;

import java.util.ArrayList;

public class Line{

	private ArrayList<Hexagon> hexagonList;

    public Line(){
        hexagonList = new ArrayList<Hexagon>();
    }

    public ArrayList<Hexagon> getHexagonList() {
        return hexagonList;
    }

    public void setHexagonList(ArrayList<Hexagon> hexagonList) {
        this.hexagonList = hexagonList;
    }
}
