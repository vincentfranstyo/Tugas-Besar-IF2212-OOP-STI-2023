package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Utilities.*;
import com.BNMO.Object.Object;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;

public class Room {
    private ArrayList<Object> objects;
    private String nameRoom;
    private Room above;
    private Room right;
    private Room below;
    private Room left;
    private int length = 6;
    private int width = 6;
    private int totalObject;
    private Object[][] mapObj;
    private static int price = 1500;
    public Room(String nRoom, Room above, Room right, Room below, Room left){
        this.nameRoom = nRoom;
        this.above = above;
        this.right = right;
        this.below = below;
        this.left = left;
        this.objects = new ArrayList<Object>();
        this.mapObj = new Object[6][6];
        this.totalObject = 0;
    }
    private boolean availableLoc(Point Loc, int width, int length){
        if((Loc.getX() < 1 && (Loc.getX()+length > 7)) || (Loc.getY() < 1 && (Loc.getY()+width > 7))){
            return false;
        }
        else{
            boolean available = true;
            for(int i=Loc.getX(); i<length+Loc.getX(); i++){
                for(int j=Loc.getY(); j<width+Loc.getY(); j++){
                    if(mapObj[i-1][j-1] != null){
                        available = false;
                        break;
                    }
                }
            }
            return available;
        }
    }
    public void addObject(Object o, Point loc){
        // Cek location yang diinput, location merupakan titik kiri atas dari object
        if(o instanceof NonFoodObjects){
            while(!availableLoc(loc, ((NonFoodObjects) o).getWidth(), ((NonFoodObjects) o).getLength())){
                System.out.print("Location Not Available, Please Input Location Object Again: ");
                Scanner sc = new Scanner(System.in);
                try {
                    loc.setX(sc.nextInt()); loc.setY(sc.nextInt());
                } catch (NoSuchElementException e){
                    sc.close();
                }
            }
            ((NonFoodObjects) o).setPosition(loc);
            for(int i=loc.getX(); i<loc.getX()+((NonFoodObjects) o).getLength(); i++){
                for(int j=loc.getY(); j<loc.getY()+((NonFoodObjects) o).getWidth(); j++){
                    setMapObj(i-1, j-1, o);
                }
            }
            this.objects.add(totalObject, o);
            this.totalObject++;
        }
    }
    public void removeObject(Object o, Point Loc, Sim owner){
        if(objects.contains(o)){
            ((NonFoodObjects) o).setPosition(null);;
            owner.getInventory().addObject(o);
            objects.remove(o);
            this.totalObject--;
        } else {
            System.out.println("No " + o.getName() + " Object in The " + nameRoom + " Room");
        }
    }

    public Iterator<Object> getObjects() {
        return objects.iterator();
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String newNameRoom) {
        this.nameRoom = newNameRoom;
    }

    public Room getAbove() {
        return above;
    }

    public void setAbove(Room newAbove) {
        this.above = newAbove;
    }

    public Room getRight() {
        return right;
    }

    public void setRight(Room newRight) {
        this.right = newRight;
    }

    public Room getBelow() {
        return below;
    }

    public void setBelow(Room newBelow) {
        this.below = newBelow;
    }

    public Room getLeft() {
        return left;
    }

    public void setLeft(Room newLeft) {
        this.left = newLeft;
    }

    public int getTotalObject() {
        return totalObject;
    }
    public int getPrice(){
        return price;
    }
    public void setMapObj(int x, int y, Object o){
        this.mapObj[x][y] = o;
    }
    public Object getObjFromMap(int x, int y){
        return mapObj[x][y];
    }
    public Object[][] getMapObj(){
        return mapObj;
    }
    // public static void main(String[] args) {
    //     Room A = new Room("Main Room", null, null, null, null);
    //     Object mattress = new SingleBed("Single Bed", null);
    //     Object toilet = new Toilet("WC", null);
    //     Object tablenchair = new TableAndChair("Table and Chair", null);
    //     A.addObject(mattress, new Point(1,1));
    //     A.addObject(toilet, new Point(6,6));
    //     A.addObject(tablenchair, new Point(4,1));
    // }
}
