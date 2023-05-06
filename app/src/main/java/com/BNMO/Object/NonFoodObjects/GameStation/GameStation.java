package com.BNMO.Object.NonFoodObjects.GameStation;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

import java.util.ArrayList;
import java.util.List;

public class GameStation extends NonFoodObjects {
    private static ArrayList<Game> games = null;

    public GameStation(String name) {
        super(name, 1, 1, 120);
        this.setType("GameStation");
    }

    public static ArrayList<Game> getGames() {
        games = new ArrayList<>();
        games.add(new Game("PUBG", 1, 20));
        games.add(new Game("Mobile Legend", 2, 10));
        games.add(new Game("Free Fire", 3, 15));
        games.add(new Game("Call of Duty", 4, 25));
        games.add(new Game("Clash of Clans", 5, 5));
        games.add(new Game("Clash Royale", 6, 5));
        games.add(new Game("Among Us", 7, 10));
        games.add(new Game("Minecraft", 8, 20));
        return games;
    }

    public void displayGameList() {
        ArrayList<Game> games = getGames();
        for (Game game : games) {
            System.out.println(game.getName());
        }
    }

    public void playGame(Game game, Sim sim) {
        try {
            setIsOccupied(true);
            sim.setStatus("Playing " + game.getName());
            System.out.println("Playing " + game.getName());
            Thread.sleep(30000);
            System.out.println("Finished playing " + game.getName());
            sim.setMood(sim.getMood() + game.getFunPoint());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sim.setStatus("Nothing");
            setIsOccupied(false);
        }
    }
}