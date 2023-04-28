package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {
    private Sim owner;
    private ArrayList<Object> objects;
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

    public ArrayList<Object> getObjects() {
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

    public void addObject(Object object) {
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
        for (Object object : objects) {
            if (!listedObjs.contains(object.getType())) {
                listedObjs.add(object.getType());
                System.out.println(object.getCategory() + ": " + object.getType() + " x"
                        + getObjectNum(object.getClass().getName()));
            }
        }
    }

    public int getObjectNum(String className) {
        int num = 0;
        for (Object object : objects) {
            if (object.getClass().getName().equals(className)) {
                num++;
            }
        }
        return num;
    }

    public boolean contains(String name) {
        for (Object object : objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void removeObject(String name) {
        for (Object object : objects) {
            if (object.getName().equals(name)) {
                objects.remove(object);
                setCapacity(getCapacity() + 1);
                break;
            }
        }
    }

}
