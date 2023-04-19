package com.BNMO.Object.NonFoodObjects.TableAndChair;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class TableAndChair extends NonFoodObjects {

    public TableAndChair(String name) {
        super(name, 3, 3, 50);
    }

    public void eatDish(Time time, Sim sim, Dishes dish) {
        if (!getIsOccupied()) {
            if (sim.getStatus().equals("Nothing")) {
                int duration = time.convertToSecond();
                setIsOccupied(true);
                sim.setStatus("Is eating");
                System.out.println("You are eating " + dish.getName());
                sim.setFullness(sim.getFullness() + (dish.getSatiety() * (duration / 30)));
            } else {
                System.out.println("You can't eat while doing something else.");
            }
        }

        else {
            System.out.println("Too bad! The table is in use, please find another table!");
        }
    }

    public void eatDish(Time time, Sim sim, Ingredient ing) {
        if (!getIsOccupied()) {
            if (sim.getStatus().equals("Nothing")) {
                int duration = time.convertToSecond();
                setIsOccupied(true);
                sim.setStatus("Is eating");
                System.out.println("You are eating " + ing.getName()");
                sim.setFullness(sim.getFullness() + (ing.getSatiety() * (duration / 30)));
            } else {
                System.out.println("You can't eat while doing something else.");
            }
        }

        else {
            System.out.println("Too bad! The table is in use, please find another table!");
        }
    }

}
