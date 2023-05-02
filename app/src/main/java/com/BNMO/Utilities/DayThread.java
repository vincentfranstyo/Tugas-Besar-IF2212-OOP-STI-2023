package com.BNMO.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DayThread implements Runnable {
    private AtomicInteger dailyWorkDuration = new AtomicInteger(0);
    private AtomicBoolean slept = new AtomicBoolean(false);
    private AtomicBoolean sleepPenalty = new AtomicBoolean(false);
    private AtomicBoolean eaten = new AtomicBoolean(false);
    private AtomicBoolean poopedAfterAte = new AtomicBoolean(false);
    private AtomicBoolean poopPenalty = new AtomicBoolean(false);
    private boolean paused = false;
    private final Object lock = new Object();
    int notSleptMark = -1;
    int notPoopedMark = -1;
    int i = 720;
    int day;

    public void pauseThread() {
        paused = true;
        if (day == 0) {
            System.out.println("Hari ke-" + day + 1 + " telah dijeda!");
            System.out.println("Silahkan melakukan aksi aktif untuk melanjutkan hari!");
            System.out.println();
        } else {
            System.out.println("Hari ke-" + day + " telah dijeda!");
            System.out.println("Silahkan melakukan aksi aktif untuk melanjutkan hari!");
            System.out.println();
        }

    }

    public void resumeThread() {
        synchronized (lock) {
            paused = false;
            if (day == 0) {
                System.out.println("Hari ke-" + day + 1 + " telah dilanjutkan!");
            } else {
                System.out.println("Hari ke-" + day + " telah dilanjutkan!");
            }
            lock.notifyAll();
        }
    }

    public boolean getSlept() {
        return slept.get();
    }

    public int getDailyWorkDuration() {
        return dailyWorkDuration.get();
    }

    public boolean getSleepPenalty() {
        return sleepPenalty.get();
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

    public void setNotEnoughSleep(boolean sleepPenalty) {
        this.sleepPenalty.set(sleepPenalty);
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

    public boolean getPoopPenalty() {
        return poopPenalty.get();
    }

    public void setPoopPenalty(boolean poopPenalty) {
        this.poopPenalty.set(poopPenalty);
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
            }
            if (getSleepPenalty() && ((i - notSleptMark) % 600 == 0)) {
                System.out.println("ini i : " + i);
                System.out.println("ini moodnya skrg " + Menu.getCurrentSim().getMood());
                System.out.println("ini healthnya skrg " + Menu.getCurrentSim().getHealth());
                Menu.getCurrentSim().setMood(Menu.getCurrentSim().getMood() - 5);
                Menu.getCurrentSim().setHealth(Menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu kurang tidur, sehingga mempengaruhi mood dan health kamu!");
                notSleptMark = -1;
            }

            if (getPoopPenalty() && ((i - notPoopedMark) % 240 == 0)) {
                Menu.getCurrentSim().setMood(Menu.getCurrentSim().getMood() - 5);
                Menu.getCurrentSim().setHealth(Menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu belum buang air setelah makan, sehingga mempengaruhi mood dan health kamu!");
                notPoopedMark = -1;
                poopPenalty.set(false);
            }

            // Dampak tidak tidur 10 menit
            if (!getSlept() && notSleptMark == -1) {
                System.out.println("not slept mark is triggered");
                notSleptMark = i;
                sleepPenalty.set(true);
            } else if (getSlept()) {
                sleepPenalty.set(false);
            }

            // Dampak tidak buang air 4 menit setelah makan
            if (getEaten() && !getPoopedAfterAte() && notPoopedMark == -1) {
                System.out.println("not pooped mark is triggered");
                notPoopedMark = i;
                poopPenalty.set(true);
            } else if (getPoopedAfterAte()) {
                poopPenalty.set(false);
            }
        }
    }
}
