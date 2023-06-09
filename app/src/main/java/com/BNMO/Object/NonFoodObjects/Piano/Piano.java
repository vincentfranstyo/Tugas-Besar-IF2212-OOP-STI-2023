package com.BNMO.Object.NonFoodObjects.Piano;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Piano extends NonFoodObjects {
    public Piano(String name) {
        super(name, 3, 3, 50);
        this.setType("Piano");
    }

    public void playPiano(Time time, Sim sim) {
        try {
            if (!getIsOccupied()) {
                setIsOccupied(true);
                if (sim.getStatus().equals("Nothing")) {
                    int duration = time.convertToSecond();
                    sim.setStatus("Playing piano");
                    System.out.println(sim.getName() + " is playing piano");
                    Thread.sleep(duration * 1000L);
                    System.out.println(sim.getName() + " is done playing piano");
                    sim.setMood(sim.getMood() + 5 * duration / 20);
                } else {
                    System.out.println("You can't play the piano while you are " + sim.getStatus());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sim.setStatus("Nothing");
            setIsOccupied(false);
        }
    }
}