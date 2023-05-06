package com.BNMO.Object.NonFoodObjects.AudioPlayer;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Utilities.*;
import com.BNMO.SIMS.Sim;
import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AudioPlayer extends NonFoodObjects {
    private boolean isOn;
    private final List<Music> shop;
    private final List<Music> library;
    private int battery;
    Clip clip;

    public AudioPlayer(String name) {
        super(name, 2, 2, 50);
        this.battery = 100;
        this.shop = new ArrayList<>();
        this.library = new ArrayList<>();
        this.setType("Audio Player");

        shop.add(new Music("Forever", "Vanze feat Brenton Mattheus", 1, "House", new Time(0, 0, 4, 12), 2));
        shop.add(new Music("Heroes Ignore", "Janji", 2, "House", new Time(0, 0, 3, 28), 2));
        shop.add(new Music("High", "JPB", 3, "Trap", new Time(0, 0, 3, 12), 2));
        shop.add(new Music("Invincible", "DEAF KEV", 4, "Trap", new Time(0, 0, 4, 33), 2));
        shop.add(new Music("On & On", "Cartoon", 5, "Drumstep", new Time(0, 0, 3, 28), 2));
        shop.add(new Music("Shine", "Spektrem", 6, "House", new Time(0, 0, 4, 19), 2));
        shop.add(new Music("Why We Lose", "Cartoon feat Coleman Trapp", 6, "D&B", new Time(0, 0, 3, 33), 2));
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void turnOn() {
        if (getIsOn()) {
            System.out.println(getName() + " is already on!");
        } else {
            if (getBattery() > 0) {
                this.isOn = true;
                System.out.println(getName() + " has been turned on!");
            } else {
                System.out.println("Battery is empty!");
            }
        }
    }

    public void turnOff() {
        if (getIsOn()) {
            this.isOn = false;
            System.out.println(getName() + " has been turned off!");
        } else {
            System.out.println(getName() + " is already off!");
        }
    }

    public List<Music> getLibrary() {
        return library;
    }

    public List<Music> getMusic() {
        return shop;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void charge(Time time) {
        int duration = time.convertToSecond();

        if ((getBattery() + duration / 2) > 100) {
            setBattery(100);
        } else {
            setBattery((getBattery() + duration / 2));
        }
        System.out.println("Battery is charged to " + getBattery() + "%");
    }

    public void buy(Music music, Sim sim) {
        if (getIsOn()) {
            if (sim.getMoney() >= music.getPrice()) {
                library.add(music);
                sim.setMoney(sim.getMoney() - music.getPrice());
                sim.setMood(sim.getMood() + 3);
                System.out.println("You have bought " + music.getName() + " by " + music.getArtist() + " for "
                        + music.getPrice() + " dollars!");
            } else {
                System.out.println("You don't have enough money!");
            }
        } else {
            System.out.println("Turn on the audio player first!");
        }
    }

    public void showShop() {
        if (getIsOn()) {
            System.out.println("Music Shop");
            System.out.println("==========");
            System.out.println("You can buy these music:");
            for (Music music : getMusic()) {
                System.out.println("ID[" + music.getMusicID() + "] " + music.getName() + " - " + music.getArtist());
            }
            System.out.println();
        } else {
            System.out.println("Turn on the audio player first!");
        }
    }

    public void showLibrary() {
        if (getIsOn()) {
            if (!getLibrary().isEmpty()) {
                System.out.println("Library");
                System.out.println("=================");
                for (Music music : getLibrary()) {
                    System.out.println(
                            music.getName() + " - " + music.getArtist() + "(" + music.getLength().displayHHMMSS()
                                    + ")");
                }
                System.out.println();
            } else {
                System.out.println("Library is empty!");
            }
        } else {
            System.out.println("Turn on the audio player first!");
        }
    }

    public void playMusic(Sim sim, String title) {
        boolean musicFound = false;

        for (Music music : getLibrary()) {
            if (music.getName().toLowerCase().equals(title)) {
                musicFound = true;
                try {
                    AudioInputStream audioIn = null;
                String filePath = "/" + music.getName() + ".wav";
                InputStream inputStream = getClass().getResourceAsStream(filePath);
                if (inputStream != null) {
                        audioIn = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource("/" + music.getName() + ".wav"));
                        System.out.println("Playing " + music.getName() + "....");                     
                        
                        // Get a clip to play the audio
                        Clip clip = AudioSystem.getClip();

                        // Open the clip and load the audio data from the input stream
                        clip.open(audioIn);

                        // Start playing the audio
                        clip.start();
                        Thread inputThread = new Thread(() -> {
                            try {
                                while (clip.isRunning()) {
                                    Thread.sleep(10);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        inputThread.start();

                        long totalLength = clip.getMicrosecondLength();
                        // Wait for the audio to finish playing
                        while (!clip.isRunning())
                            Thread.sleep(10);

                        // Display the remaining time every 1 second
                        long prevRemaining = -1;
                        while (clip.isRunning()) {
                            long remaining = (totalLength - clip.getMicrosecondPosition()) / 1000000;
                            Time timeLeft = new Time(remaining);
                            String strRemaining = timeLeft.displayHHMMSS();
                            // Only update the remaining time if it has changed
                            if (remaining != prevRemaining) {
                                System.out.print("\r" + strRemaining + " ");
                                prevRemaining = remaining;
                            }

                            Thread.sleep(1000); // Wait 1 second before displaying the next update
                        }

                        // Clear the line after the loop ends
                        System.out.print("\r" + " ".repeat(20) + "\r");
                        // Close the clip and input stream
                        clip.close();
                        audioIn.close();
                        inputThread.interrupt();

                        sim.setMood(sim.getMood() + music.getLength().convertToSecond() / 30);
                        sim.setHealth(sim.getHealth() - 10);
                            setBattery(getBattery() - (int) Math.ceil(music.getLength().convertToSecond() / 30));
                        } else {
                            System.out.println("Failed to open audio file.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (!musicFound) {
                System.out.println("Music not found!");
            }
    }

    public void audioPlayerMenu(Sim sim) {
        Scanner scanner = new Scanner(System.in);

        boolean done = false;
        String op;
        while (!done) {
            System.out.println("Hi there! Welcome to " + getName() + "!");
            System.out.println("What would you like to do?");
            System.out.println("[1] Turn on");
            System.out.println("[2] Turn off");
            System.out.println("[3] Charge");
            System.out.println("[4] Buy music");
            System.out.println("[5] Show Library");
            System.out.println("[6] Play music");
            System.out.println("[7] Help");
            System.out.println("[8] Exit");
            try {
                System.out.print("Enter your choice: ");
                try {
                    op = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    scanner.close();
                    scanner = new Scanner(System.in);
                    op = scanner.nextLine();
                }
                if (op.equalsIgnoreCase("exit")) {
                    done = true;
                    break;
                }
                System.out.println(" ");
                int choice;
 
                choice = Integer.parseInt(op);
                if (choice < 1 || choice > 9) {
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
                } else {
                    switch (choice) {
                        case 1:
                            turnOn();
                            break;
                        case 2:
                            turnOff();
                            break;
                        case 3:
                            System.out.print("Enter how long you want to charge (in seconds): ");
                            int chargeTime = Integer.parseInt(scanner.nextLine());
                            charge(new Time(chargeTime));
                            break;
                        case 4:
                            if (getIsOn()) {
                                showShop();
                                System.out.print("Select the music you want to buy: ");
                                int musicID = Integer.parseInt(scanner.nextLine());
                                for (Music music : getMusic()) {
                                    if (music.getMusicID() == musicID) {
                                        buy(music, sim);
                                    }
                                }
                            } else {
                                System.out.println("Turn on the audio player first!");
                            }
                            break;
                        case 5:
                            if (getIsOn()) {
                                showLibrary();
                            } else {
                                System.out.println("Turn on the audio player first!");
                            }
                            break;
                        case 6:
                            if (getIsOn()) {
                                if (getLibrary().isEmpty()) {
                                    System.out.println("Library is empty! You can buy music from the shop.");
                                    break;
                                }

                                System.out.println("Select the music you want to play: ");
                                showLibrary();
                                System.out.print("Enter the title: ");
                        
                                String title = scanner.nextLine().toLowerCase();
                                playMusic(sim, title);
                            } else {
                                System.out.println("Turn on the audio player first!");
                            }
                            break;
                        case 7:
                            System.out.println("Welcome to " + getName() + "!");
                            System.out.println("In order to use this audio player, you need to turn it on first.");
                            System.out.println("To play a music, you need to buy it first from the shop.");
                            System.out.println("You can play a music by entering its titlel.");
                            System.out.println("You can also add, remove, or play a playlist.");
                            System.out.println("Have fun listening!");
                            break;
                        case 8:
                            done = true;
                            System.out.println("Exiting " + getName() + " ...." + " Bye!");
                            break;
                        default:
                            break;
                    }
                }
                System.out.println(" ");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                System.out.println(" ");
            }
        }

    }
}
