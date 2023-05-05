package com.BNMO.SIMS;

import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.Object;
import com.BNMO.Buildings.*;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;
import com.BNMO.Object.NonFoodObjects.Bed.SingleBed;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Stove.GasStove;
import com.BNMO.Object.NonFoodObjects.Clock.Clock;
import com.BNMO.World;

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
    private static ArrayList<Sim> sims = new ArrayList<Sim>();
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
        this.money = 1000000; // 100
        this.fullness = 80; // 80
        this.mood = 80; // 80
        this.health = 80; // 80
        this.status = "Nothing";
        this.isAlive = true;
        this.inventory = new Inventory<Object>(this);
        System.out.println("kena location");
        this.location = new Point(2, 2); // TODO ini harusnya random tpi ngecek dulu

        World world = World.getInstance();
        this.currentHouse = new House(this.location, this);
        this.currentRoom = currentHouse.getRooms().next();
        world.addHouse(currentHouse);

        this.currentJobDuration = new Time();
        this.inventory.addObject(new Toilet("Toilet 1"));
        this.inventory.addObject(new GasStove("Kompor Gas 1"));
        this.inventory.addObject(new TableAndChair("Meja Makan 1"));
        this.inventory.addObject(new Clock("Jam 1"));
        this.inventory.addObject(new SingleBed("Kasur 1"));
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
        } else if (fullness <= 0) {
            this.fullness = 0;
            setAlive(false);
            System.out.println("Kamu mati karena kelaparan.");
        } else {
            this.fullness = fullness;
        }
    }

    public void setMood(int mood) {
        if (mood > 100) {
            this.mood = 100;
        } else if (mood <= 0) {
            this.mood = 0;
            setAlive(false);
            System.out.println("Kamu mati karena depresi.");
        } else {
            this.mood = mood;
        }

    }

    public void setHealth(int health) {
        if (health > 100) {
            this.health = 100;
        } else if (health <= 0) {
            this.health = 0;
            setAlive(false);
            System.out.println("Kamu mati karena sakit.");
        } else {
            this.health = health;
        }
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

    public static ArrayList<Sim> getSims() {
        return sims;
    }

    public void work(Time time) {
        int duration = time.convertToSecond();
        try {
            Time jobDurTime = new Time(getCurrentJobDuration().convertToSecond() + duration);
            setCurrentJobDuration(jobDurTime);
            setStatus("Working");
            System.out.println(getName() + " is working...");
            Thread.sleep(duration * 1000);
            System.out.println(getName() + " is done working");
            setFullness(getFullness() - 10 * (duration / 30));
            setMood(getMood() - 10 * (duration / 30));
            setMoney(getMoney() + (getJob().getSalary() * (duration / 240)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setStatus("Nothing");
        }
    }

    public void workout(Time time) {
        int duration = time.convertToSecond();
        try {
            setStatus("Working Out");
            System.out.println(getName() + " is working out...");
            Thread.sleep(duration * 1000);
            System.out.println(getName() + " is done working out");
            setFullness(getFullness() - 5 * (duration / 20));
            setMood(getMood() + 10 * (duration / 20));
            setHealth(getHealth() + 5 * (duration / 20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setStatus("Nothing");
        }
    }

    public void changeJob(String jobName) {
        System.out.println("Current job duration: " + currentJobDuration.convertToSecond() + " seconds");
        if (currentJobDuration.convertToSecond() >= 720) {
            // Check if the jobName key exists in the map
            if (Job.getJobs().containsKey(jobName)) {
                int salary = Job.getJobs().get(jobName);
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
            Random rand = new Random();
            int randomNum = rand.nextInt(1, 5);
            System.out.println("Kamu akan mendapatkan item dalam " + randomNum + " menit");
            DayThread dayThread = DayThread.getInstance();
            int currentSec = dayThread.getDaySec();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            int newCurrentSec = dayThread.getDaySec();
                            dayThread.setBuyingCountTime(randomNum - ((newCurrentSec - currentSec) % 60));
                            if (!dayThread.getPaused()) {
                                if (newCurrentSec - currentSec == randomNum * 60) {
                                    System.out.println("Item mu telah sampai");
                                    setMoney(getMoney() - object.getPrice());
                                    inventory.addObject(object);
                                    break;
                                } else if ((newCurrentSec - currentSec) % 60 == 0) {
                                    System.out.println("Item mu akan sampai dalam "
                                            + (randomNum - ((newCurrentSec - currentSec) % 60)) + " menit");
                                }
                                // Thread.sleep(1500);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
        } else {
            System.out.println("You don't have enough money");
        }

        // try {
        // Thread.sleep(randomNum * 60000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
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

    public void goToObject(NonFoodObjects nfo) {
        setLocation(nfo.getPosition());
    }

}
