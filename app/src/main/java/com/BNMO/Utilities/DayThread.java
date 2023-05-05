package com.BNMO.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DayThread implements Runnable {
    private static DayThread dayThreadInstance;
    private static AtomicInteger dailyWorkDuration = new AtomicInteger(0);
    private static AtomicBoolean slept = new AtomicBoolean(false);
    private static AtomicBoolean sleepPenalty = new AtomicBoolean(false);
    private static AtomicBoolean eaten = new AtomicBoolean(false);
    private static AtomicBoolean poopedAfterAte = new AtomicBoolean(false);
    private static AtomicBoolean poopPenalty = new AtomicBoolean(false);
    private static AtomicBoolean workAvail = new AtomicBoolean(true);
    private static boolean paused = false;
    private static final Object lock = new Object();
    private static int notSleptMark = -1;
    private static int notPoopedMark = -1;
    private static AtomicInteger daySec = new AtomicInteger(720);
    private static int day;
    private static int mins;
    private static int secs;

    private DayThread() {
    }

    public static DayThread getInstance() {
        if (dayThreadInstance == null) {
            dayThreadInstance = new DayThread();
        }
        return dayThreadInstance;
    }

    public static void pauseThread() {
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

    public static int getDaySec() {
        return daySec.get();
    }

    public static void setDaySec(int daySecVar) {
        daySec.set(daySecVar);
    }

    public static void timeLeftForTheDay() {
        mins = (getDaySec() + 1) % 720 / 60;
        secs = (getDaySec() + 1) % 720 % 60;
        System.out.println(String.format("%02d", mins) + ":" + String.format("%02d", secs) + " out of 12 mins");
    }

    public static void resumeThread() {
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

    public static void setWorkAvail(boolean workAvailVar) {
        workAvail.set(workAvailVar);
    }

    public static boolean getWorkAvail() {
        return workAvail.get();
    }

    public static boolean getSlept() {
        return slept.get();
    }

    public static int getDailyWorkDuration() {
        return dailyWorkDuration.get();
    }

    public static boolean getSleepPenalty() {
        return sleepPenalty.get();
    }

    public static boolean getPoopedAfterAte() {
        return poopedAfterAte.get();
    }

    public static void setSlept(boolean sleptVar) {
        slept.set(sleptVar);
    }

    public static void setDailyWorkDuration(int dailyWorkDurationVar) {
        dailyWorkDuration.set(dailyWorkDurationVar);
    }

    public static void setNotEnoughSleep(boolean sleepPenaltyVar) {
        sleepPenalty.set(sleepPenaltyVar);
    }

    public static void setPoopedAfterAte(boolean poopedAfterAteVar) {
        poopedAfterAte.set(poopedAfterAteVar);
    }

    public static boolean getEaten() {
        return eaten.get();
    }

    public static void setEaten(boolean eatenVar) {
        eaten.set(eatenVar);
    }

    public static boolean getPoopPenalty() {
        return poopPenalty.get();
    }

    public static void setPoopPenalty(boolean poopPenaltyVar) {
        poopPenalty.set(poopPenaltyVar);
    }

    public static boolean getPaused() {
        return paused;
    }

    @Override
    public void run() {
        Menu menu = Menu.getInstance();
        while (true) {
            day = getDaySec() / 720;
            synchronized (lock) {
                while (paused) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (getDaySec() % 720 == 0) {
                System.out.println();
                System.out.println("Hari ke-" + day + " dimulai!");
                System.out.println();
                setWorkAvail(true);
                setDailyWorkDuration(0);
            }

            try {
                Thread.sleep(1000);
                setDaySec(getDaySec() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
            if (getSleepPenalty() && ((getDaySec() - notSleptMark) % 600 == 0)) {
                System.out.println("ini i : " + getDaySec());
                System.out.println("ini moodnya skrg " + menu.getCurrentSim().getMood());
                System.out.println("ini healthnya skrg " + menu.getCurrentSim().getHealth());
                menu.getCurrentSim().setMood(menu.getCurrentSim().getMood() - 5);
                menu.getCurrentSim().setHealth(menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu kurang tidur, sehingga mempengaruhi mood dan health kamu!");
                notSleptMark = -1;
            }

            if (getPoopPenalty() && ((getDaySec() - notPoopedMark) % 240 == 0)) {
                menu.getCurrentSim().setMood(menu.getCurrentSim().getMood() - 5);
                menu.getCurrentSim().setHealth(menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu belum buang air setelah makan, sehingga mempengaruhi mood dan health kamu!");
                notPoopedMark = -1;
                poopPenalty.set(false);
            }

            // Dampak tidak tidur 10 menit
            if (!getSlept() && notSleptMark == -1) {
                System.out.println("not slept mark is triggered");
                notSleptMark = getDaySec();
                sleepPenalty.set(true);
            } else if (getSlept()) {
                sleepPenalty.set(false);
            }

            // Dampak tidak buang air 4 menit setelah makan
            if (getEaten() && !getPoopedAfterAte() && notPoopedMark == -1) {
                System.out.println("not pooped mark is triggered");
                notPoopedMark = getDaySec();
                poopPenalty.set(true);
            } else if (getPoopedAfterAte()) {
                poopPenalty.set(false);
            }
        }
    }
}
