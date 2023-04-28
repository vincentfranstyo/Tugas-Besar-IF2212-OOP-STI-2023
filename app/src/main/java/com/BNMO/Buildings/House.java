package com.BNMO.Buildings;

import java.util.*;
import com.BNMO.Object.Object;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.Bed.*;
import com.BNMO.Object.NonFoodObjects.Clock.Clock;
import com.BNMO.Object.NonFoodObjects.Stove.GasStove;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;

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
        this.initRoom.addObject(new Toilet("Toilet"), new Point(6, 1));
        this.initRoom.addObject(new GasStove("Kompor Gas"), new Point(1, 6));
        this.initRoom.addObject(new TableAndChair("Meja Makan"), new Point(1, 3));
        this.initRoom.addObject(new Clock("Jam", null), new Point(5, 1));
        this.initRoom.addObject(new SingleBed("Kasur"), new Point(6, 5));
    }

    public void addRoom(Room curRoom) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    // Pilih ruangan yang ingin di bangun (Above, Right, Below, atau Left) dari
                    // currentRoom lalu instansiasi sebuah room baru dengan posisi yang dipilih
                    if (curRoom.getFront() != null && curRoom.getRight() != null && curRoom.getBehind() != null
                            && curRoom.getLeft() != null) {
                        System.out.println(
                                "Tidak Dapat Menambah Ruangan Karena Sudah Ada Ruangan Pada Setip Sisi Ruangan Sekerang");
                    } else {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Arah Ruangan:");
                        if (curRoom.getFront() == null)
                            System.out.println("Front");
                        if (curRoom.getRight() == null)
                            System.out.println("Right");
                        if (curRoom.getBehind() == null)
                            System.out.println("Behind");
                        if (curRoom.getLeft() == null)
                            System.out.println("Left");
                        System.out.println("Pilih Arah Ruangan yang Akan Dibangun: ");
                        String pilihan = sc.next();
                        System.out.println("Masukkan Nama Ruangan: ");
                        String rName = sc.next();
                        sc.close();
                        Room newRoom = new Room(rName, null, null, null, null);
                        Thread.sleep(1080000); // 18 menit
                        if (pilihan.toLowerCase().equals("front")) {
                            newRoom.setBehind(curRoom);
                            curRoom.setFront(newRoom);
                        }
                        if (pilihan.toLowerCase().equals("right")) {
                            newRoom.setLeft(curRoom);
                            curRoom.setRight(newRoom);
                        }
                        if (pilihan.toLowerCase().equals("behind")) {
                            newRoom.setFront(curRoom);
                            curRoom.setBehind(newRoom);
                        }
                        if (pilihan.toLowerCase().equals("left")) {
                            newRoom.setRight(curRoom);
                            curRoom.setLeft(newRoom);
                        }
                        rooms.add(newRoom);
                        synchronized (this) {
                            totalRoom++;
                        }
                    }
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
    // public static void main(String[] args) {
    // Sim budi = new Sim("Budi");
    // House budiHouse = new House(new Point(3, 4), budi);
    // budi.move(budiHouse.initRoom);
    // budiHouse.addRoom(budi.getCurrentRoom());
    // }
}
