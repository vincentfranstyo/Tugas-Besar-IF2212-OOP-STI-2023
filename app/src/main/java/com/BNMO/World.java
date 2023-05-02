package com.BNMO;

import com.BNMO.Buildings.*;
import com.BNMO.Utilities.*;
import com.BNMO.SIMS.Sim;

import java.util.*;

public class World {
    private int length;
    private int width;
    private ArrayList<House> houseList;
    private static int totalBuild = 0;

    public World(Sim initSim) {
        this.length = 64;
        this.width = 64;
        this.houseList = new ArrayList<House>();

        Random rand = new Random();

        int xRandom = rand.nextInt(65);
        if (xRandom == 0) {
            xRandom++;
        } else if (xRandom % 3 == 0) {
            xRandom--;
        }

        Random rand2 = new Random();

        int yRandom = rand2.nextInt(65);
        if (yRandom == 0) {
            yRandom++;
        }  
        if (yRandom % 5 == 0) {
            yRandom--;
        }

        Point initPoint = new Point(xRandom, yRandom);
        House initHouse = new House(initPoint, initSim);
        addHouse(initHouse);
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
                // if(i % 5 == 0 && j % 3 == 0){
                // map[i][j] = " o";
                // }
                // else if(j % 3 == 0){
                // map[i][j] = "||";
                // }
                // else if(i % 5 == 0){
                // map[i][j] = "===";
                // }
                // else {
                // map[i][j] = " + ";
                // }
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
                // else if(j % 2 == 1){
                // map[i][j] = " ";
                // }
                // else if(i % 2 == 0){
                // map[i][j] = "+";
                // }
                // else if(i % 2 == 1){
                // map[i][j] = " ";
                // }
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

    public void printHouseLayout(House house) {
        ArrayList<ArrayList<String>> houseLayout = new ArrayList<>();
        int yCount = houseLayout.size();
        for (int i = 0; i < 5; i++) {
            houseLayout.add(new ArrayList<>());
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0) {
                    houseLayout.get(i).add("--");
                } else if (i == 2) {
                    houseLayout.get(i).add("  ");
                } else if (i == 4) {
                    houseLayout.get(i).add("--");
                } else if (j == 0) {
                    houseLayout.get(i).add("|");
                } else if (j == 4) {
                    houseLayout.get(i).add("|");
                } else {
                    houseLayout.get(i).add("   ");
                }
            }
        }
        Iterator<Room> rooms = house.getRooms();
        Room curRoom = rooms.next();
        houseLayout.get(2).set(2, curRoom.getNameRoom());

