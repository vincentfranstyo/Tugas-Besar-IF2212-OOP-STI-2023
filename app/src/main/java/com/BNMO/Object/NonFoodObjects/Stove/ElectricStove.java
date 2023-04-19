package com.BNMO.Object.NonFoodObjects.Stove;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;
import com.BNMO.Object.Dishes;
import com.BNMO.Object.Ingredients;

public class ElectricStove extends NonFoodObjects {
    private boolean currentState;

    public ElectricStove(String name) {
        super(name, 1, 1, 200);
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

    public void cookDish(Sim sim, Dishes dish) {
        if (!dish.checkIngredients(sim)) {
            System.out.println("You do not have the ingredients to cook this dish.");
        } else {
            if (getCurrentState()) {
                System.out.println("The " + dish.getName() + " is now cooking.");
                Time.sleep(1000);
                System.out.println("The " + dish.getName() + " is now done cooking.");
            } else {
                System.out.println("The gas stove is off. Please turn it on.");
            }
        }
    }

}
