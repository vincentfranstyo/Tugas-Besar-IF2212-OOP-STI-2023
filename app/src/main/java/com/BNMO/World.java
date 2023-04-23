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

    public World(){
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<House>();

    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public void addHouse(Point loc, Sim owner){
        House rumah = new House(loc,owner);
        houseList.add(rumah);
        totalBuild ++;
    }

    public void removeHouse(House house){
        totalBuild --;
    }

    public int getTotalBuild(){
        return totalBuild;
    }


}
