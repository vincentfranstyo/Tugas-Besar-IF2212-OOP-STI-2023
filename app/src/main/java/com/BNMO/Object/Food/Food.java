package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

import java.util.ArrayList;

public abstract class Food extends Object {
    private int satiety;
    private ArrayList<String> dishesList;
    private ArrayList<String> ingredientList;

    public Food(String name) {
        super(name, "Food");
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public ArrayList<String> getDishes() {
        dishesList.add("Nasi Ayam");
        dishesList.add("Nasi Kari");
        dishesList.add("Susu Kacang");
        dishesList.add("Tumis Sayur");
        dishesList.add("Bistik");
        return dishesList;
    }

    public ArrayList<String> getIngredientList() {
        ingredientList.add("Nasi");
        ingredientList.add("Kentang");
        ingredientList.add("Ayam");
        ingredientList.add("Wortel");
        ingredientList.add("Bayam");
        ingredientList.add("Kacang");
        ingredientList.add("Susu");
        return ingredientList;
    }
}
