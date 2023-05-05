package com.BNMO.Object.NonFoodObjects.GameStation;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

import java.util.ArrayList;
import java.util.List;

public class GameStation extends NonFoodObjects {
    private boolean isOn;
    private int battery;
    private static ArrayList<Game> games;

    public GameStation(String name, int length, int width, int price) {
        super(name, 4, 2, 400);
        this.isOn = false;
        this.battery = 100;
        this.games = new ArrayList<>();
        games.add(new Game("PUBG", 1, 20));
        games.add(new Game("Mobile Legend", 2, 10));
        games.add(new Game("Free Fire", 3, 15));
        games.add(new Game("Call of Duty", 4, 25));
        games.add(new Game("Clash of Clans", 5, 5));
        games.add(new Game("Clash Royale", 6, 5));
        games.add(new Game("Among Us", 7, 10));
        games.add(new Game("Minecraft", 8, 20));

        this.setType("GameStation");
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setBattery(int battery) {
        if (battery > 100) {
            this.battery = 100;
        } else {
            this.battery = battery;
        }
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public int getBattery() {
        return battery;
    }

    public void turnOn() {
        if (getBattery() < 0) {
            System.out.println("The battery is dead");
        } else {
            this.isOn = true;
        }
    }

    public void turnOff() {
        this.isOn = false;
    }

    public void charge(Time time) {
        if (getBattery() < 100) {
            setBattery(getBattery() + (time.convertToSecond() / 10));
        } else {
            System.out.println("The battery is full");
        }
    }

    public static List<Game> getGames() {
        return games;
    }

    public void displayGameList() {
        for (Game game : games) {
            System.out.println(game.getName());
        }
    }

    public void playGame(Game game, Sim sim) {
        if (getIsOn()) {
            try {
                setIsOccupied(true);
                sim.setStatus("Playing " + game.getName());
                System.out.println("Playing " + game.getName());
                setBattery(getBattery() - 10);
                Thread.sleep(15000); // Sleep for 15 second
                System.out.println("Finished playing " + game.getName());
                sim.setMood(sim.getMood() + game.getFunPoint());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sim.setStatus("Nothing");
                setIsOccupied(false);
            }
        } else {
            System.out.println("Turn on the GameStation first!");
        }
    }
}