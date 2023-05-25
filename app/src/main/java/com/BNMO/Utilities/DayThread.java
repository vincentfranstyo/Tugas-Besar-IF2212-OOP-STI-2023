package com.BNMO.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DayThread implements Runnable {
    private static DayThread dayThreadInstance;
    private final AtomicBoolean changeSimToday = new AtomicBoolean(true);
    private boolean paused = false;
    private final Object lock = new Object();
    private final AtomicInteger daySec = new AtomicInteger(720);
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
            System.out.println("Hari ke-" + day + " telah dijeda!");
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
                System.out.println("Hari ke-" + day + " telah dilanjutkan!");
            } else {
                System.out.println("Hari ke-" + day + " telah dilanjutkan!");
            }
            lock.notifyAll();
        }
    }

    public boolean getPaused() {
        return paused;
    }

    @Override
    public synchronized void run() {
        Menu menu = Menu.getInstance();
        while (true) {
            day = getDaySec() / 720;
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                    setDaySec(getDaySec() + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
                menu.getCurrentSim().setWorkAvail(true);
                menu.getCurrentSim().setDailyWorkDuration(0);
                setChangeSimToday(true);
            }

            if (menu.getCurrentSim().getSleepPenalty() && ((getDaySec() - menu.getCurrentSim().getNotSleptMark()) % 600 == 0)) {
                menu.getCurrentSim().setMood(menu.getCurrentSim().getMood() - 5);
                menu.getCurrentSim().setHealth(menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu kurang tidur, sehingga mempengaruhi mood dan health kamu!");
                menu.getCurrentSim().setNotSleptMark(-1);
            }

            if (menu.getCurrentSim().getPoopPenalty() && ((getDaySec() - menu.getCurrentSim().getNotPoopedMark()) % 240 == 0)) {
                menu.getCurrentSim().setMood(menu.getCurrentSim().getMood() - 5);
                menu.getCurrentSim().setHealth(menu.getCurrentSim().getHealth() - 5);
                System.out.println("Kamu belum buang air setelah makan, sehingga mempengaruhi mood dan health kamu!");
                menu.getCurrentSim().setNotPoopedMark(-1);
                menu.getCurrentSim().setPoopPenalty(false);
            }

            // Dampak tidak tidur 10 menit
            if (!menu.getCurrentSim().getSlept() && menu.getCurrentSim().getNotSleptMark() == -1) {
                System.out.println(menu.getCurrentSim().getName() + " not slept mark");
                menu.getCurrentSim().setNotSleptMark(getDaySec());
                menu.getCurrentSim().setSleepPenalty(true);
            } else if (menu.getCurrentSim().getSlept()) {
                menu.getCurrentSim().setSleepPenalty(false);
            }

            // Dampak tidak buang air 4 menit setelah makan
            if (menu.getCurrentSim().getEaten() && !menu.getCurrentSim().getPoopedAfterAte() && menu.getCurrentSim().getNotPoopedMark() == -1) {
                System.out.println("not pooped mark");
                menu.getCurrentSim().setNotPoopedMark(getDaySec());
                menu.getCurrentSim().setPoopPenalty(true);
            } else if (menu.getCurrentSim().getPoopedAfterAte()) {
                menu.getCurrentSim().setPoopPenalty(false);
            }
        }
    }
}
