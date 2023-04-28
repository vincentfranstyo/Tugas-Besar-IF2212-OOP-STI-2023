package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;

import java.util.*;

public class Menu {
    private boolean gameStarted = false;
    private Sim currentSim;
    private ArrayList<Sim> simList;

    public Menu(Sim initSim) {
        this.currentSim = initSim;
    }

    public void start() {
        this.gameStarted = true;
        this.simList = new ArrayList<Sim>();
        this.simList.add(currentSim);
        System.out.println("Permainan Akan Segera Dimulai...");
        System.out.println("Selamat Bermain !!!");
    }

    public void help() {
        System.out.println("Panduan Game Sim-Plicity");
        System.out.println("1. Ketika memulai permainan Sim-Plicity sebuah World akan dibuat dengan ukuran 64x64");
        
        String[][] map = new String[64][64];
        for (int i=0; i < map.length; i++){
            for (int j=0; j < map[i].length; j++){
                map[i][j] = " + ";
            }
        }

        for (int i=0; i < map.length; i++){
            for (int j=0; j < map[i].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

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

    public void exit() {
        this.gameStarted = false;
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
    }

    public void addSim(Sim sim) {
        simList.add(sim);
    }

    public void changeSim(String name) {
        for (int i = 0; i < simList.size(); i++) {
            if (simList.get(i).getName().equals(name)) {
                this.currentSim = simList.get(i);
            }
        }
    }

    public void viewSimList() {
        System.out.println("Daftar Sim:");
        for (int i = 0; i < simList.size(); i++) {
            System.out.println(simList.get(i).getName());
        }
    }

    public void viewSimStatus() {
        System.out.println("Status Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Status: " + currentSim.getStatus());
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Sim getCurrentSim() {
        return currentSim;
    }
}
