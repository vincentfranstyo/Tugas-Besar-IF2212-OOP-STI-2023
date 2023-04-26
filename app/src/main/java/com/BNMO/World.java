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

    public World(String initSimName) {
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<House>();
        Sim newSim = new Sim(initSimName);

        Random rand = new Random();
        int xRandom = rand.nextInt(65);
        int yRandom = rand.nextInt(65);

        Point initPoint = new Point(xRandom, yRandom);

        addHouse(initPoint, newSim);
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

    public void addHouse(Point loc, Sim owner) {
        House rumah = new House(loc, owner);
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
