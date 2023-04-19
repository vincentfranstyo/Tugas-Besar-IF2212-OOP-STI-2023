package com.BNMO.NonFoodObjects.Toilet;

import com.BNMO.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;

public class Toilet extends NonFoodObjects {
    public Toilet(String name) {
        super(name, 1, 1, 50);
    }

    public void useToilet(Sim sim) {
        if (getIsOccupied()) {
            System.out.println("Toilet is occupied. Please wait.");
        } else {
            System.out.println("Toilet is free. Please use it.");
            setIsOccupied(true);
            sim.setStatus("Using toilet");
        }
    }
}
