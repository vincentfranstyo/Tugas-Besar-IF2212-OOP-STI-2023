/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.BNMO;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.Object.NonFoodObjects.AudioPlayer.AudioPlayer;
import com.BNMO.Object.NonFoodObjects.TV.TV;
import com.BNMO.Object.NonFoodObjects.TableAndChair.TableAndChair;
import com.BNMO.Object.NonFoodObjects.Toilet.Toilet;
import com.BNMO.Object.NonFoodObjects.Book.Book;
import com.BNMO.Object.NonFoodObjects.Bed.*;
import com.BNMO.Object.NonFoodObjects.Piano.Piano;
import com.BNMO.Object.Food.*;

import com.BNMO.Utilities.*;
import com.BNMO.SIMS.Sim;
import com.BNMO.World;

public class App {
    private static int time = 0;

    public static void main(String[] args) {
        Thread timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (true) {
                    try {
                        System.out.println("Hari ke-" + i + " telah dimulai!");
                        System.out.println();
                        Thread.sleep(360000);
                        System.out.println();
                        System.out.println("Telah berlalu setengah hari!");
                        System.out.println();
                        Thread.sleep(300000);
                        System.out.println();
                        System.out.println("Hari ini tersisa 1 menit dalam waktu nyata!");
                        System.out.println();
                        Thread.sleep(60000);
                        System.out.println();
                        System.out.println("Hari telah berganti!");
                        System.out.println();
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        timeThread.start();

        Scanner userInput = new Scanner(System.in);
        System.out.println("Selamat Datang di Sim-Plicity!");
        System.out.print("Apakah kamu ingin memulai permainan? (Y/N) ");
        String startGame = userInput.nextLine();
        System.out.println();

        while (!startGame.equals("Y") && !startGame.equals("N")) {
            System.out.println("Masukkan Y atau N!");
            System.out.print("Apakah kamu ingin memulai permainan? (Y/N) ");
            startGame = userInput.nextLine();
            System.out.println();
        }

        if (startGame.equals("Y")) {
            System.out.println("Masukkan nama sim: ");
            String simName = userInput.nextLine();
            Sim initSim = new Sim(simName);
            Menu menu = new Menu(initSim);
            System.out.println();
            menu.start();

            World world = new World(initSim);

            System.out.println();
            menu.viewSimInfo();
            System.out.println();

            while (menu.isGameStarted()) {
                System.out.println("Apa yang ingin kamu lakukan?");
                System.out.println("[1] Help");
                System.out.println("[2] Lihat info sim");
                System.out.println("[3] Lihat info rumah");
                System.out.println("[4] Melihat Map");
                System.out.println("[5] Menambah SIMS");
                System.out.println("[6] Mengganti SIMS");
                System.out.println("[7] Mengganti pekerjaan");
                System.out.println("[8] Melakukan aktivitas");
                System.out.println("[9] Upgrade rumah");
                System.out.println("[10] Membeli Barang");
                System.out.println("[11] Melihat inventory");
                System.out.println("[12] Exit");
                System.out.println();

                System.out.println("Masukkan perintah: (dalam angka)");
                String command;
                try {
                    command = userInput.nextLine();
                } catch (NoSuchElementException e) {
                    userInput.close();
                    userInput = new Scanner(System.in);
                    command = userInput.nextLine();
                }

                int commandNum;
                try {
                    commandNum = Integer.parseInt(command);
                } catch (NumberFormatException e) {
                    System.out.println("Masukan harus dalam bentuk angka!");
                    command = userInput.nextLine();
                    commandNum = Integer.parseInt(command);
                }

                ArrayList<Integer> validCommands = new ArrayList<Integer>();
                for (int i = 1; i <= 12; i++) {
                    validCommands.add(i);
                }

                while (!validCommands.contains(commandNum)) {
                    System.out.println("Masukkan perintah yang valid!");
                    System.out.println("Masukkan perintah: (dalam angka)");
                    command = userInput.nextLine();
                    commandNum = Integer.parseInt(command);
                }

                if (commandNum == 1) {
                    System.out.println();
                    System.out.println("Kamu memanggil help!");
                    menu.help();
                    System.out.println();
                } else if (commandNum == 2) {
                    System.out.println();
                    System.out.println("Berikut adalah info sim kamu:");
                    menu.viewSimInfo();
                    System.out.println();
                } else if (commandNum == 3) {
                    System.out.println();
                    System.out.println("Berikut adalah info rumah kamu:");
                    menu.getCurrentSim().getCurrentHouse().printRooms();
                    // TODO CLI layout
                    System.out.println();
                } else if (commandNum == 4) {
                    System.out.println();
                    System.out.println("Berikut adalah Map:");
                    // TODO info dunia + layout
                    world.printMap();
                } else if (commandNum == 5) {
                    System.out.println();
                    System.out.println("Masukkan nama sim yang ingin kamu tambahkan: ");
                    String newSimName = userInput.nextLine();
                    menu.addSim(new Sim(newSimName));
                    menu.viewSimList();
                    System.out.println();
                } else if (commandNum == 6) {
                    System.out.println();
                    System.out.println("Masukkan nama sim yang ingin kamu mainkan: ");
                    String wantedSim = userInput.nextLine();
                    menu.changeSim(wantedSim);
                    System.out.println();
                }

                else if (commandNum == 7) {
                    System.out.println();
                    System.out.println("Berikut adalah aktivitas-aktivitas yang bisa kamu lakukan!");
                    System.out.println("[1] Makan");
                    System.out.println("[2] Melihat waktu");
                    System.out.println("[3] Memasak");
                    System.out.println("[4] Membaca");
                    System.out.println("[5] Menulis");
                    System.out.println("[6] Mendengarkan musik");
                    System.out.println("[7] Menonton TV");
                    System.out.println("[8] Bermain piano");
                    System.out.println("[9] Bermain game");
                    System.out.println("[10] Buang air");
                    System.out.println("[11] Tidur");
                    System.out.println();

                    System.out.println("Masukkan perintah: (dalam angka)");
                    String activity = userInput.nextLine();
                    int activityNum;
                    while (true) {
                        try {
                            activityNum = Integer.parseInt(activity);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Masukan harus dalam bentuk angka!");
                            activity = userInput.nextLine();
                        }
                    }
                    ArrayList<Integer> validActivities = new ArrayList<Integer>();
                    for (int i = 1; i <= 11; i++) {
                        validActivities.add(i);
                    }

                    while (!validActivities.contains(activityNum)) {
                        System.out.println("Masukkan angka yang valid!");
                        activity = userInput.nextLine();
                        while (true) {
                            try {
                                activityNum = Integer.parseInt(activity);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Masukan harus dalam bentuk angka!");
                                activity = userInput.nextLine();
                            }
                        }
                    }

                    if (activityNum == 1) {
                        TableAndChair tableValidator = null;
                        while (menu.getCurrentSim().getCurrentRoom().getObjects().hasNext()) {
                            if (menu.getCurrentSim().getCurrentRoom().getObjects().next() instanceof TableAndChair) {
                                tableValidator = (TableAndChair) menu.getCurrentSim().getCurrentRoom().getObjects()
                                        .next();
                                break;
                            }
                        }
                        if (tableValidator == null) {
                            // Tidak ada table and chair di ruangan ini
                            System.out.println("Tidak ada meja dan kursi di ruangan ini!");
                        } else {
                            System.out.println("Kamu memilih untuk makan!");
                            menu.getCurrentSim().goToObject((NonFoodObjects) tableValidator);
                            System.out.println("Berikut daftar makanan yang dapat kamu makan!");
                            menu.getCurrentSim().getInventory().printFoodList();
                            System.out.println("Makanan apa yang ingin kamu makan?");
                            String foodName = userInput.nextLine();
                            String loweredFood = foodName.toLowerCase();

                            ArrayList<String> validFoods = new ArrayList<String>();
                            for (Food food : menu.getCurrentSim().getInventory().getFoods()) {
                                validFoods.add(food.getName().toLowerCase());
                            }

                            while (!validFoods.contains(loweredFood)) {
                                System.out.println("Masukkan makanan yang valid!");
                                foodName = userInput.nextLine();
                                loweredFood = foodName.toLowerCase();
                            }

                            if (menu.getCurrentSim().getInventory().getFood(loweredFood).getType().equals("Dishes")) {
                                timeThread.start();
                                tableValidator.eatDish(menu.getCurrentSim(),
                                        (Dishes) menu.getCurrentSim().getInventory().getFood(loweredFood));
                            }

                            else if (menu.getCurrentSim().getInventory().getFood(loweredFood).getType()
                                    .equals("Ingredients")) {
                                timeThread.start();
                                tableValidator
                                        .eatIngredients(menu.getCurrentSim(),
                                                (Ingredients) menu.getCurrentSim().getInventory().getFood(loweredFood));
                            }

                        }
                    }

                    else if (activityNum == 9) {

                    }
                }

                else if (commandNum == 8) {
                    System.out.println();
                    System.out.println("Pilihan Update:");
                    System.out.println("[1] Menambah Ruangan");
                    System.out.println("[2] Menghapus Ruangan Sekarang");
                    System.out.println("[3] Menambah Object Pada Ruangan Sekarang");
                    System.out.println("[4] Menghapus Object Pada Ruangan Sekarang");
                    System.out.print("Masukkan Pilihan (dalam angka): ");
                    int numUpHouse = userInput.nextInt();
                    System.out.println();
                    if(numUpHouse == 1){
                        // TODO add room in side current room
                        if(menu.getCurrentSim().getCurrentRoom().getFront() != null && menu.getCurrentSim().getCurrentRoom().getRight() != null && menu.getCurrentSim().getCurrentRoom().getBehind() != null && menu.getCurrentSim().getCurrentRoom().getLeft() != null){
                            System.out.println("Tidak Dapat Menambah Ruangan Karena Sudah Ada Ruangan Pada Setip Sisi Ruangan Sekerang");
                        }
                        else{
                            System.out.println("Arah Ruangan:");
                            if(menu.getCurrentSim().getCurrentRoom().getFront() == null) System.out.println("Front");
                            if(menu.getCurrentSim().getCurrentRoom().getRight() == null) System.out.println("Right");
                            if(menu.getCurrentSim().getCurrentRoom().getBehind() == null) System.out.println("Behind");
                            if(menu.getCurrentSim().getCurrentRoom().getLeft() == null) System.out.println("Left");                
                            System.out.println("Pilih Arah Ruangan yang Akan Dibangun: ");
                            String choice = userInput.next();
                            System.out.println("Masukkan Nama Ruangan: ");
                            String roomName = userInput.next();
                            System.out.println();
                            menu.getCurrentSim().getCurrentHouse().addRoom(initSim, menu.getCurrentSim().getCurrentRoom(), roomName, choice);
                        }
                    }
                    else if(numUpHouse == 2){
                        // TODO Delete Current Room (Room pada house Hanya bisa di delete jika lebih dari 1) & move ke some room pada house
                    }
                    else if(numUpHouse == 3){
                        // TODO add object in current room 
                    }
                    else if(numUpHouse == 4){
                        // TODO delete object in current room and add to inventory owner
                    }

                } else if (commandNum == 9) {
                    System.out.println();
                    System.out.println("Berikut adalah barang-barang yang bisa kamu beli!");
                    System.out.println("Masukkan barang yang ingin kamu beli!");
                    String wantedItemName = userInput.nextLine();
                    String loweredName = wantedItemName.toLowerCase();
                    NonFoodObjects wantedItem = null;
                    if (loweredName.equals("piano")) {
                        wantedItem = new Piano("myPiano");
                    }
                    menu.getCurrentSim().buy(wantedItem);
                }

                else if (commandNum == 10) {
                    // menu.getCurrentSim().getInventory()
                    // .addObject(new Book("BukuUwu", 1000, 5, "Meemaw"));
                    // menu.getCurrentSim().getInventory()
                    // .addObject(new Book("BukuAwa", 1000, 5, "Meemaw"));
                    // menu.getCurrentSim().getInventory()
                    // .addObject(new Book("BukuIwi", 1000, 5, "Meemaw"));
                    // menu.getCurrentSim().getInventory()
                    // .addObject(new Book("BukuEwe", 1000, 5, "Meemaw"));
                    // menu.getCurrentSim().getInventory().addObject(new SingleBed("myBed"));
                    // menu.getCurrentSim().getInventory().addObject(new SingleBed("herBed"));
                    System.out.println();
                    menu.getCurrentSim().getInventory().printInventory();
                    System.out.println();
                }

                else if (commandNum == 11) {
                    menu.exit();
                    break;
                }

                else {
                    System.out.println("Perintah tidak dikenali!");
                    System.out.println();
                }
            }
            userInput.close();
            System.exit(0);

        } else {
            System.out.println("Terima kasih telah bermain!");
        }
    }
}
