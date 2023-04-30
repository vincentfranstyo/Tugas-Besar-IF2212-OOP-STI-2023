package com.BNMO.Object.NonFoodObjects.Stove;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Food.Dishes;

// ini ku ganti ya bukan abstract class soalnya
public class GasStove extends NonFoodObjects implements Stove {
    public GasStove(String name) {
        super(name, 2, 1, 100);
        this.setType("Gas Stove");
    }

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
                int duration = dish.getCookingTime();
                Thread.sleep(duration * 1000);
                System.out.println("The " + dish.getName() + " is done.");
                sim.setStatus("Cooking " + dish.getName());
                sim.setMood(sim.getMood() + 10);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        } finally {
            setIsOccupied(false);
        }
    }
}
