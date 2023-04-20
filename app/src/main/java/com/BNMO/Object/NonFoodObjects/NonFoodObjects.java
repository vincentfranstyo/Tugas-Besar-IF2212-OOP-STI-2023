package com.BNMO.Object.NonFoodObjects;

import com.BNMO.Object.Object;
import com.BNMO.Utilities.*;

public abstract class NonFoodObjects extends Object {
    private String name;
    private int length;
    private int width;
    private int price;
    private boolean isOccupied;
    private Point position;

    public NonFoodObjects(String name, int length, int width, int price, Point position) {
        super("NonFoodObject");
        this.name = name;
        this.length = length;
        this.width = width;
        this.price = price;
        this.isOccupied = false;
        this.position = position;
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