package com.BNMO.Object.NonFoodObjects.Toilet;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class Toilet extends NonFoodObjects {
    public Toilet(String name) {
        super(name, 1, 1, 50);
    }

    public void use(Time time, Sim sim) {
        if (!getIsOccupied()) {
            if (sim.getStatus().equals("Nothing")) {
                int duration = time.convertToSecond();
                setIsOccupied(true);
                sim.setStatus("Using toilet");
                sim.setFullness(sim.getFullness() - (20 * (duration / 10)));
                sim.setMood(sim.getMood() + (10 * (duration / 10)));
            } else {
                System.out.println("You can't use the toilet while doing something else.");
            }
        }

        else {
            System.out.println("Too bad! The toilet is in use, please find another toilet!");
        }
    }
}
