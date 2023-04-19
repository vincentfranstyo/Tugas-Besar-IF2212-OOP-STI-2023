package com.BNMO.Object.NonFoodObjects.Piano;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class Piano extends NonFoodObjects {
    public Piano(String name, int price) {
        super(name, 3, 3, price);
    }

    public void use(Time time, Sim sim) {
        if (!getIsOccupied()) {
            setIsOccupied(true);
            if (sim.getStatus().equals("Nothing")) {
                int duration = time.convertToSecond();
                sim.setStatus("Playing piano");
                sim.setMood(sim.getMood() + 5 * duration / 20);
            } else {
                System.out.println("You can't play the piano while you are " + sim.getStatus());
            }
        }
    }
}