package com.BNMO.Object.NonFoodObjects.Stove;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Food.Dishes;

public class ElectricStove extends NonFoodObjects implements Stove {

    public ElectricStove(String name) {
        super(name, 1, 1, 200);
        this.setType("Electric Stove");
    }

    // public boolean getCurrentState() {
    // return currentState;
    // }

    // public void setCurrentState(boolean currentState) {
    // this.currentState = currentState;
    // }

    // public void turnOn() {
    // if (!getCurrentState()) {
    // setCurrentState(true);
    // System.out.println("The electric stove is now on.");
    // } else {
    // System.out.println("The electric stove is already on.");
    // }
    // }

    // public void turnOff() {
    // if (getCurrentState()) {
    // setCurrentState(false);
    // System.out.println("The electric stove is now off.");
    // } else {
    // System.out.println("The electric stove is already off.");
    // }
    // }

    @Override
    public void cookDish(Sim sim, Dishes dish) {
        try {
            if (!dish.checkIngredients(sim)) {
                System.out.println("You do not have the ingredients to cook this dish.");
            } else {
                for (int i = 0; i < dish.getCurrentDishIngredients().size(); i++) {
                    sim.getInventory().removeObject(dish.getCurrentDishIngredients().get(i).getName());
                }
                setIsOccupied(true);
                System.out.println(sim.getName() + " is cooking the " + dish.getName() + ".");
                sim.setStatus("Cooking " + dish.getName());
                int duration = dish.getCookingTime();
                Thread.sleep(duration * 1000L);
                System.out.println("The " + dish.getName() + " is done.");
                sim.setMood(sim.getMood() + 10);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        } finally {
            setIsOccupied(false);
            sim.setStatus("Nothing");
        }
    }

}
