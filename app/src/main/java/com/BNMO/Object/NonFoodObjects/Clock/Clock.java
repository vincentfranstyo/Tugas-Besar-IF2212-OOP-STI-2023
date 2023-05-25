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
        Menu menu = Menu.getInstance();
        DayThread dayThread = DayThread.getInstance();
        System.out.println("Today's clock");
        dayThread.timeLeftForTheDay();
        System.out.println();
        System.out.println("Durasi pembangunan " + menu.getCurrentSim().getName() + " tersisa: " + menu.getCurrentSim().getBuildingCountTime() + " detik");
        System.out.println();
        System.out.println("Durasi pembelian barang " + menu.getCurrentSim().getName() + " tersisa: " + menu.getCurrentSim().getBuyingCountTime() + " detik");
        System.out.println();

    }
}
