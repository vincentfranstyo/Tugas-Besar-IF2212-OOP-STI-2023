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
        System.out.println("blablabla");
    }

    public void exit() {
        this.gameStarted = false;
    }

    public void viewSimInfo(Sim sim) {
        System.out.println("Info Sim:");
        System.out.println("Nama: " + sim.getName());
        System.out.println("Job: " + sim.getJob());
        System.out.println("Job Duration: " + sim.getCurrentJobDuration());
        System.out.println("Money: " + sim.getMoney());
        System.out.println("Fullness: " + sim.getFullness());
        System.out.println("Mood: " + sim.getMood());
        System.out.println("Health: " + sim.getHealth());
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
