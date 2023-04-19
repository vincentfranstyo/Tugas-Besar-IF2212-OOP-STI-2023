package com.BNMO.Object.NonFoodObjects.TableAndChair;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class TableAndChair extends NonFoodObjects {

    public TableAndChair(String name) {
        super(name, 3, 3, 50);
    }

    public void use(Time time, Sim sim) {
        if (!getIsOccupied()) {
            if (sim.getStatus().equals("Nothing")) {
                int duration = time.convertToSecond();
                setIsOccupied(true);
                sim.setStatus("Is eating");
                sim.setFullness(sim.getFullness() + (20 * (duration / 10)));
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
