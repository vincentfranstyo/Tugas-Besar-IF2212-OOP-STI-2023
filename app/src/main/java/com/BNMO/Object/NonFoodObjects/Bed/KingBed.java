package com.BNMO.Object.NonFoodObjects.Bed;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class KingBed extends NonFoodObjects implements Bed {
    private int capacity = 2;
    private int spaceLeft;

    public KingBed(String name) {
        super(name, 5, 2, 150);
        this.setType("King Bed");
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
                System.out.println("The " + sim.getName() + " is now sleeping.");
                sim.setStatus("Sleeping");
                int duration = time.convertToSecond();
                setSpaceLeft(getSpaceLeft() - 1);
                sim.setMood(sim.getMood() + (30 * (duration / 240)));
                sim.setHealth(sim.getHealth() + (20 * (duration / 240)));
                Thread.sleep(duration * 1000);
                System.out.println("The " + sim.getName() + " is now awake.");
            } else {
                System.out.println(
                        "The " + sim.getName() + " cannot sleep on the " + getName() + " because it is full.");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}