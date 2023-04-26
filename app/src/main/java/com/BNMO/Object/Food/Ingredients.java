package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

public class Ingredients extends Object {
    private int satiety;

    public Ingredients(String name, int price, int satiety) {
        super(name, "Ingredient");
    }

    public int getSatiety() {
        return satiety;
    }
}
