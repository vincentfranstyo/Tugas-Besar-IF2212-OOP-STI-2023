package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Object.Object;
import com.BNMO.Object.NonFoodObjects.Bed.Bed;
import com.BNMO.Object.NonFoodObjects.Bed.SingleBed;
import com.BNMO.Object.NonFoodObjects.Clock.Clock;
import com.BNMO.Object.NonFoodObjects.Stove.GasStove;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;
import com.BNMO.World;
import com.BNMO.Object.*;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class House {
    private ArrayList<Room> rooms;
    private int totalRoom;
    private Sim owner;
    private Point location;
    public House(Point loc, Sim owner){
        this.rooms = new ArrayList<Room>();
        this.totalRoom = 0;
        this.owner = owner;
        this.location = loc;
    }
    public void addRoom(){
        if(totalRoom != 0){
            Thread t = new Thread(new Runnable() {
            public void run(){
                try {
                    Thread.sleep(1080000); // 18 menit
                    // Pilih ruangan yang ingin di bangun (Above, Right, Below, atau Left) dari currentRoom lalu instansiasi sebuah room baru dengan posisi yang dipilih
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Choose");
                    sc.close();
                    totalRoom++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } 
            });
            t.start();
        }
        else{
            // Create Room pertama kali tidak akan menunggu waktu
            Room newRoom = new Room("Main Room", null, null, null, null);
            Object bed = new SingleBed("Single Bed",null);
            Object toilet = new Toilet("Toilet", null);
            Object stove = new GasStove("Gas Stove", null);
            Object clock = new Clock("Jam", null, null);
            Object tablenchair = new TableAndChair("Table and Chair", null);
            newRoom.addObject(clock, location);
            rooms.add(totalRoom, newRoom);
        }
    }
    public void deleteRoom(Room delRoom){
        if(rooms.contains(delRoom)){
            rooms.remove(delRoom);
            totalRoom--;
            Iterator<Object> objectsRoom = delRoom.getObjects();
            // Memindahkan semua object yang ada di dalam room ke dalam inventory
            while(objectsRoom.hasNext()){
                owner.getInventory().addObject(objectsRoom.next());
            }
            System.out.println("Room "+ delRoom.getNameRoom() +" Berhasil di Delete dari Rumah " + owner.getName());
        }
        else{
            System.out.println("Tidak Ada Room " + delRoom.getNameRoom() +" Pada Rumah " + owner.getName());
        }
    }
    public int getTotalRoom(){
        return totalRoom;
    }
    public Sim getOwner(){
        return owner;
    }
    public void setOwner(Sim newOwner){
        this.owner = newOwner;
    }
    public Iterator<Room> getRooms(){
        return rooms.iterator();
    }
    public Point getLocation(){
        return location;
    }
    public void setLocation(Point newLoc){
        this.location = newLoc;
    }
}
