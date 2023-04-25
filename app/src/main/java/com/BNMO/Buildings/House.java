package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Object.Object;
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
        Thread t = new Thread(new Runnable() {
           public void run(){
            try {
                // Pilih ruangan yang ingin di bangun (Above, Right, Below, atau Left) dari currentRoom lalu instansiasi sebuah room baru dengan posisi yang dipilih
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
