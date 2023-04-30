package com.BNMO.Object;

import com.BNMO.Object.Food.Food;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.NonFoodObjects.Bed.*;
import com.BNMO.Object.NonFoodObjects.Stove.*;
import com.BNMO.Object.NonFoodObjects.TV.*;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Piano.Piano;
import com.BNMO.Object.NonFoodObjects.Clock.Clock;
import com.BNMO.Object.NonFoodObjects.AudioPlayer.*;
import com.BNMO.Object.NonFoodObjects.Book.*;

import java.util.ArrayList;

public abstract class Object {
    private String name;
    private String type;
    private String category;
    private int price;
    private static ArrayList<NonFoodObjects> buyableObjects;

    public Object(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return "Name: " + name + ", Type: " + type;
    }

    public boolean equals(Object o) {
        if (o instanceof Object) {
            Object object = (Object) o;
            return object.getName().equals(name) && object.getType().equals(type);
        }
        return false;
    }

    public static ArrayList<NonFoodObjects> getBuyableObjects() {
        buyableObjects.add(new SingleBed("Single Bed"));
        buyableObjects.add(new QueenBed("Queen Bed"));
        buyableObjects.add(new KingBed("King Bed"));
        buyableObjects.add(new GasStove("Gas Stove"));
        buyableObjects.add(new ElectricStove("Electric Stove"));
        buyableObjects.add(new TV("TV", new Channel("MTV", 1, "News")));
        buyableObjects.add(new TableAndChair("Table And Chair"));
        buyableObjects.add(new Piano("Piano"));
        buyableObjects.add(new Book("Book"));
        buyableObjects.add(new AudioPlayer("Audio Player"));
        buyableObjects.add(new Clock("Clock"));
        buyableObjects.add(new Journal("Journal"));
        buyableObjects.add(new Toilet("Toilet"));
        return buyableObjects;
    }

    public static void printBuyableObjects() {
        System.out.println("Buyable objects: ");
        int i = 0;
        for (Object object : getBuyableObjects()) {
            System.out.println(i + 1 + ". " + object.getType());
            i++;
        }
    }

}
