package com.serkox.entity;

public class Camera {

    private int x;
    private int y;

    public Camera(int p_x, int p_y){
        this.x = p_x;
        this.y = p_y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
