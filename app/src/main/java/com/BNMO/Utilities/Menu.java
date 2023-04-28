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
