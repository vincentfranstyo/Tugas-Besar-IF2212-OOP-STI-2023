package com.BNMO.Object.Food;

import com.BNMO.SIMS.Sim;

import java.util.ArrayList;

public class Dishes extends Food {
    private final ArrayList<Ingredients> ingredients;
    private final int cookingTime;

    public Dishes(String name) {
        super(name);
        this.ingredients = new ArrayList<>();
        if (getName().equals("Nasi Ayam")) {
            this.setSatiety(16);
            this.ingredients.add(new Ingredients("Nasi"));
            this.ingredients.add(new Ingredients("Ayam"));
        } else if (getName().equals("Nasi Kari")) {
            this.setSatiety(30);
            this.ingredients.add(new Ingredients("Nasi"));
            this.ingredients.add(new Ingredients("Ayam"));
            this.ingredients.add(new Ingredients("Kentang"));
            this.ingredients.add(new Ingredients("Wortel"));
            this.ingredients.add(new Ingredients("Sapi"));
        } else if (getName().equals("Susu Kacang")) {
            this.setSatiety(5);
            this.ingredients.add(new Ingredients("Susu"));
            this.ingredients.add(new Ingredients("Kacang"));
        } else if (getName().equals("Tumis Sayur")) {
            this.setSatiety(5);
            this.ingredients.add(new Ingredients("Wortel"));
            this.ingredients.add(new Ingredients("Bayam"));
        } else if (getName().equals("Bistik")) {
            this.setSatiety(22);
            this.ingredients.add(new Ingredients("Kentang"));
            this.ingredients.add(new Ingredients("Sapi"));
        }
        this.setType("Dishes");
        this.cookingTime = this.getSatiety() * 3 / 2;
    }

    public ArrayList<Ingredients> getCurrentDishIngredients() {
        return ingredients;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public boolean checkIngredients(Sim sim) {
        boolean hasIngredients = true;
        for (int i = 0; i < getCurrentDishIngredients().size(); i++) {
            if (sim.getInventory().getFood(getCurrentDishIngredients().get(i).getName().toLowerCase().replaceAll("\\s+", "")) == null) {
                hasIngredients = false;
                break;
            }
        }
        return hasIngredients;
    }
}
