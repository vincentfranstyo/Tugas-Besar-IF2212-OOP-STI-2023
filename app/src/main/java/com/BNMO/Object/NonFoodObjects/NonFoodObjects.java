package com.BNMO.Object.NonFoodObjects;

import com.BNMO.Object.Object;
import com.BNMO.Utilities.*;

public abstract class NonFoodObjects extends Object {
    private int length;
    private int width;
    private boolean isOccupied;
    private Point position;

    public NonFoodObjects(String name, int length, int width, int price) {
        super(name, "Non Food Objects");
        this.length = length;
        this.width = width;
        this.setPrice(price);
        this.isOccupied = false;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public Point getPosition() {
        return position;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}