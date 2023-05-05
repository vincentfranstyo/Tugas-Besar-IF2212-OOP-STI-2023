package com.BNMO.Object.NonFoodObjects.TV;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TV extends NonFoodObjects {
    private List<Channel> channels;
    private boolean isOn;
    private boolean hasScanned;

    public TV(String name) {
        super(name, 2, 2, 200);
        this.channels = new ArrayList<>();
        this.isOn = false;
        this.hasScanned = false;
        this.setType("TV");
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public void turnOn() {
        if (getIsOn()) {
            System.out.println("The TV is already on");
        } else {
            setIsOn(true);
            System.out.println("The TV is on");
        }
    }

    public void turnOff() {
        if (getIsOn()) {
            setIsOn(false);
            System.out.println("The TV is off");
        } else {
            System.out.println("The TV is already off");
        }
    }

    public boolean getHasScanned() {
        return hasScanned;
    }

    public void setHasScanned(boolean hasScanned) {
        this.hasScanned = hasScanned;
    }

    public void scanChannel() {
        if (getIsOn()) {
            if (!getHasScanned()) {
                System.out.println("Scanning channel...");
                setHasScanned(true);
                channels.add(new Channel("Republican TV", 1, "Politics"));
                channels.add(new Channel("Democrat T        V", 2, "Politics"));
                channels.add(new Channel("Historian TV", 3, "Education"));
                channels.add(new Channel("HBU Max", 4, "Movies"));
                channels.add(new Channel("HBU GO", 5, "Movies"));
                channels.add(new Channel("Bery Sports", 6, "Sports"));
                channels.add(new Channel("Molalala TV", 7, "Sports"));
                channels.add(new Channel("FunFuny TV", 8, "Comedy"));
                channels.add(new Channel("JokesForLizard TV", 9, "Comedy"));
                channels.add(new Channel("National Science TV", 10, "Education"));
            } else {
                System.out.println("You have already scanned for channels");
            }
        } else {
            System.out.println("The TV is off");
        }
    }

    public void addChannel(String name, String genre) {
        if (getIsOn()) {
            if (!getHasScanned()) {
                System.out.println("You haven't scanned for channels yet");
            } else {
                int id = channels.size() + 1;
                for (Channel channel : channels) {
                    if (channel.getChannelID() == id) {
                        id++; // increment until a unique ID is found
                    }
                }

                boolean channelExists = false;
                for (Channel channel : channels) {
                    if (channel.getName().equals(name)) {
                        channelExists = true;
                        break;
                    }
                }

                if (!channelExists) {
                    Channel newChannel = new Channel(name, id, genre);
                    channels.add(newChannel);
                    System.out.println("New channel has been added: " + newChannel.getName());
                } else {
                    System.out.println("Channel already exists");
                }
            }
        } else {
            System.out.println("The TV is off");
        }
    }

    public void removeChannel(int id) {
        if (getIsOn()) {
            if (!getHasScanned()) {
                System.out.println("You haven't scanned for channels yet");
            } else {
                boolean channelRemoved = false;
                for (Channel channel : channels) {
                    if (channel.getChannelID() == id) {
                        channels.remove(channel);
                        channelRemoved = true;
                        System.out.println("Removed channel: " + channel.getName());
                        break;
                    }
                }
                if (!channelRemoved) {
                    System.out.println("Channel not found");
                }
            }
        } else {
            System.out.println("The TV is off");
        }
    }

    public void showChannel() {
        if (getIsOn()) {
            if (!getHasScanned()) {
                System.out.println("You haven't scanned for channels yet");
            } else {
                System.out.println("List of available channel: ");
                for (Channel channel : channels) {
                    System.out.println(channel.getName());
                }
            }
        } else {
            System.out.println("The TV is off");
        }
    }

    public void watchTV(Time time, Sim sim, Channel channel) {
        try {
            if (!getIsOccupied()) {
                setIsOccupied(true);
                if (getIsOn()) {
                    if (sim.getStatus().equals("Nothing")) {
                        if (getHasScanned()) {
                            int duration = time.convertToSecond();
                            System.out.println(sim.getName() + "is watching TV for " + duration + " seconds");
                            Thread.sleep(duration * 1000);
                            sim.setStatus("Watching TV");
                            if (channel.getGenre().equals("Sports")) {
                                int moodChange = (int) (Math.random() * 7) - 3;
                                sim.setMood(sim.getMood() + (3 * duration / 20) + moodChange);
                                sim.setHealth(sim.getHealth() - (2 * duration / 20));
                                sim.setFullness(sim.getFullness() - (2 * duration / 20));
                            } else if (channel.getGenre().equals("Movies")) {
                                sim.setMood(sim.getMood() + (3 * duration / 20));
                                sim.setHealth(sim.getHealth() - (3 * duration / 20));
                                sim.setFullness(sim.getFullness() - (2 * duration / 20));
                            } else if (channel.getGenre().equals("Comedy")) {
                                sim.setMood(sim.getMood() + (6 * duration / 20));
                                sim.setHealth(sim.getHealth() - (2 * duration / 20));
                                sim.setFullness(sim.getFullness() - (2 * duration / 20));
                            } else if (channel.getGenre().equals("Politics")) {
                                sim.setMood(sim.getMood() - (1 * duration / 20));
                                sim.setHealth(sim.getHealth() - (2 * duration / 20));
                                sim.setFullness(sim.getFullness() - (2 * duration / 20));
                            } else if (channel.getGenre().equals("Education")) {
                                sim.setMood(sim.getMood() + (2 * duration / 20));
                                sim.setHealth(sim.getHealth() - (2 * duration / 20));
                                sim.setFullness(sim.getFullness() - (2 * duration / 20));
                            } else {
                                sim.setMood(sim.getMood() + (2 * duration / 20));
                                sim.setHealth(sim.getHealth() - (3 * duration / 20));
                                sim.setFullness(sim.getFullness() - (2 * duration / 20));
                            }
                        } else {
                            System.out.println("You haven't scanned for channels yet");
                        }
                    } else {
                        System.out.println("You can't watch TV while you are " + sim.getStatus());
                    }
                } else {
                    System.out.println("The TV is off");
                }
            } else {
                System.out.println("The TV is occupied");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        } finally {
            setIsOccupied(false);
            sim.setStatus("Nothing");
        }
    }

    public void menuTV(Sim sim) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Hello " + sim.getName() + "!");
        while (!exit) {
            System.out.println("What would you like to do?");
            System.out.println("[1] Turn on the TV");
            System.out.println("[2] Turn off the TV");
            System.out.println("[3] Scan for channels");
            System.out.println("[4] Add a channel");
            System.out.println("[5] Remove a channel");
            System.out.println("[6] Show available channels");
            System.out.println("[7] Watch TV");
            System.out.println("[8] Go back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    turnOn();
                    break;
                case 2:
                    turnOff();
                    System.out.print("The TV is off");
                    break;
                case 3:
                    scanChannel();
                    System.out.print("Successfully added 10 channels!");
                    break;
                case 4:
                    System.out.print("Enter channel name: ");
                    String name = scanner.next();
                    System.out.print("Enter channel genre: ");
                    String genre = scanner.next();
                    addChannel(name, genre);
                    break;
                case 5:
                    System.out.print("Enter channel ID: ");
                    try {
                        int id = scanner.nextInt();
                        removeChannel(id);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer for the channel ID.");
                        scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                    }
                    break;
                case 6:
                    showChannel();
                    break;
                case 7:
                    System.out.print("How long do you want to watch the TV?");
                    System.out.print("Enter the duration in seconds: ");
                    int duration = scanner.nextInt();
                    System.out.println("Which channel do you want to watch?");
                    showChannel();
                    System.out.print("Enter channel name: ");
                    scanner.nextLine(); // Consume the newline character left by scanner.nextInt()
                    String channelName = scanner.nextLine();

                    boolean foundChannel = false;
                    for (Channel channel : channels) {
                        if (channel.getName().equalsIgnoreCase(channelName)) {
                            watchTV(new Time(duration), sim, channel);
                            foundChannel = true;
                            break;
                        }
                    }
                    if (!foundChannel) {
                        System.out.println("Invalid channel name or the channel doesn't exist. Please try again.");
                    }
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}