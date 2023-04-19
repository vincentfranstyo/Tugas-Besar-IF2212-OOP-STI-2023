package com.BNMO.NonFoodObjects.TableAndChair;

import com.BNMO.NonFoodObjects.NonFoodObjects;

public class TableAndChair extends NonFoodObjects {

    public TableAndChair(String name) {
        super(name, 3, 3, 50);
    }

    public void useTableAndChair(Sim sim) {
        if (getIsOccupied()) {
            System.out.println("Table and chair is occupied. Please wait.");
        } else {
            System.out.println("Table and chair is free. Please use it.");
            setIsOccupied(true);
            sim.setStatus("Using table and chair");
        }
    }
}
