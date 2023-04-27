package com.BNMO.Object.NonFoodObjects.Stove;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Food.Dishes;

public class ElectricStove extends NonFoodObjects implements Stove {
    private boolean currentState;
    private String stoveType = "Electric Stove";

    public ElectricStove(String name, Point position) {
        super(name, 1, 1, 200, position);
        this.currentState = false;
    }

    public boolean getCurrentState() {
        return currentState;
    }

    public String getStoveType() {
        return stoveType;
    }

    public void setCurrentState(boolean currentState) {
        this.currentState = currentState;
    }

    public void turnOn() {
        if (!getCurrentState()) {
            setCurrentState(true);
            System.out.println("The electric stove is now on.");
        } else {
            System.out.println("The electric stove is already on.");
        }
    }

    public void turnOff() {
        if (getCurrentState()) {
            setCurrentState(false);
            System.out.println("The electric stove is now off.");
        } else {
            System.out.println("The electric stove is already off.");
        }
    }

    @Override
    public void cookDish(Sim sim, Dishes dish) {
        try {
            if (!dish.checkIngredients(sim)) {
                System.out.println("You do not have the ingredients to cook this dish.");
            } else {
                if (getCurrentState()) {
                    for (int i = 0; i < dish.getRecipe().getIngredients().length; i++) {
                        sim.getInventory().removeObject(dish.getRecipe().getIngredients()[i].getName());
                    }
                    System.out.println(sim.getName() + " is cooking the " + dish.getName() + ".");
                    int duration = dish.getRecipe().getCookingTime();
                    sim.setStatus("Cooking " + dish.getName());
                    sim.setMood(sim.getMood() + 10);
                    Thread.sleep(duration * 1000);
                    System.out.println("The " + dish.getName() + " is done.");
                } else {
                    System.out.println("The electric stove is off. Please turn it on.");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

}
