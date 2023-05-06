package com.BNMO;

import com.BNMO.Buildings.*;
import com.BNMO.Utilities.*;
import com.BNMO.SIMS.Sim;

import java.util.*;

public class World {
    private final int length;
    private final int width;
    private final ArrayList<House> houseList;
    private static int totalBuild = 0;
    private static World instance;

    private World(Sim initSim) {
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<House>();
    }

    public static World getInstance() {
        Menu menu = Menu.getInstance();
        if (instance == null) {
            instance = new World(menu.getCurrentSim());
        }
        return instance;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public void addHouse(House rumah) {
        for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).getLocation().getX() == rumah.getLocation().getX()
                    && houseList.get(i).getLocation().getY() == rumah.getLocation().getY()) {
                System.out.println("Sudah ada rumah di lokasi tersebut, silakan pilih lokasi lain");
                return;
            } else if (rumah.getLocation().getX() % 3 == 0) {
                System.out.println("Rumah berada di jalan raya, silakan pilih lokasi lain");
                return;
            } else if (rumah.getLocation().getY() % 5 == 0) {
                System.out.println("Rumah berada di jalan raya, silakan pilih lokasi lain");
                return;
            }
        }
        houseList.add(rumah);
        totalBuild++;
    }

    public void removeHouse(House house) {
        for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).getOwner() == house.getOwner()
                    && houseList.get(i).getLocation() == house.getLocation()) {
                houseList.remove(i);
            }
        }
        totalBuild--;
    }

    public int getTotalBuild() {
        return totalBuild;
    }

    public void printMap() {
        String[][] map = new String[129][129];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i % 10 == 0 && j % 6 == 0) {
                    map[i][j] = "_/";
                } else if (i % 10 == 0 && j % 6 == 1) {
                    map[i][j] = "\\_";
                } else if (i % 10 == 1 && j % 6 == 0) {
                    map[i][j] = "_\\";
                } else if (i % 10 == 1 && j % 6 == 1) {
                    map[i][j] = "/_";
                } else if (j % 6 == 0) {
                    map[i][j] = " |";
                } else if (j % 6 == 1) {
                    map[i][j] = "| ";
                } else if (i % 10 == 0) {
                    map[i][j] = "_";
                } else if (i % 10 == 1) {
                    map[i][j] = "_";
                } else if (j % 2 == 0 && i % 2 == 0) {
                    map[i][j] = "+";
                }
                else {
                    map[i][j] = " ";
                }
            }
        }

        for (int i = 0; i < houseList.size(); i++) {
            int xValue = houseList.get(i).getLocation().getX() * 2;
            int yValue = houseList.get(i).getLocation().getY() * 2;
            map[yValue][xValue] = "#";
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("LEGENDA PETA SIM-PLICITY");
        System.out.println("__ || : Jalan Raya");
        System.out.println("/\\\\/ : Perempatan");
        System.out.println("+ : Lahan Kosong");
        System.out.println("# : Rumah SIM");
        System.out.println("* (0,0) -> Kiri Atas ");
        System.out.println("* (64,64) -> Kanan Bawah");
        System.out.println();
        System.out.println("DAFTAR RUMAH:");
        for (int i = 0; i < houseList.size(); i++) {
            int xValue = houseList.get(i).getLocation().getX();
            int yValue = houseList.get(i).getLocation().getY();
            System.out.println("(" + xValue + "," + yValue + ")");
        }
        System.out.println();
    }

    public void printHouseLayout(House house, Room room) {
        ArrayList<ArrayList<String>> houseLayout = new ArrayList<>();
        int yCount = houseLayout.size();
        for (int i = 0; i < 15; i++) {
            houseLayout.add(new ArrayList<>());
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                houseLayout.get(i).add("  ");
            }
        }

        for (int i = 5; i < 10; i++) {
            for (int j = 5; j < 10; j++) {
                if (i % 5 == 0) {
                    houseLayout.get(i).set(j, "--");
                } else if (i % 5 == 2) {
                    houseLayout.get(i).set(j, "   ");
                } else if (i % 5 == 4) {
                    houseLayout.get(i).set(j, "--");
                } else if (j % 5 == 0) {
                    houseLayout.get(i).set(j, "|");
                } else if (j % 5 == 4) {
                    houseLayout.get(i).set(j, "|");
                } else {
                    houseLayout.get(i).set(j, "   ");
                }
            }
        }
        houseLayout.get(7).set(7, room.getNameRoom());
        houseLayout.get(7).set(6, " ");

        if (room.getFront() != null) {
            for (int i = 0; i < 5; i++) {
                for (int j = 5; j < 10; j++) {
                    if (i % 5 == 0) {
                        houseLayout.get(i).set(j, "--");
                    } else if (i % 5 == 2) {
                        houseLayout.get(i).set(j, "   ");
                    } else if (i % 5 == 4) {
                        houseLayout.get(i).set(j, "--");
                    } else if (j % 5 == 0) {
                        houseLayout.get(i).set(j, "|");
                    } else if (j % 5 == 4) {
                        houseLayout.get(i).set(j, "|");
                    } else {
                        houseLayout.get(i).set(j, "   ");
                    }
                }
            }
            houseLayout.get(2).set(7, "UC");
        }

        if (room.getBehind() != null) {
            for (int i = 10; i < 15; i++) {
                for (int j = 5; j < 10; j++) {
                    if (i % 5 == 0) {
                        houseLayout.get(i).set(j, "--");
                    } else if (i % 5 == 2) {
                        houseLayout.get(i).set(j, "   ");
                    } else if (i % 5 == 4) {
                        houseLayout.get(i).set(j, "--");
                    } else if (j % 5 == 0) {
                        houseLayout.get(i).set(j, "|");
                    } else if (j % 5 == 4) {
                        houseLayout.get(i).set(j, "|");
                    } else {
                        houseLayout.get(i).set(j, "   ");
                    }
                }
            }
            houseLayout.get(12).set(7, "UC");
        }

        if (room.getLeft() != null) {
            for (int i = 5; i < 10; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i % 5 == 0) {
                        houseLayout.get(i).set(j, "--");
                    } else if (i % 5 == 2) {
                        houseLayout.get(i).set(j, "   ");
                    } else if (i % 5 == 4) {
                        houseLayout.get(i).set(j, "--");
                    } else if (j % 5 == 0) {
                        houseLayout.get(i).set(j, "|");
                    } else if (j % 5 == 4) {
                        houseLayout.get(i).set(j, "|");
                    } else {
                        houseLayout.get(i).set(j, "   ");
                    }
                }
            }
            houseLayout.get(7).set(2, "UC");
            houseLayout.get(7).set(1, " ");
        }

        if (room.getRight() != null) {
            for (int i = 5; i < 10; i++) {
                for (int j = 10; j < 15; j++) {
                    if (i % 5 == 0) {
                        houseLayout.get(i).set(j, "--");
                    } else if (i % 5 == 2) {
                        houseLayout.get(i).set(j, "   ");
                    } else if (i % 5 == 4) {
                        houseLayout.get(i).set(j, "--");
                    } else if (j % 5 == 0) {
                        houseLayout.get(i).set(j, "|");
                    } else if (j % 5 == 4) {
                        houseLayout.get(i).set(j, "|");
                    } else {
                        houseLayout.get(i).set(j, "   ");
                    }
                }
            }
            houseLayout.get(7).set(12, "UC");
            houseLayout.get(7).set(11, " ");
        }

        Iterator<Room> rooms = house.getRooms();
        int counter = 0;
        while (rooms.hasNext()) {
            counter++;
            Room roomCur = rooms.next();
            yCount = houseLayout.size();
            for (int i = 0; i < yCount; i++) {
                int xCount = houseLayout.get(i).size();
                for (int j = 0; j < xCount; j++) {
                    if (houseLayout.get(i).get(j).equals(roomCur.getNameRoom())) {
                        houseLayout.get(i).set(j, Integer.toString(counter));
                    }
                }
            }
        }

        yCount = houseLayout.size();
        for (int i = 0; i < yCount; i++) {
            int xCount = houseLayout.get(i).size();
            for (int j = 0; j < xCount; j++) {
                System.out.print(houseLayout.get(i).get(j));
            }
            System.out.println();
        }

        houseLayout.clear();

    }

}