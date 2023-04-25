package com.BNMO.Object.Food;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

public class Dishes extends Object {
    private int price;
    private Recipe recipe;

    public Dishes(String name, int price, int cookingTime, Recipe recipe) {
        super(name, "Dish");
        this.price = price;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean checkIngredients(Sim sim) {
        boolean hasIngredients = true;
        for (int i = 0; i < recipe.getIngredients().length; i++) {
            if (!sim.getInventory().contains(recipe.getIngredients()[i].getName())) {
                hasIngredients = false;
            }
        }

        return hasIngredients;
    }
}
