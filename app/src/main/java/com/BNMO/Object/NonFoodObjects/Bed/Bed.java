package com.BNMO.Object.NonFoodObjects.Bed;

import com.BNMO.Object.NonFoodObjects.Bed.Bed;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public interface Bed {
    public String getType();

    public int getCapacity();

    public int getSpaceLeft();

    public void setSpaceLeft(int spaceLeft);

    public void sleep(Time time, Sim sim);
}
