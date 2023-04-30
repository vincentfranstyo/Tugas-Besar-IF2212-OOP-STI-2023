package com.BNMO.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DayThread implements Runnable {
    private AtomicInteger dailyWorkDuration = new AtomicInteger(0);
    private AtomicInteger dailySleptDur = new AtomicInteger(0);
    private AtomicBoolean notEnoughSleep = new AtomicBoolean(false);
    private AtomicBoolean eaten = new AtomicBoolean(false);
    private boolean paused = false;
    private final Object lock = new Object();

    public void pauseThread() {
        paused = true;
        System.out.println("Hari telah dijeda!");
        System.out.println("Silahkan melakukan aksi aktif untuk melanjutkan hari!");
        System.out.println();
    }

    public void resumeThread() {
        synchronized (lock) {
            paused = false;
            System.out.println("Hari telah dilanjutkan!");
            lock.notifyAll();
        }
    }

    public int getDailySleptDur() {
        return dailySleptDur.get();
    }

    public int getDailyWorkDuration() {
        return dailyWorkDuration.get();
    }

    public boolean getNotEnoughSleep() {
        return notEnoughSleep.get();
    }

    public boolean getEaten() {
        return eaten.get();
    }

    public void setDailySleptDur(int dailySleptDur) {
        this.dailySleptDur.set(dailySleptDur);
    }

    public void setDailyWorkDuration(int dailyWorkDuration) {
        this.dailyWorkDuration.set(dailyWorkDuration);
    }

    public void setNotEnoughSleep(boolean notEnoughSleep) {
        this.notEnoughSleep.set(notEnoughSleep);
    }

    public void setEaten(boolean eaten) {
        this.eaten.set(eaten);
    }

    @Override
    public void run() {
        int i = 720;
        int day = 1;
        while (true) {
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

                if (dailySleptDur.get() < 180 && i % 720 == 0) {
                    notEnoughSleep.set(true);
                    System.out.println("Kamu tidak cukup tidur!");
                    System.out.println();
                }
            }
        }
    }
}
