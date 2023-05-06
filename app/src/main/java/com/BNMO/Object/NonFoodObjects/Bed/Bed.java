package com.BNMO.Object.NonFoodObjects.Bed;

import com.BNMO.Object.NonFoodObjects.Bed.Bed;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public interface Bed {
    String getType();

    int getCapacity();

    int getSpaceLeft();

    void setSpaceLeft(int spaceLeft);

    void sleep(Time time, Sim sim);
}
