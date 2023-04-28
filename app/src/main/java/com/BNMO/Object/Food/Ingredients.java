package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

public class Ingredients extends Object implements Food {
    private int satiety;

    public Ingredients(String name, int price, int satiety) {
        super(name, "Food");
        this.setType("Ingredients");
        this.setPrice(price);
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
}
