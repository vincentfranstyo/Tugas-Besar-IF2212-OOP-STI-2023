package com.BNMO.Object.NonFoodObjects.Toilet;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Toilet extends NonFoodObjects {
    public Toilet(String name) {
        super(name, 1, 1, 50);
        this.setType("Toilet");
    }

    public void useToilet(Sim sim) {
        Time time = new Time(10);
        try {
            if (!getIsOccupied()) {
                if (sim.getStatus().equals("Nothing")) {
                    int duration = time.convertToSecond();
                    System.out.println(sim.getName() + " is using the toilet.");
                    setIsOccupied(true);
                    sim.setStatus("Using toilet");
                    Thread.sleep(duration * 1000L);
                    System.out.println(sim.getName() + " is done using the toilet.");
                    sim.setFullness(sim.getFullness() - (20 * (duration / 10)));
                    sim.setMood(sim.getMood() + (10 * (duration / 10)));
                } else {
                    System.out.println("You can't use the toilet while doing something else.");
                }
            }

            else {
                System.out.println("Too bad! The toilet is in use, please find another toilet!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setIsOccupied(false);
            sim.setStatus("Nothing");
        }
    }

    public void cleanToilet(Sim sim) {
        Time time = new Time(120);
        try {
            if (!getIsOccupied()) {
                if (sim.getStatus().equals("Nothing")) {
                    int duration = time.convertToSecond();
                    System.out.println(sim.getName() + " is cleaning the toilet.");
                    setIsOccupied(true);
                    sim.setStatus("Cleaning toilet");
                    Thread.sleep(duration * 1000L);
                    System.out.println(sim.getName() + " is done cleaning the toilet.");
                    sim.setMood(sim.getMood() + 20);
                    sim.setFullness((sim.getFullness() - (20 * (duration / 60))));
                } else {
                    System.out.println("You can't clean the toilet while doing something else.");
                }
            }

            else {
                System.out.println("Too bad! The toilet is in use, please find another toilet!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setIsOccupied(false);
            sim.setStatus("Nothing");
        }
    }
}
