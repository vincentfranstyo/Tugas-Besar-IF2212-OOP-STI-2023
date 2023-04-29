package com.BNMO.SIMS;

import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.Object;
import com.BNMO.Object.Food.Food;
import com.BNMO.Buildings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Sim {
    private String name;
    private Job job;
    private int money;
    private int fullness;
    private int mood;
    private int health;
    private String status = "Nothing";
    private Inventory<Object> inventory;
    private Point location;
    private Time currentJobDuration;
    private static ArrayList<Sim> sims = new ArrayList<>();
    private boolean isAlive;
    private House currentHouse;
    private Room currentRoom;

    public Sim(String name) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Badut Sulap", 15));
        jobs.add(new Job("Koki", 30));
        jobs.add(new Job("Polisi", 35));
        jobs.add(new Job("Programmer", 45));
        jobs.add(new Job("Dokter", 30));
        jobs.add(new Job("Tentara", 10));
        jobs.add(new Job("PNS", 30));
        jobs.add(new Job("Teknisi", 20));
        jobs.add(new Job("Pramusaji", 15));
        jobs.add(new Job("Pilot", 55));
        jobs.add(new Job("Apoteker", 40));
        jobs.add(new Job("Guru", 10));
        jobs.add(new Job("Fotografer", 20));
        jobs.add(new Job("Pemadam Kebakaran", 20));

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
        this.inventory = new Inventory<Object>(this);
        this.location = new Point(0, 0);
        this.currentHouse = new House(this.location, this);
        this.currentRoom = currentHouse.getRooms().next();
        sims.add(this);
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

    // Ini ku tambahin biar bisa akses inventory soalnya pas deleteRoom berarti
    // semua object di room harus dimasukin lagi ke inventory owner
    public Inventory<Object> getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public House getCurrentHouse() {
        return currentHouse;
    }

    public Room getCurrentRoom() {
        return currentRoom;
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

    public void setCurrentHouse(House currentHouse) {
        this.currentHouse = currentHouse;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void work(Time time) {
        int duration = time.convertToSecond();

        Thread statusThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    setFullness(getFullness() - 10 * duration / 30);
                    setMood(getMood() - 10 * duration / 30);
                    setMoney(getMoney() + getJob().getSalary() * duration / 240);
                    Time jobDurTime = new Time(getCurrentJobDuration().convertToSecond() + duration / 60);
                    setCurrentJobDuration(jobDurTime);
                    setStatus("Working");
                    System.out.println(getName() + "is working...");
                    Thread.sleep(2000); // Sleep for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    setStatus("Nothing");
                }
            }
        });

        statusThread.start();
        try {
            statusThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void workout(Time time) {
        int duration = time.convertToSecond();

        setFullness(getFullness() - 5 * duration / 20);
        setMood(getMood() + 10 * duration / 20);
        setHealth(getHealth() + 5 * duration / 20);
    }

    public void changeJob(String jobName) {
        if (currentJobDuration.convertToSecond() >= 720) {
            // Check if the jobName key exists in the map
            if (getJob().getJobs().containsKey(jobName)) {
                int salary = getJob().getJobs().get(jobName);
                setJob(new Job(jobName, salary));
                setCurrentJobDuration(new Time(0, 0, 0));
                setMoney(getMoney() - job.getSalary() / 2);
            } else {
                System.out.println(jobName + " not found");
            }
        } else {
            System.out.println("You can't change job yet");
        }
    }

    public void visit(House house) {
        setCurrentHouse(house);
    }

    public void buy(Object object) {
        if (getMoney() >= object.getPrice()) {
            setMoney(getMoney() - object.getPrice());
            inventory.addObject(object);
        } else {
            System.out.println("You don't have enough money");
        }

        Random rand = new Random();
        int randomNum = rand.nextInt(1, 5);
        System.out.println("You will get your item in " + randomNum + " minutes");
        try {
            Thread.sleep(randomNum * 60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move(Room room) {
        setCurrentRoom(room);
    }

    public void openInv() {
        inventory.printInventory();
    }

    public void place(NonFoodObjects object, Point location) {
        if (inventory.getObjects().contains(object)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Masukkan Arah Object (Vertikal/Horizontal): ");
            String pilihan = sc.next();
            sc.close();
            inventory.removeObject(object.getName());
            getCurrentRoom().addObject(object, location, pilihan);
        } else {
            System.out.println("You don't have this object");
        }
    }

    public void moveObject(Object object) {

    }

    public void goToObject(NonFoodObjects nfo) {
        setLocation(nfo.getPosition());
    }

    public void updateHouse(House house) {
        // menambah room
        // menghapus room
        // menambah Object
    }

}
