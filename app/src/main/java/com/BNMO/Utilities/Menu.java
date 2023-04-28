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
        System.out.println("1. Ketika memulai permainan sebuah World Sim-Plicity akan dibuat dengan ukuran 64x64");
        System.out.println("2. Pemain akan diminta untuk membuat sebuah Sim terlebih dahulu");
        System.out.println("3. Sebuah rumah dengan 1 ruangan berukuran 6x6  juga akan digenerate");
        System.out.println("4. Sim akan diletakkan di rumah tersebut dan bebas melakukan aksi apapun");
        System.out.println("5. Setiap Sim dapat melakukan aksi berupa aktif, upgrade, non-aktif, dan menambah SIM");
        System.out.println();

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
            if (simList.get(i).getName() == name) {
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
