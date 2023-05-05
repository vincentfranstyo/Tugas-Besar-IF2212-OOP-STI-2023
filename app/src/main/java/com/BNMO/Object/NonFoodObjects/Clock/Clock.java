package com.BNMO.Object.NonFoodObjects.Clock;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Utilities.*;

public class Clock extends NonFoodObjects {

    public Clock(String name) {
        super(name, 1, 1, 10);
        this.setType("Clock");
    }

    public static void use() {
        // show all ongoing action along with the duration left
        DayThread dayThread = DayThread.getInstance();
        System.out.println("Today's clock");
        dayThread.timeLeftForTheDay();
        System.out.println();
        System.out.println("Durasi pembangunan tersisa: " + dayThread.getBuildingCountTime() + " menit");
        System.out.println();
        System.out.println("Durasi pembelian barang tersisa: " + dayThread.getBuyingCountTime() + " menit");
        System.out.println();

    }
}
