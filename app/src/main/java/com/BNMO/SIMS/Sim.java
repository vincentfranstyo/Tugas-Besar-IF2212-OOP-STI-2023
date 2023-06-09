package com.BNMO.SIMS;

import com.BNMO.Utilities.*;
import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.Object;
import com.BNMO.Buildings.*;
import com.BNMO.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Sim {
    private String name;
    private Job job;
    private int money;
    private int fullness;
    private int mood;
    private int health;
    private String status = "Nothing";
    private final Inventory<Object> inventory;
    private Point location;
    private Time currentJobDuration;
    private static final ArrayList<Sim> sims = new ArrayList<Sim>();
    private boolean isAlive;
    private House currentHouse;
    private Room currentRoom;

    private boolean isBuying = false;

    private final AtomicInteger dailyWorkDuration = new AtomicInteger(0);

    private final AtomicBoolean slept = new AtomicBoolean(false);

    private final AtomicBoolean sleepPenalty = new AtomicBoolean(false);

    private final AtomicBoolean eaten = new AtomicBoolean(false);

    private final AtomicBoolean poopedAfterAte = new AtomicBoolean(false);

    private final AtomicBoolean poopPenalty = new AtomicBoolean(false);

    private final AtomicBoolean workAvail = new AtomicBoolean(true);

    private int notSleptMark = -1;

    private int notPoopedMark = -1;

    private final AtomicInteger buildingCountTime = new AtomicInteger(0);

    private final AtomicInteger buyingCountTime = new AtomicInteger(0);

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

        World world = World.getInstance();
        Random rand = new Random();

        int xRandom = rand.nextInt(65);
        if (xRandom == 0) {
            xRandom++;
        } else if (xRandom % 3 == 0) {
            xRandom--;
        }

        Random rand2 = new Random();

        int yRandom = rand2.nextInt(65);
        if (yRandom == 0) {
            yRandom++;
        }
        if (yRandom % 5 == 0) {
            yRandom--;
        }

        Point initPoint = new Point(xRandom, yRandom);
        this.currentHouse = new House(initPoint, this);
        world.addHouse(currentHouse);

        this.currentRoom = currentHouse.getRooms().next();
        this.location = initPoint;
        this.currentJobDuration = new Time();
        sims.add(this);
    }

    public Sim(String name, Point location) {
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

        World world = World.getInstance();

        this.currentHouse = new House(location, this);
        world.addHouse(currentHouse);

        this.currentRoom = currentHouse.getRooms().next();
        this.location = location;
        this.currentJobDuration = new Time();
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

    public void setSleepPenalty(boolean value) {
        sleepPenalty.set(value);}

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

    public void work(Time time) {
        int duration = time.convertToSecond();
        try {
            Time jobDurTime = new Time(getCurrentJobDuration().convertToSecond() + duration);
            setCurrentJobDuration(jobDurTime);
            setStatus("Working");
            System.out.println(getName() + " is working...");
            Thread.sleep(duration * 1000L);
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
            Thread.sleep(duration * 1000L);
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
        double distance = Math.sqrt((Math.pow(location.getX()-house.getLocation().getX(), 2)) + (Math.pow(location.getY()-house.getLocation().getY(), 2)));
        try {
            setStatus("Visiting");
            int duration = ((int) distance)*2;
            System.out.println("Perjalanan Visit Akan Memakan Waktu Selama "+ duration +" Detik");
            System.out.println(getName() + " is on the way...");
            Thread.sleep(duration * 1000L);
            setFullness(getFullness() - 10 * (duration / 30));
            setMood(getMood() + 10 * (duration / 30));
            setCurrentHouse(house);
            setLocation(house.getLocation());
            setCurrentRoom(house.getRooms().next());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setStatus("Nothing");
        }
    }

    public synchronized void buy(Object object) {
        if (getMoney() >= object.getPrice()) {
            Random rand = new Random();
            int randomNum = rand.nextInt(1, 5);
            System.out.println("Kamu akan mendapatkan item dalam " + randomNum + " menit");
            if (randomNum > 2){
                System.out.println("Kamu pasti memilih kurir murahan");
            }
            DayThread dayThread = DayThread.getInstance();
            Menu menu = Menu.getInstance();
            int currentSec = dayThread.getDaySec();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    synchronized (this){
                        while (true) {
                            try {
                                int newCurrentSec = dayThread.getDaySec();
                                if (!dayThread.getPaused()) {
                                    if ((newCurrentSec - currentSec) == randomNum * 60 - 1) {
                                        System.out.println("Item mu telah sampai");
                                        setMoney(getMoney() - object.getPrice());
                                        inventory.addObject(object);
                                        menu.getCurrentSim().setIsBuying(false);
                                        break;
                                    } else if ((newCurrentSec - currentSec) % 60 - 1 == 0) {
                                        System.out.println("Item akan sampai dalam "
                                                + (randomNum - ((newCurrentSec - currentSec) / 60)) + " menit");
                                        Thread.sleep(1500);
                                    }
                                }
                                menu.getCurrentSim().setBuyingCountTime(randomNum * 60 - (newCurrentSec - currentSec));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            t.start();
        } else {
            System.out.println("You don't have enough money, what a disgrace this family, 去做工啊!");
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

    public boolean getIsBuying() {
        return isBuying;
    }

    public void setIsBuying(boolean isBuyingVar) {
        isBuying = isBuyingVar;
    }
    public void goToObject(NonFoodObjects nfo) {
        setLocation(nfo.getPosition());
    }

    public int getNotSleptMark() {
        return this.notSleptMark;
    }

    public void setNotSleptMark(int notSleptMark) {
        this.notSleptMark = notSleptMark;
    }

    public int getNotPoopedMark() {
        return this.notPoopedMark;
    }

    public void setNotPoopedMark(int notPoopedMark) {
        this.notPoopedMark = notPoopedMark;
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

}