        Iterator<Room> kamar = house.getRooms();
        while (kamar.hasNext()) {
            Room currentRoom = kamar.next();
            if (currentRoom.getFront() != null) {

                boolean checker = false;
                for (int i = 0; i < houseLayout.size(); i++) {
                    if (houseLayout.get(i).contains(currentRoom.getNameRoom())) {
                        checker = true;
                    }
                }

                if (!checker) {
                    int startY = 0;
                    int startX = 0;
                    for (int i = 0; i < houseLayout.size(); i++) {
                        if (houseLayout.get(i).contains(currentRoom.getFront().getNameRoom())) {
                            for (int j = 0; j < houseLayout.get(i).size(); j++) {
                                if (houseLayout.get(i).get(j).equals(currentRoom.getFront().getNameRoom())) {
                                    startY = i;
                                    startX = j;
                                }
                            }
                        }
                    }
                    
                        for (int i = 0; i < 5; i++) {
                            houseLayout.add(new ArrayList<>());
                        }
                    

                    yCount = houseLayout.size();
                    for (int i = startY + 3; i < startY + 8; i++) {
                        if (startX != 0) {
                            for (int j = 0; j < startX - 2; j++) {
                                houseLayout.get(i).add("  ");
                            }
                        }
                        for (int j = startX - 2; j < startX + 3; j++) {
                            if (i % 5 == 0) {
                                houseLayout.get(i).add("--");
                            } else if (i % 5 == 2) {
                                houseLayout.get(i).add("  ");
                            } else if (i % 5 == 4) {
                                houseLayout.get(i).add("--");
                            } else if (j % 5 == 0) {
                                houseLayout.get(i).add("|");
                            } else if (j % 5 == 4) {
                                houseLayout.get(i).add("|");
                            } else {
                                houseLayout.get(i).add("   ");
                            }
                        }
                    }

                    houseLayout.get(startY + 5).set(startX, currentRoom.getNameRoom());
                }
            }

            if (currentRoom.getBehind() != null) {
                boolean checker = false;
                for (int i = 0; i < houseLayout.size(); i++) {
                    if (houseLayout.get(i).contains(currentRoom.getNameRoom())) {
                        checker = true;
                    }
                }

                if (!checker) {
                    int startY = 0;
                    int startX = 0;
                    for (int i = 0; i < houseLayout.size(); i++) {
                        if (houseLayout.get(i).contains(currentRoom.getBehind().getNameRoom())) {
                            for (int j = 0; j < houseLayout.get(i).size(); j++) {
                                if (houseLayout.get(i).get(j).equals(currentRoom.getBehind().getNameRoom())) {
                                    startY = i;
                                    startX = j;
                                }
                            }
                        }
                    }
                    System.out.println(houseLayout.get(startY).get(startX));
                    System.out.println(currentRoom.getBehind().getNameRoom());
                    System.out.println(startX);
                    System.out.println(startY);
                    
                    for (int i = 0; i < 5; i++) {
                        if (startY == 2) {
                            houseLayout.add(0, new ArrayList<>());
                        } else {
                            houseLayout.add(startY - 7, new ArrayList<>());
                        }
                    }
                    


                    if (startY == 2) {
                        for (int i = 0; i < 5; i++) {
                            if (startX != 0) {
                                for (int j = 0; j < startX - 2; j++) {
                                    houseLayout.get(i).add("  ");
                                }
                            }
                            for (int j = startX - 2; j < startX + 3; j++) {
                                if (i % 5 == 0) {
                                    houseLayout.get(i).add("--");
                                } else if (i % 5 == 2) {
                                    houseLayout.get(i).add("  ");
                                } else if (i % 5 == 4) {
                                    houseLayout.get(i).add("--");
                                } else if (j % 5 == 0) {
                                    houseLayout.get(i).add("|");
                                } else if (j % 5 == 4) {
                                    houseLayout.get(i).add("|");
                                } else {
                                    houseLayout.get(i).add("   ");
                                }
                            }
                        }

                        houseLayout.get(2).set(startX, currentRoom.getNameRoom());
                    } else {
                        for (int i = startY - 7; i < startY - 2; i++) {
                            for (int j = startX - 2; j < startX + 3; j++) {
                                if (i % 5 == 0) {
                                    houseLayout.get(i).add("--");
                                } else if (i % 5 == 2) {
                                    houseLayout.get(i).add("  ");
                                } else if (i % 5 == 4) {
                                    houseLayout.get(i).add("--");
                                } else if (j % 5 == 0) {
                                    houseLayout.get(i).add("|");
                                } else if (j % 5 == 4) {
                                    houseLayout.get(i).add("|");
                                } else {
                                    houseLayout.get(i).add("   ");
                                }
                            }
                        }
                        ;
                        houseLayout.get(startY - 5).set(startX, currentRoom.getNameRoom());
                    }
                }

            }

            if (currentRoom.getLeft() != null) {
                boolean checker = false;
                for (int i = 0; i < houseLayout.size(); i++) {
                    if (houseLayout.get(i).contains(currentRoom.getNameRoom())) {
                        checker = true;
                    }
                }

                if (!checker) {
                    int startY = 0;
                    int startX = 0;
                    for (int i = 0; i < houseLayout.size(); i++) {
                        if (houseLayout.get(i).contains(currentRoom.getLeft().getNameRoom())) {
                            for (int j = 0; j < houseLayout.get(i).size(); j++) {
                                if (houseLayout.get(i).get(j).equals(currentRoom.getLeft().getNameRoom())) {
                                    startY = i;
                                    startX = j;
                                }
                            }
                        }
                    }

                    int tempYlower = startY - 2;
                    int tempYupper = startY + 3;

                    for (int i = 0; i < houseLayout.size(); i++) {
                        if ((i >= (tempYlower)) && (i < tempYupper)) {
                            for (int j = startX + 3; j < startX + 8; j++) {
                                if (i % 5 == 0) {
                                    houseLayout.get(i).add("--");
                                } else if (i % 5 == 2) {
                                    houseLayout.get(i).add("  ");
                                } else if (i % 5 == 4) {
                                    houseLayout.get(i).add("--");
                                } else if (j % 5 == 0) {
                                    houseLayout.get(i).add("|");
                                } else if (j % 5 == 4) {
                                    houseLayout.get(i).add("|");
                                } else {
                                    houseLayout.get(i).add("   ");
                                }
                            }
                        } else {
                            try {
                                String temp = houseLayout.get(i).get(startX + 5);
                            } catch (IndexOutOfBoundsException e) {
                                for (int j = startX + 3; j < startX + 8; j++) {
                                    houseLayout.get(i).add("  ");
                                }
                            }

                        }
                    }
                    houseLayout.get(startY).set(startX + 5, currentRoom.getNameRoom());
                }
            }

            if (currentRoom.getRight() != null) {
                boolean checker = false;
                for (int i = 0; i < houseLayout.size(); i++) {
                    if (houseLayout.get(i).contains(currentRoom.getNameRoom())) {
                        checker = true;
                    }
                }

                if (!checker) {
                    int startY = 0;
                    int startX = 0;
                    for (int i = 0; i < houseLayout.size(); i++) {
                        if (houseLayout.get(i).contains(currentRoom.getRight().getNameRoom())) {
                            for (int j = 0; j < houseLayout.get(i).size(); j++) {
                                if (houseLayout.get(i).get(j).equals(currentRoom.getRight().getNameRoom())) {
                                    startY = i;
                                    startX = j;
                                }
                            }
                        }
                    }
                    if (startX == 2) {
                        int tempYlower = startY - 2;
                        int tempYupper = startY + 3;

                        for (int i = 0; i < houseLayout.size(); i++) {
                            if ((i >= (tempYlower)) && (i < tempYupper)) {
                                for (int j = 0; j < 5; j++) {
                                    if (i % 5 == 0) {
                                        houseLayout.get(i).add(j, "--");
                                    } else if (i % 5 == 2) {
                                        houseLayout.get(i).add(j, "  ");
                                    } else if (i % 5 == 4) {
                                        houseLayout.get(i).add(j, "--");
                                    } else if (j % 5 == 0) {
                                        houseLayout.get(i).add(j, "|");
                                    } else if (j % 5 == 4) {
                                        houseLayout.get(i).add(j, "|");
                                    } else {
                                        houseLayout.get(i).add(j, "   ");
                                    }
                                }
                            } else {
                                try {
                                    String temp = houseLayout.get(i).get(startX - 5);
                                } catch (IndexOutOfBoundsException e) {
                                    for (int j = 0; j < 5; j++) {
                                        houseLayout.get(i).add(j, "  ");
                                    }
                                }

                            }
                        }

                        houseLayout.get(startY).set(2, currentRoom.getNameRoom());
                    } else {
                        int tempYlower = startY - 2;
                        int tempYupper = startY + 3;

                        for (int i = 0; i < houseLayout.size(); i++) {
                            if ((i >= (tempYlower)) && (i < tempYupper)) {
                                for (int j = startX - 2; j < startX + 3; j++) {
                                    if (i % 5 == 0) {
                                        houseLayout.get(i).add(j, "--");
                                    } else if (i % 5 == 2) {
                                        houseLayout.get(i).add(j, "  ");
                                    } else if (i % 5 == 4) {
                                        houseLayout.get(i).add(j, "--");
                                    } else if (j % 5 == 0) {
                                        houseLayout.get(i).add(j, "|");
                                    } else if (j % 5 == 4) {
                                        houseLayout.get(i).add(j, "|");
                                    } else {
                                        houseLayout.get(i).add(j, "   ");
                                    }
                                }
                            } else {
                                try {
                                    String temp = houseLayout.get(i).get(startX - 5);
                                } catch (IndexOutOfBoundsException e) {
                                    for (int j = 0; j < 5; j++) {
                                        houseLayout.get(i).add(j, "  ");
                                    }
                                }

                            }
                        }

                        houseLayout.get(startY).set(startX - 5, currentRoom.getNameRoom());
                    }

                }
            }

        }
        rooms = house.getRooms();
        int counter = 0;
        while (rooms.hasNext()){
            counter++;
            Room roomCur = rooms.next();
            yCount = houseLayout.size();
            for (int i = 0; i < yCount; i++) {
                int xCount = houseLayout.get(i).size();
                for (int j = 0; j < xCount; j++) {
                    if(houseLayout.get(i).get(j).equals(roomCur.getNameRoom())){
                        houseLayout.get(i).set(j,Integer.toString(counter));
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

    }

}
