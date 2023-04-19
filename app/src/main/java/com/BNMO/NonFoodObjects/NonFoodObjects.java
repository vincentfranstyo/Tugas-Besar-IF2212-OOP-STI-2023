package com.BNMO.NonFoodObjects;

public abstract class NonFoodObjects {
    private String name;
    private int length;
    private int width;
    private int price;

    public NonFoodObjects(String name, int length, int width, int price) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.price = price;
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
}