package com.BNMO.Object.NonFoodObjects.Bed;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.NonFoodObjects.Bed.Bed;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class SingleBed extends NonFoodObjects implements Bed {
    private String bedType = "Single Bed";
    private int capacity = 1;
    private int spaceLeft;

    public SingleBed(String name) {
        super(name, 4, 1, 50);
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