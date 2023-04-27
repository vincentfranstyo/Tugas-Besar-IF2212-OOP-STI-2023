package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

public class Ingredients extends Object {
    private int satiety;

    public Ingredients(String name, int price, int satiety) {
        super(name, "Ingredient");
        this.setPrice(price);
    }

    public int getSatiety() {
        return satiety;
    }
}
