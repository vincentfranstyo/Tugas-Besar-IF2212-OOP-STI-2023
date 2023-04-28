package com.BNMO;

import com.BNMO.Buildings.House;
import com.BNMO.Utilities.*;
import com.BNMO.SIMS.Sim;

import java.util.*;

public class World {
    private int length;
    private int width;
    private ArrayList<House> houseList;
    private static int totalBuild = 0;

    public World(Sim initSim) {
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<House>();

        Random rand = new Random();
        int xRandom = rand.nextInt(65);
        int yRandom = rand.nextInt(65);

        Point initPoint = new Point(xRandom, yRandom);
        House initHouse = new House(initPoint, initSim);
        addHouse(initHouse);
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public void addHouse(House rumah) {
        for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).getLocation() == rumah.getLocation()
                    && houseList.get(i).getLocation() == rumah.getLocation()) {
                System.out.println("Sudah ada rumah di lokasi tersebut, silakan pilih lokasi lain");
                return;
            }
        }
        houseList.add(rumah);
        totalBuild++;
    }

    public void removeHouse(House house) {
        for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).getOwner() == house.getOwner()
                    && houseList.get(i).getLocation() == house.getLocation()) {
                houseList.remove(i);
            }
        }
        totalBuild--;
    }

    public int getTotalBuild() {
        return totalBuild;
    }

}
