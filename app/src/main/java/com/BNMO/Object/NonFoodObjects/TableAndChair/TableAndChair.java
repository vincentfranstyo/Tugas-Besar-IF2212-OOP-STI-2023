package com.BNMO.Object.NonFoodObjects.TableAndChair;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Food.Dishes;
import com.BNMO.Object.Food.Ingredients;

public class TableAndChair extends NonFoodObjects {

    public TableAndChair(String name) {
        // input : name, x, y -> create position
        super(name, 3, 3, 50);
        this.setType("Table and Chair");
    }

    public void eat(Sim sim, Dishes dish, Ingredients ing) {
        Time time = new Time(30);

        if (!getIsOccupied()) {
            if (sim.getStatus().equals("Nothing")) {
                if (dish != null) {
                    if (sim.getInventory().getObjects().contains(dish)) {
                        try {
                            int duration = time.convertToSecond();
                            setIsOccupied(true);
                            sim.setStatus("Is eating");
                            System.out.println(sim.getName() + " are eating " + dish.getName());
                            sim.getInventory().removeObject(dish.getName());
                            Thread.sleep(duration * 1000);
                            System.out.println(sim.getName() + " are done eating " + dish.getName());
                            sim.setFullness(sim.getFullness() + (dish.getSatiety() * (duration / 30)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            setIsOccupied(false);
                        }
                    } else {
                        System.out.println("You don't have " + dish.getName() + " in your inventory.");
                    }
                } else {
                    if (sim.getInventory().getObjects().contains(ing)) {
                        try {
                            int duration = time.convertToSecond();
                            setIsOccupied(true);
                            sim.setStatus("Is eating");
                            System.out.println(sim.getName() + " are eating " + ing.getName());
                            sim.getInventory().removeObject(ing.getName());
                            Thread.sleep(duration * 1000);
                            System.out.println(sim.getName() + " are done eating " + ing.getName());
                            sim.setFullness(sim.getFullness() + (ing.getSatiety() * (duration / 30)));
                            setIsOccupied(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("You don't have " + ing.getName() + " in your inventory.");
                    }
                }
            } else {
                System.out.println("You can't eat while doing something else.");
            }
        } else {
            System.out.println("Too bad! The table is in use, please find another table!");
        }
    }
}
