package com.BNMO.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DayThread implements Runnable {
    private static DayThread dayThreadInstance;
    private AtomicInteger dailyWorkDuration = new AtomicInteger(0);
    private AtomicBoolean slept = new AtomicBoolean(false);
    private AtomicBoolean sleepPenalty = new AtomicBoolean(false);
    private AtomicBoolean eaten = new AtomicBoolean(false);
    private AtomicBoolean poopedAfterAte = new AtomicBoolean(false);
    private AtomicBoolean poopPenalty = new AtomicBoolean(false);
    private AtomicBoolean workAvail = new AtomicBoolean(true);
    private AtomicInteger buildingCountTime = new AtomicInteger(0);
    private AtomicInteger buyingCountTime = new AtomicInteger(0);
    private AtomicBoolean changeSimToday = new AtomicBoolean(true);
    private AtomicBoolean isBuying = new AtomicBoolean(false);
    private boolean paused = false;
    private final Object lock = new Object();
    private int notSleptMark = -1;
    private int notPoopedMark = -1;
    private AtomicInteger daySec = new AtomicInteger(720);
    private int day;
    private int mins;
    private int secs;

    private DayThread() {
    }

    public static DayThread getInstance() {
        if (dayThreadInstance == null) {
            dayThreadInstance = new DayThread();
        }
        return dayThreadInstance;
    }

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

    public boolean getChangeSimToday() {
        return changeSimToday.get();
    }

    public void setChangeSimToday(boolean changeSimTodayVar) {
        changeSimToday.set(changeSimTodayVar);
    }

    public boolean getIsBuying() {
        return isBuying.get();
    }

    public void setIsBuying(boolean isBuyingVar) {
        isBuying.set(isBuyingVar);
    }

    public int getBuildingCountTime() {
        return buildingCountTime.get();
    }

    public void setBuildingCountTime(int countTimeVar) {
        buildingCountTime.set(countTimeVar);
    }

    public int getBuyingCountTime() {
        return buyingCountTime.get();
    }

    public void setBuyingCountTime(int countTimeVar) {
        buyingCountTime.set(countTimeVar);
    }

    public int getDaySec() {
        return daySec.get();
    }

    public void setDaySec(int daySecVar) {
        daySec.set(daySecVar);
    }

    public void timeLeftForTheDay() {
        mins = (getDaySec()) % 720 / 60;
        secs = (getDaySec()) % 720 % 60;
        System.out.println("Day " + day);
        System.out.println("Waktu tersisa untuk hari ini: ");
        System.out.println(String.format("%02d", mins) + ":" + String.format("%02d", secs) + " out of 12 mins");
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

    public void setWorkAvail(boolean workAvailVar) {
        workAvail.set(workAvailVar);
    }

    public boolean getWorkAvail() {
        return workAvail.get();
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

    public void setSlept(boolean sleptVar) {
        slept.set(sleptVar);
    }

    public void setDailyWorkDuration(int dailyWorkDurationVar) {
        dailyWorkDuration.set(dailyWorkDurationVar);
    }

    public void setNotEnoughSleep(boolean sleepPenaltyVar) {
        sleepPenalty.set(sleepPenaltyVar);
    }

    public void setPoopedAfterAte(boolean poopedAfterAteVar) {
        poopedAfterAte.set(poopedAfterAteVar);
    }

    public boolean getEaten() {
        return eaten.get();
    }

    public void setEaten(boolean eatenVar) {
        eaten.set(eatenVar);
    }

    public boolean getPoopPenalty() {
        return poopPenalty.get();
    }

    public void setPoopPenalty(boolean poopPenaltyVar) {
        poopPenalty.set(poopPenaltyVar);
    }

    public boolean getPaused() {
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
                setChangeSimToday(true);
            }

            try {
                Thread.sleep(1000);
                setDaySec(getDaySec() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
            if (getSleepPenalty() && ((getDaySec() - notSleptMark) % 600 == 0)) {
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
