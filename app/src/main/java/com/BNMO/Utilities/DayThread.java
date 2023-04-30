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
        int i = 1;
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
            // TODO ini jadiin cek per detik
            try {
                System.out.println("Hari ke-" + i + " telah dimulai!");
                System.out.println();
                Thread.sleep(360000);
                System.out.println();
                System.out.println("Telah berlalu setengah hari!");
                System.out.println();
                Thread.sleep(300000);
                System.out.println();
                System.out.println("Hari ini tersisa 1 menit dalam waktu nyata!");
                System.out.println();
                Thread.sleep(60000);
                System.out.println();
                System.out.println("Hari telah berganti!");
                System.out.println();
                i++;

                if (dailySleptDur.get() < 180) {
                    notEnoughSleep.set(true);
                    System.out.println("Kamu tidak cukup tidur!");
                    System.out.println();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
