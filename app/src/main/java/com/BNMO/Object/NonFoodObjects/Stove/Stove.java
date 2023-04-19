package com.BNMO.Object.NonFoodObjects.Stove;

import com.BNMO.Object.Food.Dishes;
import com.BNMO.SIMS.Sim;

public interface Stove {
    public boolean getCurrentState();

    public void setCurrentState(boolean currentState);

    public void turnOn();

    public void turnOff();

    public void cookDish(Sim sim, Dishes dish);
}
