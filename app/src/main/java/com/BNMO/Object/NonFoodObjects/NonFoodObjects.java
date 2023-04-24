package com.BNMO.Object.NonFoodObjects;

import com.BNMO.Object.Object;
import com.BNMO.Utilities.*;

public abstract class NonFoodObjects extends Object {
    private int length;
    private int width;
    private int price;
    private boolean isOccupied;
    private Point position;

    public NonFoodObjects(String name, int length, int width, int price, Point position) {
        super(name, "NonFoodObject");
        this.length = length;
        this.width = width;
        this.price = price;
        this.isOccupied = false;
        this.position = position;
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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}