package com.BNMO.Utilities;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;
import com.BNMO.Object.Food.Food;

import java.util.ArrayList;

public class Inventory<T extends Object> {
    private final Sim owner;
    private ArrayList<T> objects = new ArrayList<>();
    private boolean status;
    private int capacity;

    public Inventory(Sim owner) {
        this.owner = owner;
        this.objects = new ArrayList<>();
        this.status = false;
        this.capacity = 100;
    }

    public Sim getOwner() {
        return owner;
    }

    public ArrayList<T> getObjects() {
        return objects;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addObject(T object) {
        if (getCapacity() > 0) {
            objects.add(object);
            setCapacity(getCapacity() - 1);
        } else {
            System.out.println("Inventory is full");
        }
    }

    public void printInventory() {
        System.out.println("Inventory of " + getOwner().getName());
        ArrayList<String> listedObjs = new ArrayList<String>();
        for (T object : objects) {
            if (object.getCategory().equals("Non Food Objects")) {
                if (!listedObjs.contains(object.getType())) {
                    listedObjs.add(object.getType());
                    NonFoodObjects nfObject = (NonFoodObjects) object;
                    System.out.println(nfObject.getCategory() + ": " + nfObject.getType() + " x"
                            + getObjectNum(nfObject.getClass().getName()) + " ukuran: " + nfObject.getLength() + " x " + nfObject.getWidth());
                }
            } else {
                if (!listedObjs.contains(object.getName())) {
                    listedObjs.add(object.getName());
                    System.out.println(object.getType() + ": " + object.getName() + " x"
                            + getObjectNum(object.getName()));
                }
            }

        }
    }

    public int getObjectNum(String className) {
        int num = 0;
        for (T object : objects) {
            if (object.getCategory().equals("Non Food Objects")) {
                if (object.getClass().getName().equals(className)) {
                    num++;
                }
            } else {
                if (object.getName().equals(className)) {
                    num++;
                }
            }
        }
        return num;
    }

    public void removeObject(String name) {
        for (T object : objects) {
            if (object.getName().equals(name)) {
                objects.remove(object);
                setCapacity(getCapacity() + 1);
                break;
            }
        }
    }

    public ArrayList<Food> getFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        for (T object : objects) {
            if (object.getCategory().equals("Food")) {
                foods.add((Food) object);
            }
        }
        return foods;
    }

    public void printFoodList() {
        ArrayList<Food> foodList = getFoods();
        Food[] foods = foodList.toArray(new Food[foodList.size()]);
        for (Food fo : foods) {
            System.out.println(fo.getName());
        }
    }

    public Food getFood(String foodName) {
        ArrayList<Food> foodList = getFoods();
        Food[] foods = foodList.toArray(new Food[foodList.size()]);
        for (Food fo : foods) {
            if (fo.getName().toLowerCase().replaceAll("\\s+", "").equals(foodName)) {
                return fo;
            }
        }
        return null;
    }
}
