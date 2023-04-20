package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;
import com.BNMO.Object.Object;

import java.util.ArrayList;

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

    public void removeObject(Object object) {
        objects.remove(object);
        setCapacity(getCapacity() + 1);
    }

    public void printInventory() {
        System.out.println("Inventory of " + getOwner().getName());
        for (Object object : objects) {
            System.out.println(object.getName());
        }
    }

}
