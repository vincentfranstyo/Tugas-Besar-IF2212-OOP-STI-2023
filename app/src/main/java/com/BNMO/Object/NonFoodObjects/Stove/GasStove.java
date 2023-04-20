package com.BNMO.Object.NonFoodObjects.Stove;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Food.Dishes;
import com.BNMO.Object.Food.Ingredients;

public abstract class GasStove extends NonFoodObjects implements Stove {
    private boolean currentState;
    private String stoveType = "Gas Stove";

    public GasStove(String name, Point position) {
        super(name, 2, 1, 100, position);
        this.currentState = false;
    }

    public boolean getCurrentState() {
        return currentState;
    }

    public void setCurrentState(boolean currentState) {
        this.currentState = currentState;
    }

    public void turnOn() {
        if (!getCurrentState()) {
            setCurrentState(true);
            System.out.println("The gas stove is now on.");
        } else {
            System.out.println("The gas stove is already on.");
        }
    }

    public void turnOff() {
        if (getCurrentState()) {
            setCurrentState(false);
            System.out.println("The gas stove is now off.");
        } else {
            System.out.println("The gas stove is already off.");
        }
    }

    @Override
    public void cookDish(Sim sim, Dishes dish) {
        if (!dish.checkIngredients(sim)) {
            System.out.println("You do not have the ingredients to cook this dish.");
        } else {
            if (getCurrentState()) {
                System.out.println("The " + dish.getName() + " is now cooking.");
                int duration = dish.getRecipe().getDuration(); // to be checked again
                // wait for duration
                sim.setStatus("Cooking " + dish.getName());
                sim.setMood(sim.getMood() + 10);
                System.out.println("The " + dish.getName() + " is done.");
            } else {
                System.out.println("The gas stove is off. Please turn it on.");
            }
        }
    }
}
