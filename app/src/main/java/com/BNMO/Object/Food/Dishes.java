package com.BNMO.Object.Food;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

import java.util.ArrayList;

public class Dishes extends Object implements Food {
    private ArrayList<Ingredients> ingredients;
    private int cookingTime;
    private int satiety;

    public Dishes(String name, ArrayList<Ingredients> ingredients, int cookingTime, int satiety) {
        super(name, "Food");
        if (getName().equals("Nasi Ayam")) {
            this.satiety = 16;
        } else if (getName().equals("Nasi Kari")) {
        } else if (getName().equals("Susu Kacang")) {

        } else if (getName().equals("Tumis Sayur")) {

        } else if (getName().equals("Bistik")) {
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
