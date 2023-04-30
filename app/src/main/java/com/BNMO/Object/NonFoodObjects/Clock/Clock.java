package com.BNMO.Object.NonFoodObjects.Clock;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Clock extends NonFoodObjects {

    public Clock(String name) {
        super(name, 1, 1, 10);
        this.setType("Clock");
    }

    public void use(Sim sim) {
        // show all ongoing action along with the duration left
        System.out.println("left duration");
    }
}
