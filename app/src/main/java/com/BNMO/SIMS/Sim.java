package com.BNMO.SIMS;

import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sim {
    private String name;
    private Job job;
    private int money;
    private int fullness;
    private int mood;
    private int health;
    private String status = "Nothing";
    private Inventory inventory;
    private Point location;
    private Time currentJobDuration;
    private boolean isAlive;

    public Sim(String name) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Badut Sulap", 15));
        jobs.add(new Job("Koki", 30));
        jobs.add(new Job("Polisi", 35));
        jobs.add(new Job("Programmer", 45));
        jobs.add(new Job("Dokter", 30));

        Random random = new Random();
        int index = random.nextInt(jobs.size());
        this.job = jobs.get(index);

        this.name = name;
        this.money = 100;
        this.fullness = 80;
        this.mood = 80;
        this.health = 80;
        this.status = "Nothing";
        this.isAlive = true;
        // this.inventory = new Inventory(this);
        // this.location = location;
    }

    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public int getMoney() {
        return money;
    }

    public int getFullness() {
        return fullness;
    }

    public int getMood() {
        return mood;
    }

    public int getHealth() {
        return health;
    }

    public String getStatus() {
        return status;
    }

    public Point getLocation() {
        return location;
    }

    public Time getCurrentJobDuration() {
        return currentJobDuration;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setFullness(int fullness) {
        if (fullness > 100) {
            this.fullness = 100;
        } else if (fullness < 0) {
            this.fullness = 0;
            setAlive(false);
        } else
            this.fullness = fullness;
    }

    public void setMood(int mood) {
        if (mood > 100) {
            this.mood = 100;
        } else if (mood < 0) {
            this.mood = 0;
            setAlive(false);
        } else
            this.mood = mood;
    }

    public void setHealth(int health) {
        if (health > 100) {
            this.health = 100;
        } else if (health < 0) {
            this.health = 0;
            setAlive(false);
        } else
            this.health = health;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setCurrentJobDuration(Time currentJobDuration) {
        this.currentJobDuration = currentJobDuration;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void work(Time time) {
        int duration = time.convertToSecond();

        setFullness(getFullness() - 10 * duration / 30);
        setMood(getMood() - 10 * duration / 30);
        setMoney(getMoney() + getJob().getSalary() * duration / 240);

        Thread statusThread = new Thread(() -> {
            try {
                setStatus("Working");
                Thread.sleep(240000); // Sleep for 4 minutes
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                setStatus("Nothing");
            }
        });

        statusThread.start();
        try {
            statusThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void workout(Time time) {
        int duration = time.convertToSecond();

        setFullness(getFullness() - 5 * duration / 20);
        setMood(getMood() + 10 * duration / 20);
        setHealth(getHealth() + 5 * duration / 20);
    }

    public void changeJob(Job job) {
        if (currentJobDuration.convertToSecond() >= 720) {
            setJob(job);
            setCurrentJobDuration(new Time(0, 0, 0));
            setMoney(getMoney() - job.getSalary() / 2);
        } else {
            System.out.println("You can't change job yet");
        }
    }

    public void visit(House house) {

    }

    public void buy(NonFoodObjects object) {

    }

    public void move(Room room) {

    }

    public void openInv() {

    }

    public void place(NonFoodObjects object) {

    }

    public void moveObject(Object object) {

    }

    public void goToObject(Object object) {

    }

    public void upgradeHouse() {

    }
}
