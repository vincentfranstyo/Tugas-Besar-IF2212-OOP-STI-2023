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
        if (xRandom == 0){
            xRandom++;
        }
        else if (xRandom % 3 == 0){
            xRandom--;
        }

        int yRandom = rand.nextInt(65);
        if (yRandom == 0){
            yRandom++;
        }
        else if (yRandom % 5 == 0){
            yRandom--;
        }

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
            if (houseList.get(i).getLocation().getX() == rumah.getLocation().getX()
                    && houseList.get(i).getLocation().getY() == rumah.getLocation().getY()) {
                System.out.println("Sudah ada rumah di lokasi tersebut, silakan pilih lokasi lain");
                return;
            }
            else if (rumah.getLocation().getX() % 3 == 0){
                System.out.println("Rumah berada di jalan raya, silakan pilih lokasi lain");
                return;
            }
            else if (rumah.getLocation().getY() % 5 == 0){
                System.out.println("Rumah berada di jalan raya, silakan pilih lokasi lain");
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
