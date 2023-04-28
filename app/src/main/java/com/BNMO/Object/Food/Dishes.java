package com.BNMO.Object.Food;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

public class Dishes extends Object {
    private Recipe recipe;
    private int satiety;

    public Dishes(String name, int price, int cookingTime, Recipe recipe, int satiety) {
        super(name, "Dish");
        this.setPrice(price);
        this.recipe = recipe;
        this.satiety = satiety;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean checkIngredients(Sim sim) {
        boolean hasIngredients = true;
        for (int i = 0; i < recipe.getIngredients().length; i++) {
            if (!sim.getInventory().getObjects().contains(recipe.getIngredients()[i])) {
                hasIngredients = false;
            }
        }
        return hasIngredients;
    }

    public int getSatiety() {
        return satiety;
    }
}
