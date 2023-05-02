package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;

import java.util.*;

public class Menu {
    private static boolean gameStarted = false;
    private static Sim currentSim;
    private static ArrayList<Sim> simList = new ArrayList<Sim>();

    public static void start() {
        setGameStarted(true);
        addSim(currentSim);
        System.out.println("Permainan Akan Segera Dimulai...");
        System.out.println("Selamat Bermain !!!");
    }

    public static void help() {
        System.out.println("Panduan Game Sim-Plicity");
        System.out.println(
                "1. Ketika memulai permainan Sim-Plicity sebuah World akan dibuat dengan ukuran 64x64 dengan peta sebagai berikut:");

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

        System.out.println("2. Pemain diminta untuk membuat sebuah Sim terlebih dahulu");
        System.out.println("3. Sebuah rumah dengan 1 ruangan berukuran 6x6 akan digenerate setelah SIM dibuat");
        System.out.println("4. Sim akan diletakkan di rumah tersebut dan bebas melakukan aksi apapun");
        System.out.println("5. Setiap Sim dapat melakukan aksi berupa aktif, non-aktif, upgrade, dan menambah SIM");
        System.out.println("6. Untuk melihat informasi Sim ..... ");
        System.out.println();
        System.out.println("List Aksi Aktif:");
        System.out.println();
        System.out.println("List Aksi Non-Aktif:");
        System.out.println();
        System.out.println("List Aksi Upgrade:");
        System.out.println();
        System.out.println("List Aksi Tambah SIM:");
    }

    public static void exit() {
        setGameStarted(false);
        System.out.println("Terima kasih telah bermain Sim-Plicity!");
    }

    public void viewSimInfo() {
        System.out.println("Info Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Job: " + currentSim.getJob().getName());
        System.out.println("Money: " + currentSim.getMoney());
        System.out.println("Fullness: " + currentSim.getFullness());
        System.out.println("Mood: " + currentSim.getMood());
        System.out.println("Health: " + currentSim.getHealth());
        System.out.println("Current Location: " + currentSim.getCurrentRoom().getNameRoom());
    }

    public static void addSim(Sim sim) {
        simList.add(sim);
    }

    public static void changeSim(String name) {
        for (int i = 0; i < simList.size(); i++) {
            if (simList.get(i).getName().equals(name)) {
                currentSim = simList.get(i);
            }
        }
    }

    public static void viewSimList() {
        System.out.println("Berikut adalah daftar SIMS yang kamu miliki:");
        for (int i = 0; i < simList.size(); i++) {
            System.out.println(simList.get(i).getName());
        }
    }

    public static void viewSimStatus() {
        System.out.println("Status Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Status: " + currentSim.getStatus());
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(Boolean start){
        gameStarted = start;
    }

    public static Sim getCurrentSim() {
        return currentSim;
    }

    public static void setCurrentSim(Sim sim){
        currentSim = sim;
    }

    public static ArrayList getSimList(){
        return simList;
    }
    
}
