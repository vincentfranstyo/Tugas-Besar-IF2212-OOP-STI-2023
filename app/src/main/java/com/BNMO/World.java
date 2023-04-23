package com.BNMO;

import com.BNMO.Buildings.House;
import com.BNMO.Utilities.Point;

import java.util.ArrayList;

public class World {
    private int length;
    private int width;
    private ArrayList<House> houseList;
    private static int totalBuild = 0;

    public World(){
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<>();

    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }

    public ArrayList<House> getObjects() {
        return houseList;
    }

    public void addHouse(Point point){
        totalBuild ++;
    }

    public void removeHouse(House house){
        totalBuild --;
    }

    public int getTotalBuild(){
        return totalBuild;
    }


}
