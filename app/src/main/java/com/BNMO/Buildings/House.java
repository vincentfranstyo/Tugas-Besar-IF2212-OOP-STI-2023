package com.BNMO.Buildings;

import java.util.*;

import com.BNMO.Object.NonFoodObjects.GameStation.GameStation;
import com.BNMO.Object.Object;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.Bed.*;
import com.BNMO.Object.NonFoodObjects.Clock.Clock;
import com.BNMO.Object.NonFoodObjects.Stove.GasStove;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;
import com.BNMO.Object.NonFoodObjects.TV.TV;

public class House {
    private final ArrayList<Room> rooms;
    private int totalRoom = 0;
    private Sim owner;
    private Point location;
    private final Room initRoom;

    public House(Point loc, Sim owner) {
        this.rooms = new ArrayList<Room>();
        this.totalRoom = 0;
        this.owner = owner;
        this.location = loc;
        this.initRoom = new Room("Main room", null, null, null, null);
        this.rooms.add(initRoom);
        this.totalRoom++;
        this.initRoom.addObject(new Toilet("Toilet 1"), new Point(6, 1), "horizontal");
        this.initRoom.addObject(new GasStove("Kompor Gas 1"), new Point(1, 6), "horizontal");
        this.initRoom.addObject(new TableAndChair("Meja Makan 1"), new Point(1, 3), "horizontal");
        this.initRoom.addObject(new Clock("Jam 1"), new Point(5, 1), "horizontal");
        this.initRoom.addObject(new SingleBed("Kasur 1"), new Point(6, 3), "vertikal");
//        this.initRoom.addObject(new TV("TV 1"), new Point(4, 2), "vertikal");
    }

    private void fixDirectRoom() {
        for (Room r : rooms) {
            if (r.getFront() != null) {
                if (r.getFront().getRight() != null) {
                    if (r.getRight() != null) {
                        r.getRight().setFront(r.getFront().getRight());
                    }
                }
                if (r.getFront().getLeft() != null) {
                    if (r.getLeft() != null) {
                        r.getLeft().setFront(r.getFront().getLeft());
                    }
                }
            }
            if (r.getBehind() != null) {
                if (r.getBehind().getRight() != null) {
                    if (r.getRight() != null) {
                        r.getRight().setBehind(r.getBehind().getRight());
                    }
                }
                if (r.getBehind().getLeft() != null) {
                    if (r.getLeft() != null) {
                        r.getLeft().setBehind(r.getBehind().getLeft());
                    }
                }
            }
            if (r.getRight() != null) {
                if (r.getRight().getFront() != null) {
                    if (r.getFront() != null) {
                        r.getFront().setRight(r.getRight().getFront());
                    }
                }
                if (r.getRight().getBehind() != null) {
                    if (r.getBehind() != null) {
                        r.getBehind().setRight(r.getRight().getBehind());
                    }
                }
            }
            if (r.getLeft() != null) {
                if (r.getLeft().getFront() != null) {
                    if (r.getFront() != null) {
                        r.getFront().setLeft(r.getLeft().getFront());
                    }
                }
                if (r.getLeft().getBehind() != null) {
                    if (r.getBehind() != null) {
                        r.getBehind().setLeft(r.getLeft().getBehind());
                    }
                }
            }
        }
    }

