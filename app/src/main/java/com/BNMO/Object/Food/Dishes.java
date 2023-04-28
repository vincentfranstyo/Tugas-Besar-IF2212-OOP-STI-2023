package com.BNMO.Object.Food;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

import java.util.ArrayList;

public class Dishes extends Object implements Food {
    private ArrayList<Ingredients> ingredients;
    private int cookingTime;
    private int satiety;

    public Dishes(String name) {
        super(name, "Food");
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

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public boolean checkIngredients(Sim sim) {
        boolean hasIngredients = true;
        for (int i = 0; i < getIngredients().size(); i++) {
            if (!sim.getInventory().getObjects().contains(getIngredients().get(i))) {
                hasIngredients = false;
            }
        }
        return hasIngredients;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
}
