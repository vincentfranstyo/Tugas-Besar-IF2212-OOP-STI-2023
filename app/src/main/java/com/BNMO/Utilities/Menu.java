package com.BNMO;

import com.BNMO.SIMS.Sim;

public class Menu {
    private boolean gameStarted = false;

    public void start() {
        this.gameStarted = true;
    }

    public void help() {
        System.out.println("blablabla");
    }

    public void exit() {
        this.gameStarted = false;
    }

    public void viewSimInfo(Sim sim) {
        System.out.println("Info Sim");
    }

    public void addSim() {

    }

    public void changeSim() {

    }
}
