package com.BNMO.NonFoodObjects;

import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class Piano extends NonFoodObjects {
    private boolean isBeingUsed;

    public Piano(String name, int length, int width, int price) {
        super(name, length, width, price);
        this.isBeingUsed = false;
    }

    public void setStatus(boolean isBeingUsed) {
        this.isBeingUsed = isBeingUsed;
    }

    public boolean getStatus() {
        return isBeingUsed;
    }

    public void playPiano(Time time, Sim sim) {
        if (getStatus() == false) {
            setStatus(true);
            if (sim.getStatus().equals("Nothing")) {
                int duration = time.convertToSecond();
                sim.setStatus("Playing piano");
                sim.setMood(sim.getMood() + 5 * duration / 20);
            } else {
                System.out.println("You can't play the piano while you are " + sim.getStatus());
            }
        }
    }
}