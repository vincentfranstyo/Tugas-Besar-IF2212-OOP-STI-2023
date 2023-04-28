package com.BNMO.Object.Food;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

public class Dishes extends Object {
    private Ingredients[] ingredients;
    private int cookingTime;
    private int satiety;

    public Dishes(String name, Ingredients[] ingredients, int price, int cookingTime, int satiety) {
        super(name, "Food");
        this.setPrice(price);
        this.setType("Dishes");
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.satiety = satiety;
    }

    public Ingredients[] getIngredients() {
        return ingredients;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public boolean checkIngredients(Sim sim) {
        boolean hasIngredients = true;
        for (int i = 0; i < getIngredients().length; i++) {
            if (!sim.getInventory().getObjects().contains(getIngredients()[i])) {
                hasIngredients = false;
            }
        }
        return hasIngredients;
    }

    public int getSatiety() {
        return satiety;
    }
}
