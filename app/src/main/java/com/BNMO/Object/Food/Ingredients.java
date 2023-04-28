package com.BNMO.Object.Food;

import com.BNMO.Object.Object;

public class Ingredients extends Object implements Food {
    private int satiety;

    public Ingredients(String name) {
        super(name, "Food");
        this.setType("Ingredients");
        if (getName().equals("Nasi")) {
            this.setPrice(5);
            this.setSatiety(5);
        } else if (getName().equals("Kentang")) {
            this.setPrice(3);
            this.setSatiety(4);
        } else if (getName().equals("Ayam")) {
            this.setPrice(10);
            this.setSatiety(8);
        } else if (getName().equals("Sapi")) {
            this.setPrice(12);
            this.setSatiety(15);
        } else if (getName().equals("Wortel")) {
            this.setPrice(3);
            this.setSatiety(2);
        } else if (getName().equals("Bayam")) {
            this.setPrice(3);
            this.setSatiety(2);
        } else if (getName().equals("Kacang")) {
            this.setPrice(2);
            this.setSatiety(2);
        } else if (getName().equals("Susus")) {
            this.setPrice(2);
            this.setSatiety(1);
        }
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
}
