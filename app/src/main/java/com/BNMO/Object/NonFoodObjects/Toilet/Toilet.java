package com.BNMO.Object.NonFoodObjects.Toilet;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Toilet extends NonFoodObjects {
    public Toilet(String name, Point position) {
        super(name, 1, 1, 50, position);
    }

    public void useToilet(Time time, Sim sim) {
        try {
            if (!getIsOccupied()) {
                if (sim.getStatus().equals("Nothing")) {
                    int duration = time.convertToSecond();
                    System.out.println(sim.getName() + " is using the toilet.");
                    setIsOccupied(true);
                    sim.setStatus("Using toilet");
                    sim.setFullness(sim.getFullness() - (20 * (duration / 10)));
                    sim.setMood(sim.getMood() + (10 * (duration / 10)));
                    Thread.sleep(time.convertToSecond() * 1000);
                } else {
                    System.out.println("You can't use the toilet while doing something else.");
                }
            }

            else {
                System.out.println("Too bad! The toilet is in use, please find another toilet!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
