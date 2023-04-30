package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

import java.util.ArrayList;

public abstract class Food extends Object {
    private int satiety;
    private static ArrayList<String> dishesList;
    private static ArrayList<String> ingredientList;

    public Food(String name) {
        super(name, "Food");
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public static ArrayList<String> getDishes() {
        dishesList.add("Nasi Ayam");
        dishesList.add("Nasi Kari");
        dishesList.add("Susu Kacang");
        dishesList.add("Tumis Sayur");
        dishesList.add("Bistik");
        return dishesList;
    }

    public static void printDishes() {
        for (int i = 0; i < getDishes().size(); i++) {
            System.out.println(i + 1 + ". " + getDishes().get(i));
        }
    }

    public static ArrayList<String> getIngredientList() {
        ingredientList.add("Nasi");
        ingredientList.add("Kentang");
        ingredientList.add("Ayam");
        ingredientList.add("Wortel");
        ingredientList.add("Bayam");
        ingredientList.add("Kacang");
        ingredientList.add("Susu");
        return ingredientList;
    }

    public static void printIngredients() {
        for (int i = 0; i < getIngredientList().size(); i++) {
            System.out.println(i + 1 + ". " + getIngredientList().get(i));
        }
    }
}
