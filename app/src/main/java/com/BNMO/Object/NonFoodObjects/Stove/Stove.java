package com.BNMO.Object.NonFoodObjects.Stove;

public interface Stove {
    public boolean getCurrentState();

    public void setCurrentState(boolean currentState);

    public void turnOn();

    public void turnOff();

    public void cookDish();
}
