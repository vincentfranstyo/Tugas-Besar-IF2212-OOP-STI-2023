package com.BNMO.Object.NonFoodObjects.Bed;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class SingleBed extends NonFoodObjects implements Bed {
    private int capacity = 1;
    private int spaceLeft;

    public SingleBed(String name) {
        super(name, 4, 1, 50);
        this.setType("Single Bed");
        this.setSpaceLeft(capacity);
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
        try {
            if (getSpaceLeft() > 0) {
                System.out.println(sim.getName() + " is now sleeping.");
                System.out.println();
                sim.setStatus("Sleeping");
                int duration = time.convertToSecond();
                setSpaceLeft(getSpaceLeft() - 1);
                Thread.sleep(duration * 1000);
                System.out.println(sim.getName() + " is now awake.");
                sim.setMood(sim.getMood() + (30 * (duration / 240)));
                sim.setHealth(sim.getHealth() + (20 * (duration / 240)));
                setSpaceLeft(getSpaceLeft() + 1);
                System.out.println();
            } else {
                System.out.println(sim.getName() + " cannot sleep on the " + getName() + " because it is full.");
                System.out.println();
            }
        } catch (InterruptedException e) {
            System.out.println("SleepThread interrupted");
        } finally {
            sim.setStatus("Nothing");
        }
    }
}
