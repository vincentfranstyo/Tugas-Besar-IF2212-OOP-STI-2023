package com.BNMO.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DayThread implements Runnable {
    private AtomicInteger dailyWorkDuration = new AtomicInteger(0);
    private AtomicBoolean slept = new AtomicBoolean(false);
    private AtomicBoolean notEnoughSleep = new AtomicBoolean(false);
    private AtomicBoolean eaten = new AtomicBoolean(false);
    private AtomicBoolean poopedAfterAte = new AtomicBoolean(false);
    private AtomicBoolean notPoopedYet = new AtomicBoolean(false);
    private boolean paused = false;
    private final Object lock = new Object();
    int i = 720;
    int day;

    public void pauseThread() {
        paused = true;
        System.out.println("Hari ke-" + day + " telah dijeda!");
        System.out.println("Silahkan melakukan aksi aktif untuk melanjutkan hari!");
        System.out.println();
    }

    public void resumeThread() {
        synchronized (lock) {
            paused = false;
            if (day == 0)
                System.out.println("Hari ke-" + day + " telah dilanjutkan!");
            lock.notifyAll();
        }
    }

    public boolean getSlept() {
        return slept.get();
    }

    public int getDailyWorkDuration() {
        return dailyWorkDuration.get();
    }

    public boolean getNotEnoughSleep() {
        return notEnoughSleep.get();
    }

    public boolean getPoopedAfterAte() {
        return poopedAfterAte.get();
    }

    public void setSlept(boolean slept) {
        this.slept.set(slept);
    }

    public void setDailyWorkDuration(int dailyWorkDuration) {
        this.dailyWorkDuration.set(dailyWorkDuration);
    }

    public void setNotEnoughSleep(boolean notEnoughSleep) {
        this.notEnoughSleep.set(notEnoughSleep);
    }

    public void setPoopedAfterAte(boolean poopedAfterAte) {
        this.poopedAfterAte.set(poopedAfterAte);
    }

    public boolean getEaten() {
        return eaten.get();
    }

    public void setEaten(boolean eaten) {
        this.eaten.set(eaten);
    }

    @Override
    public void run() {
        while (true) {
            day = i / 720;
            synchronized (lock) {
                while (paused) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (i % 720 == 0) {
                System.out.println();
                day++;
                System.out.println("Hari ke-" + day + " dimulai!");
                System.out.println();
            }

            // Dampak tidak tidur 10 menit
            int notSleptMark = 0;
            if (!getSlept()) {
                notSleptMark = i;
            } else {
                notEnoughSleep.set(false);
            }

            if (!getSlept() && ((i - notSleptMark) % 600 == 0)) {
                notEnoughSleep.set(true);
                Menu.getCurrentSim().setMood(Menu.getCurrentSim().getMood() - 5);
                Menu.getCurrentSim().setHealth(Menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu kurang tidur, sehingga mempengaruhi mood dan health kamu!");
            }

            // Dampak tidak buang air 4 menit setelah makan
            int notPoopedMark = 0;
            if (getEaten() && !getPoopedAfterAte()) {
                notPoopedMark = i;
            } else {
                notPoopedYet.set(false);
            }

            if (getEaten() && !getPoopedAfterAte() && ((i - notPoopedMark) % 240 == 0)) {
                notPoopedYet.set(true);
                Menu.getCurrentSim().setMood(Menu.getCurrentSim().getMood() - 5);
                Menu.getCurrentSim().setHealth(Menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu belum buang air setelah makan, sehingga mempengaruhi mood dan health kamu!");
            }
            notPoopedYet.set(false);

            try {
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (i % 660 == 0) {
                    System.out.println("Hari ini tersisa 1 menit lagi!");
                    System.out.println();
                } else if (i % 360 == 0) {
                    System.out.println("Telah berlalu setengah hari!");
                    System.out.println();
                } else if (i % 180 == 0) {
                    System.out.println("Hari ini telah berlalu 3 menit!");
                }
            }
        }
    }
}
