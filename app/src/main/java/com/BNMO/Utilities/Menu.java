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
    }

    public void help() {
        System.out.println("Panduan untuk game Sim");
    }

    public void exit() {
        this.gameStarted = false;
    }

    public void viewSimInfo() {
        System.out.println("Info Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Job: " + currentSim.getJob());
        System.out.println("Money: " + currentSim.getMoney());
        System.out.println("Fullness: " + currentSim.getFullness());
        System.out.println("Mood: " + currentSim.getMood());
        System.out.println("Health: " + currentSim.getHealth());
    }

    public void addSim(String name) {
        Sim sim = new Sim(name);
        simList.add(sim);
    }

    public void changeSim(String name) {
        for (int i = 0; i < simList.size(); i++) {
            if(simList.get(i).getName() == name){
                this.currentSim = simList.get(i);
            }
        }
    }
}
