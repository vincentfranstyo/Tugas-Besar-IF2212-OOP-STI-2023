package com.BNMO.Object.NonFoodObjects.Bed;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.NonFoodObjects.Bed.Bed;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class QueenBed extends NonFoodObjects implements Bed {
    private String bedType = "Queen Bed";
    private int capacity = 2;
    private int spaceLeft;

    public QueenBed(String name) {
        super(name, 4, 2, 100);
    }

    public String getType() {
        return bedType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpaceLeft() {
        return spaceLeft;
    }

    public void setSpaceLeft(int spaceLeft) {
        this.spaceLeft = spaceLeft;
    }

    @Override
    public void sleep(Time time, Sim sim) {
        if (getSpaceLeft() > 0) {
            System.out.println("The " + sim.getName() + " is now sleeping.");
            sim.setStatus("Sleeping");
            int duration = time.convertToSecond();
            // wait for duration
            setSpaceLeft(getSpaceLeft() - 1);
            sim.setMood(sim.getMood() + (30 * (duration / 240)));
            sim.setHealth(sim.getHealth() + (20 * (duration / 240)));
            System.out.println("The " + sim.getName() + " is now awake.");

        } else {
            System.out.println("The " + sim.getName() + " cannot sleep on the " + getName() + " because it is full.");
        }
    }

}