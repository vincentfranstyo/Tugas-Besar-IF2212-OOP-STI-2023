package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

public abstract class Food extends Object {
    private int satiety;

    public Food(String name) {
        super(name, "Food");

    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
}
