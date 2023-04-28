package com.BNMO.Object.NonFoodObjects.TV;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

import java.util.ArrayList;
import java.util.List;

public class TV extends NonFoodObjects {
    // this class also has a list of channel
    private List<Channel> channels;
    private Channel currentChannel;
    private boolean isOn;
    private boolean hasScanned;

    public TV(String name, int length, int width, int price, Channel currentChannel) {
        super(name, 2, 2, 200);
        this.channels = new ArrayList<>();
        this.currentChannel = currentChannel;
        this.isOn = false;
        this.hasScanned = false;
        this.setType("TV");
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public boolean getHasScanned() {
        return hasScanned;
    }

    public void setHasScanned(boolean hasScanned) {
        this.hasScanned = hasScanned;
    }

    public Channel getCurrentChannel() {
        if (getIsOn()) {
            return currentChannel;
        } else {
            System.out.println("The TV is off");
            return null;
        }
    }

    public void scanChannel() {
        if (getIsOn()) {
            if (getHasScanned() == false) {
                System.out.println("Scanning channel...");
                setHasScanned(true);
                channels.add(new Channel("Republican TV", 1, "Politics"));
                channels.add(new Channel("Democrat TV", 2, "Politics"));
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

    public void setCurrentChannel(String channelName) {
        if (getIsOn()) {
            if (!getHasScanned()) {
                System.out.println("You haven't scanned for channels yet");
            } else {
                boolean foundChannel = false;
                for (Channel channel : channels) {
                    if (channel != null && channel.getName().equals(channelName)) {
                        foundChannel = true;
                        setCurrentChannel(channel.getName());
                        break;
                    }
                }
                if (!foundChannel) {
                    System.out.println("Channel not found");
                }
            }
        } else {
            System.out.println("The TV is off");
        }
    }

    public void watchTV(Time time, Sim sim) {
        try {
            if (!getIsOccupied()) {
                setIsOccupied(true);
                if (getIsOn()) {
                    if (sim.getStatus().equals("Nothing")) {
                        int duration = time.convertToSecond();
                        System.out.println(sim.getName() + "is watching TV for " + duration + " seconds");
                        Thread.sleep(duration * 1000);
                        sim.setStatus("Watching TV");
                        sim.setMood(sim.getMood() + (4 * duration / 20));
                        sim.setHealth(sim.getHealth() - (3 * duration / 20));
                        sim.setFullness(sim.getFullness() - (2 * duration / 20));
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
        }
    }

}