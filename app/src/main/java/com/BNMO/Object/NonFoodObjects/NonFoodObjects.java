package com.BNMO.Object.NonFoodObjects;

import com.BNMO.Object.Object;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public abstract class NonFoodObjects extends Object {
    private String name;
    private int length;
    private int width;
    private int price;
    private boolean isOccupied;

    public NonFoodObjects(String name, int length, int width, int price) {
        super("NonFoodObject");
        this.name = name;
        this.length = length;
        this.width = width;
        this.price = price;
        this.isOccupied = false;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getPrice() {
        return price;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}