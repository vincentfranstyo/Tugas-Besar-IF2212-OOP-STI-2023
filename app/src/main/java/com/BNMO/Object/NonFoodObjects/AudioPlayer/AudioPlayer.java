import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AudioPlayer extends NonFoodObjects {
    private boolean isOn;
    private List<Music> shop;
    private List<Music> library;
    private List<Playlist> playlists;
    private int battery;
    Clip clip;

    public AudioPlayer (String name, int length, int width, int price) {
        super(name, length, width, price);
        this.battery = 100;
        this.shop = new ArrayList<>();
        this.library = new ArrayList<>();
        this.playlists = new ArrayList<>();

        shop.add(new Music("Interaksi", "Tulus", 1, "Pop", new Time(0, 0, 2, 50), 2));
        shop.add(new Music("Jatuh Suka", "Tulus", 2, "Pop", new Time(0, 0, 3, 55), 2));
        shop.add(new Music("Kau Rumahku", "Raissa Anggiani", 3, "Pop", new Time(0, 0, 4, 35), 2));
        shop.add(new Music("Intuisi", "Yura Yunita", 4, "Pop", new Time(0, 0, 4, 0), 2));
        shop.add(new Music("Enchanted", "Taylor Swift", 5, "Pop", new Time(0, 0, 5, 52), 2));
        shop.add(new Music("Love Story (Taylor's Version)", "Taylor Swift", 6, "Pop", new Time(0, 0, 3, 56), 2));
        shop.add(new Music("Jatuh Hati", "Raisa", 6, "Pop", new Time(0, 0, 3, 27), 2));
        shop.add(new Music("Labirin", "Tulus", 7, "Pop", new Time(0, 0, 3, 3), 2));
        shop.add(new Music("Perih", "Vierra", 8, "Pop", new Time(0, 0, 3, 20), 2));
        shop.add(new Music("All Too Well (10 Minute Version) (Taylor's Version)", "Taylor Swift", 9, "Pop", new Time(0, 0, 10, 13), 2));
        shop.add(new Music("Red (Taylor's Version)", "Taylor Swift", 10, "Pop", new Time(0, 0, 3, 43), 2));
        shop.add(new Music("Hari Bersamanya", "Sheila On 7", 11, "Pop", new Time(0, 0, 3, 53), 2));
        shop.add(new Music("Remaja", "Hivi!", 12, "Pop", new Time(0, 0, 3, 38), 2));
        shop.add(new Music("Back To December", "Taylor Swift", 13, "Pop", new Time(0, 0, 4, 53), 2));
        shop.add(new Music("us", "keshi", 14, "Pop", new Time(0, 0, 4, 8), 2));
        shop.add(new Music("like i need u", "keshi", 15, "Pop", new Time(0, 0, 3, 2), 2));
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void turnOn() {
        if (getIsOn()) {
            System.out.println(getName() + " is already on!");
        }
        else {
            if (getBattery() > 0) {
                this.isOn = true;
                System.out.println(getName() + " has been turned on!");
            }
            else {
                System.out.println("Battery is empty!");
            }
        }
    }

    public void turnOff() {
        if (getIsOn()) {
            this.isOn = false;
            System.out.println(getName() + " has been turned off!");
        }
        else {
            System.out.println(getName() + " is already off!");
        }
    }


    public List<Music> getLibrary() {
        return library;
    }

    public List<Music> getMusic() {
        return shop;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist) {
        this.playlists.remove(playlist);
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void charge(Time time) {
        int duration = time.convertToSecond();

        setBattery((getBattery() + duration/20));
        System.out.println("Battery is charged to " + getBattery() + "%");
    }

    public void buy(Music music, Sim sim) {
        if (getIsOn()) {
            if (sim.getMoney() >= music.getPrice()) {
                    library.add(music);
                    sim.setMoney(sim.getMoney() - music.getPrice());
                    System.out.println("You have bought " + music.getName() + " by " + music.getArtist() + " for " + music.getPrice() + " dollars!");
                }
             else {
                System.out.println("You don't have enough money!");
            }
        }
        else {
            System.out.println("Turn on the audio player first!");
        }
    }

    public void showShop() {
        if (getIsOn()) {
            System.out.println("Music Shop");
            System.out.println("==========");
            System.out.println("You can buy these music:");
            for (Music music : getMusic()) {
                System.out.println("ID[" + music.getMusicID() + "] " +music.getName() + " - " + music.getArtist());
            }
            System.out.println("");
        }
        else {
            System.out.println("Turn on the audio player first!");
        }
    }

    public void showLibrary() {
        if (getIsOn()) {
            System.out.println("Library");
            System.out.println("=================");
            for (Music music : getLibrary()) {
                System.out.println(music.getName() + " - " + music.getArtist() + "(" + music.getLength().displayHHMMSS() + ")");
            }
            System.out.println("");
        }
        else {
            System.out.println("Turn on the audio player first!");
        }
    }

    public void audioPlayerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi there! Welcome to " + getName() + "!");
        System.out.println("What would you like to do?");
        System.out.println("[1] Turn on");
        System.out.println("[2] Turn off");
        System.out.println("[3] Charge");
        System.out.println("[4] Show music shop");
        System.out.println("[5] Show playlists");
        System.out.println("[6] Add playlist");
        System.out.println("[7] Remove playlist");
        System.out.println("[8] Buy music");
        System.out.println("[9] Show Library");
        System.out.println("[10] Play music");

        boolean done = false;
        final AtomicBoolean stopRequested = new AtomicBoolean(false);
        while (!done) {
            try {
                System.out.print("Enter your choice: ");
                String op = scanner.nextLine();
                if (op.equalsIgnoreCase("exit")) {
                    done = true;
                    break;
                }
                System.out.println(" ");
                int choice = Integer.parseInt(op);
                if (choice < 1 || choice > 10) {
                    System.out.println("Invalid input. Please enter a number between 1 and 10.");
                } else {
                    switch (choice) {
                        case 1:
                            turnOn();
                            break;
                        case 2:
                            turnOff();
                            break;
                        case 3:
                            charge(new Time(0,0,50,20));
                            break;
                        case 4:
                            showShop();
                            break;
                        case 5:
                        int count = 1;
                            System.out.println("Here are your playlists:");
                            for (Playlist playlist : getPlaylists()) {
                                System.out.println(count + ". " + playlist.getName());
                                count++;
                            }
                            break;
                        case 6:
                            System.out.print("Enter the playlist name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter the playlist description: ");
                            String desc = scanner.nextLine();
                            System.out.print("Enter the playlistID: ");
                            int playlistID = Integer.parseInt(scanner.nextLine());
                            playlists.add(new Playlist(name, desc, playlistID));
                            System.out.println(" ");
                            System.out.print("Playlist " + name + " has been added!");
                            break;
                        case 7:
                            //removePlaylist();
                            break;
                        case 8:
                            showShop();
                            System.out.print("Select the music you want to buy: ");
                            int musicID = Integer.parseInt(scanner.nextLine());
                            for (Music music : getMusic()) {
                                if (music.getMusicID() == musicID) {
                                    buy(music, new Sim("Budi"));
                                }
                            }
                            break;
                        case 9:
                            showLibrary();
                            break;
                        case 10:
                            System.out.println("Select the music you want to play: ");
                            System.out.println("[1] Back To December");
                            System.out.println("[2] The Story Of Us");
                            System.out.println("[3] Kibou");
                            System.out.print("Enter your choice: ");
                            int choice2 = Integer.parseInt(scanner.nextLine());
                             try {
                                AudioInputStream audioIn = null;
                                // Open the audio file
                                if (choice2 == 1) {
                                    System.out.println("Playing Back To December....");
                                    audioIn = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource("Back To December.wav"));
                                }
                                else if (choice2 == 2){
                                    System.out.println("Playing The Story Of Us....");
                                    audioIn = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource("The Story Of Us.wav"));
                                }
                                else if (choice2 == 3){
                                    System.out.println("Playing Kibou....");
                                    audioIn = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource("Kibou.wav"));
                                }
                                // Get a clip to play the audio
                                Clip clip = AudioSystem.getClip();

                                // Open the clip and load the audio data from the input stream
                                clip.open(audioIn);

                                // Start playing the audio
                                clip.start();
                                Scanner reader = new Scanner(System.in);
                                Thread inputThread = new Thread(() -> {
                                    try {
                                        while (clip.isRunning()) {
                                            String input = reader.nextLine();
                                                if (input.equalsIgnoreCase("stop")) {
                                                    stopRequested.set(true);  // set stopRequested to true
                                                    break;
                                                }
                                            Thread.sleep(10);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                                inputThread.start();

                                long totalLength = clip.getMicrosecondLength();
                                // Wait for the audio to finish playing
                                while (!clip.isRunning() && !stopRequested.get()) // modified condition
                                    Thread.sleep(10);

                                // Display the remaining time every 1 second
                                long prevRemaining = -1;
                                while (clip.isRunning() && !stopRequested.get()) { // modified condition
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
                                stopRequested.set(false);  // reset stopRequested to false
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
        scanner.close();
    }
}
