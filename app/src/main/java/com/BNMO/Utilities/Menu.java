package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;

import java.util.*;

public class Menu {
    private boolean gameStarted = false;
    private Sim currentSim;
    private ArrayList<Sim> simList;

    public void start() {
        this.gameStarted = true;
        this.simList = new ArrayList<Sim>();
        System.out.println("Permainan Akan Segera Dimulai...");
        System.out.println("Selamat Bermain !!!");
    }

    public void help() {
        System.out.println("Panduan Game Sim-Plicity");

    }

    public void exit() {
        this.gameStarted = false;
    }

    public void viewSimInfo(Sim currentSim) {
        System.out.println("Info Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Job: " + currentSim.getJob());
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
}
