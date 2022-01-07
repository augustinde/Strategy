package com.serkox.state;

public abstract class State {
    public State current, buyUnit, analyse, moveUnit, attack;

    public abstract void enter();
    public abstract void update();
}
