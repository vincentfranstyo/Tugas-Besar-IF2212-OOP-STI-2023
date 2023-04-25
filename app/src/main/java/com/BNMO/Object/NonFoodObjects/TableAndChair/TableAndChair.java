package com.BNMO.Object.NonFoodObjects.TableAndChair;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Food.Dishes;
import com.BNMO.Object.Food.Ingredients;

public class TableAndChair extends NonFoodObjects {

    public TableAndChair(String name, Point position) {
        // input : name, x, y -> create position
        super(name, 3, 3, 50, position);
    }

    public void eatDish(Time time, Sim sim, Dishes dish) {
        Thread eatThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!getIsOccupied()) {
                    if (sim.getStatus().equals("Nothing")) {
                        int duration = time.convertToSecond();
                        Thread.sleep(duration * 1000);
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
        });

        eatThread.start();
        try {
            eatThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eatIngredients(Time time, Sim sim, Ingredients ing) {
        Thread eatThread = new Thread(new Runnable(){
            @Override
            public void run(){
                if (!getIsOccupied()) {
                    if (sim.getStatus().equals("Nothing")) {
                        int duration = time.convertToSecond();
                        Thread.sleep(duration * 1000);
                        setIsOccupied(true);
                        sim.setStatus("Eating");
                        System.out.println("You are eating " + ing.getName());
                        sim.setFullness(sim.getFullness() + (ing.getSatiety() * (duration / 30)));
                    } else {
                        System.out.println("You can't eat while doing something else.");
                    }
                }
                else {
                    System.out.println("Too bad! The table is in use, please find another table!");
                }
            }
        });

        eatThread.start();
        try {
            eatThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

}
