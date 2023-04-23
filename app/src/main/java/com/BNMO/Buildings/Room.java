package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Object;
import com.BNMO.SIMS.Sim;

public class Room {
    private HashMap<Point, Object> objects;
    private String nameRoom;
    private Room above;
    private Room right;
    private Room below;
    private Room left;
    private int totalObject;
    public Room(String nRoom, Room above, Room right, Room below, Room left){
        this.nameRoom = nRoom;
        this.above = above;
        this.right = right;
        this.below = below;
        this.left = left;
        this.objects = new HashMap<Point, Object>();
        this.totalObject = 0;
    }
    public void addObject(Object o, Point loc){
        Scanner sc = new Scanner(System.in);
        while((loc.getX() < 0 && loc.getX() > 64) || (loc.getY() < 0 && loc.getY() > 64) || (objects.containsKey(loc))){
            System.out.print("Location Not Available Please Input Location Object Again: ");
            loc.setX(sc.nextInt()); loc.setY(sc.nextInt());
        }
        this.objects.put(loc, o);
        this.totalObject++;        
        sc.close();
    }
    public void removeObject(Object o, Point Loc, Sim owner){
        if(objects.containsValue(o)){
            owner.getInventory().addObject(o);
            objects.remove(Loc);
            this.totalObject--;
        }
        else{
            System.out.println("No "+o.getName()+" Object in The " +nameRoom+ " Room");
        }
    }
    public Iterator<Object> getObjects(){
        return objects.values().iterator();
    }
    public String getNameRoom(){
        return nameRoom;
    }
    public void setNameRoom(String newNameRoom){
        this.nameRoom = newNameRoom;
    }
    public Room getAbove(){
        return above;
    }
    public void setAbove(Room newAbove){
        this.above = newAbove;
    }
}