    public synchronized void addRoom(Sim sim, Room curRoom, String rName, String direction) {
        if (sim.getName().equals(owner.getName())) {
            if (sim.getMoney() >= Room.getPrice()) {
                Room newRoom = new Room(rName, null, null, null, null);
                boolean valid = true;
                if (direction.equalsIgnoreCase("front")) {
                    newRoom.setBehind(curRoom);
                    curRoom.setFront(newRoom);
                } else if (direction.equalsIgnoreCase("right")) {
                    newRoom.setLeft(curRoom);
                    curRoom.setRight(newRoom);
                } else if (direction.equalsIgnoreCase("behind")) {
                    newRoom.setFront(curRoom);
                    curRoom.setBehind(newRoom);
                } else if (direction.equalsIgnoreCase("left")) {
                    newRoom.setRight(curRoom);
                    curRoom.setLeft(newRoom);
                } else {
                    valid = false;
                }
                if (valid) {
                    sim.setMoney(sim.getMoney() - Room.getPrice());
                    DayThread dayThread = DayThread.getInstance();
                    Menu menu = Menu.getInstance();
                    int currentSec = dayThread.getDaySec();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (true) {
                                    int newCurrSec = dayThread.getDaySec();
                                    menu.getCurrentSim().setBuildingCountTime(18 * 60 - (newCurrSec - currentSec));
                                    if (newCurrSec - currentSec != 0) {
                                        if (newCurrSec - currentSec - 1 == 1080) {
                                            System.out.println(
                                                    "Ruangan " + newRoom.getNameRoom() + " Telah Berhasil Dibangun!");
                                            break;
                                        } else if ((newCurrSec - currentSec) % 60 - 1 == 0) {
                                            if (!dayThread.getPaused()) {
                                                System.out.println("Waktu Pembangunan Ruangan " + newRoom.getNameRoom()
                                                        + " Tersisa " + (18 - ((newCurrSec - currentSec) / 60))
                                                        + " Menit.");
                                            }
                                            Thread.sleep(1500);
                                        }
                                    }
                                }
                                rooms.add(newRoom);
                                synchronized (this) {
                                    totalRoom++;
                                }
                                fixDirectRoom();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                } else {
                    System.out.println("Input Arah Ruangan Salah!");
                    System.out.println();
                }
            } else {
                System.out.println("Tidak Bisa Menambah Ruangan Karena Uang Tidak Cukup!");
                System.out.println();
            }
        } else {
            System.out.println("Tidak Bisa Menambah Ruangan Pada Rumah Orang Lain!");
            System.out.println();
        }
    }

    public void deleteRoom(Sim sim, Room delRoom) {
        if (sim.getName().equals(owner.getName())) {
            if (rooms.contains(delRoom)) {
                rooms.remove(delRoom);
                if (delRoom.getFront() != null)
                    delRoom.getFront().setBehind(null);
                if (delRoom.getBehind() != null)
                    delRoom.getBehind().setFront(null);
                if (delRoom.getLeft() != null)
                    delRoom.getLeft().setRight(null);
                if (delRoom.getRight() != null)
                    delRoom.getRight().setLeft(null);
                totalRoom--;
                Iterator<Object> objectsRoom = delRoom.getObjects();
                // Memindahkan semua object yang ada di dalam room ke dalam inventory
                while (objectsRoom.hasNext()) {
                    owner.getInventory().addObject(objectsRoom.next());
                }
                System.out
                        .println("Room " + delRoom.getNameRoom() + " Berhasil di Delete dari Rumah " + owner.getName());
                System.out
                        .println("Room " + delRoom.getNameRoom() + " Berhasil di Delete dari Rumah " + owner.getName());
                System.out.println();
                fixDirectRoom();
            } else {
                System.out.println("Tidak Ada Room " + delRoom.getNameRoom() + " Pada Rumah " + owner.getName());
                System.out.println();
            }
        } else {
            System.out.println("Tidak Bisa Menghapus Ruangan Pada Rumah Orang Lain!");
            System.out.println();
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

    public void printRooms() {
        Iterator<Room> itrRoom = getRooms();
        int counter = 0;
        while (itrRoom.hasNext()) {
            counter++;
            Room temp = itrRoom.next();
            System.out.println(counter + ". " + temp.getNameRoom());
            temp.printObjRoom();
            System.out.println();
        }
        System.out.println();
    }

    public Room getRoom(String nameRoom) {
        Iterator<Room> itrRoom = getRooms();
        while (itrRoom.hasNext()) {
            Room curRoom = itrRoom.next();
            if (curRoom.getNameRoom().equals(nameRoom)) {
                return curRoom;
            }
        }
        return null;
    }
}
