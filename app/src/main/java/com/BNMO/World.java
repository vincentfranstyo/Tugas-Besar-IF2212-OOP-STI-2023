package com.BNMO;

import com.BNMO.Buildings.House;
import com.BNMO.Utilities.Point;

import java.util.ArrayList;

public class World {
    private int length;
    private int width;
    private ArrayList<House> houseList;
    private int totalBuild;

    public World(){
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<>();
        totalBuild = 0;

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

    }

    public void removeHouse(House house){

    }

    public int getTotalBuild(){
        return totalBuild;
    }


}
