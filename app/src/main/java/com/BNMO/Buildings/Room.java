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

    public Room(String nRoom, Room above, Room right, Room below, Room left) {
        this.nameRoom = nRoom;
        this.above = above;
        this.right = right;
        this.below = below;
        this.left = left;
        this.objects = new ArrayList<Object>();
        this.totalObject = 0;
    }

    private boolean availableLoc(Point Loc) {
        Iterator<Object> itr = getObjects();
        boolean available = true;
        while (itr.hasNext() && available) {
            available = !Loc.equals(((NonFoodObjects) itr.next()).getPosition());
        }
        return available;
    }

    public void addObject(Object o, Point loc) {
        // Cek location yang diinput
        while ((loc.getX() < 0 && loc.getX() > 64) || (loc.getY() < 0 && loc.getY() > 64) || (!availableLoc(loc))) {
            System.out.print("Location Not Available Please Input Location Object Again: ");
            Scanner sc = new Scanner(System.in);
            try {
                loc.setX(sc.nextInt());
                loc.setY(sc.nextInt());
            } catch (NoSuchElementException e) {
                sc.close();
            }
        }
        if (o instanceof NonFoodObjects)
            ((NonFoodObjects) o).setPosition(loc);
        this.objects.add(totalObject, o);
        this.totalObject++;
    }

    public void removeObject(Object o, Point Loc, Sim owner) {
        if (objects.contains(o)) {
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
}
