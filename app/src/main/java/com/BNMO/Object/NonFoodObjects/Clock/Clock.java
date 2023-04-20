package com.BNMO.Object.NonFoodObjects.Clock;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Clock extends NonFoodObjects {
    private Time currentTime;

    public Clock(String name, Time currentTime, Point position) {
        super(name, 1, 1, 10, position);
        this.currentTime = currentTime;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Time currentTime) {
        this.currentTime = currentTime;
    }

    public void use(Time time, Sim sim) {
        // show all ongoing action along with the duration left
        System.out.println("left duration");
    }
}
