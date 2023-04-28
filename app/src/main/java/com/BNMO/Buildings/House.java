package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Object.Object;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;
import com.BNMO.Object.NonFoodObjects.Clock.Clock;
import com.BNMO.Object.NonFoodObjects.Stove.*;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Bed.*;

public class House {
    private ArrayList<Room> rooms;
    private int totalRoom = 0;
    private Sim owner;
    private Point location;
    private Room initRoom;

    public House(Point loc, Sim owner) {
        this.rooms = new ArrayList<Room>();
        this.totalRoom = 0;
        this.owner = owner;
        this.location = loc;
        this.initRoom = new Room("Ruang 1", null, null, null, null);
        this.rooms.add(initRoom);
        this.totalRoom++;
        this.initRoom.addObject(new Toilet("Toilet 1"), new Point(6, 1));
        this.initRoom.addObject(new GasStove("Kompor Gas 1"), new Point(1, 6));
        this.initRoom.addObject(new TableAndChair("Meja Makan 1"), new Point(1, 5));
        this.initRoom.addObject(new Clock("Jam 1", null), new Point(5, 1));
        this.initRoom.addObject(new SingleBed("Kasur 1"), new Point(6, 6));
    }

    public void addRoom() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    // Pilih ruangan yang ingin di bangun (Above, Right, Below, atau Left) dari
                    // currentRoom lalu instansiasi sebuah room baru dengan posisi yang dipilih
                    // Scanner sc = new Scanner(System.in);
                    // System.out.println("");
                    // sc.close();
                    Thread.sleep(9000);
                    totalRoom++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void deleteRoom(Room delRoom) {
        if (rooms.contains(delRoom)) {
            rooms.remove(delRoom);
            totalRoom--;
            Iterator<Object> objectsRoom = delRoom.getObjects();
            // Memindahkan semua object yang ada di dalam room ke dalam inventory
            while (objectsRoom.hasNext()) {
                owner.getInventory().addObject(objectsRoom.next());
            }
            System.out.println("Room " + delRoom.getNameRoom() + " Berhasil di Delete dari Rumah " + owner.getName());
        } else {
            System.out.println("Tidak Ada Room " + delRoom.getNameRoom() + " Pada Rumah " + owner.getName());
        }
    }

    public int getTotalRoom() {
        return totalRoom;
    }

    public Sim getOwner() {
        return owner;
    }

    public void setOwner(Sim newOwner) {
        this.owner = newOwner;
    }

    public Iterator<Room> getRooms() {
        return rooms.iterator();
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point newLoc) {
        this.location = newLoc;
    }
}
