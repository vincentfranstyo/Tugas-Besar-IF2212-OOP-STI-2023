package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

import java.util.ArrayList;

public class Inventory<T extends Object> {
    private Sim owner;
    private ArrayList<T> objects;
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
            if (!listedObjs.contains(object.getType())) {
                listedObjs.add(object.getType());
                System.out.println(object.getCategory() + ": " + object.getType() + " x"
                        + getObjectNum(object.getClass().getName()));
            }
        }
    }

    public int getObjectNum(String className) {
        int num = 0;
        for (T object : objects) {
            if (object.getClass().getName().equals(className)) {
                num++;
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
}
