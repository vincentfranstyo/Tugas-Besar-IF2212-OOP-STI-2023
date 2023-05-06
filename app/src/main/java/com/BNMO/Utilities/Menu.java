package com.BNMO.Utilities;

import com.BNMO.SIMS.Sim;

import java.util.*;

public class Menu {
    private static Menu instance;
    private boolean gameStarted = false;
    private Sim currentSim;
    private final ArrayList<Sim> simList = new ArrayList<Sim>();

    private Menu() {

    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void start() {
        setGameStarted(true);
        addSim(currentSim);
        System.out.println("Permainan Akan Segera Dimulai...");
        System.out.println("Selamat Bermain !!!");
    }

    public void help() {
        System.out.println("Panduan Game Sim-Plicity");
        System.out.println();
        System.out.println(
                "1. Ketika memulai permainan Sim-Plicity sebuah World akan dibuat dengan ukuran 64x64 dengan peta sebagai berikut:");

        String[][] map = new String[129][129];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i % 10 == 0 && j % 6 == 0) {
                    map[i][j] = "_/";
                } else if (i % 10 == 0 && j % 6 == 1) {
                    map[i][j] = "\\_";
                } else if (i % 10 == 1 && j % 6 == 0) {
                    map[i][j] = "_\\";
                } else if (i % 10 == 1 && j % 6 == 1) {
                    map[i][j] = "/_";
                } else if (j % 6 == 0) {
                    map[i][j] = " |";
                } else if (j % 6 == 1) {
                    map[i][j] = "| ";
                } else if (i % 10 == 0) {
                    map[i][j] = "_";
                } else if (i % 10 == 1) {
                    map[i][j] = "_";
                } else if (j % 2 == 0 && i % 2 == 0) {
                    map[i][j] = "+";
                }
                else {
                    map[i][j] = " ";
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("LEGENDA PETA SIM-PLICITY");
        System.out.println("__ || : Jalan Raya");
        System.out.println("/\\\\/ : Perempatan");
        System.out.println("+ : Lahan Kosong");
        System.out.println("# : Rumah SIM");
        System.out.println("* (0,0) -> Kiri Atas ");
        System.out.println("* (64,64) -> Kanan Bawah");
        System.out.println();

        System.out.println("2. Setiap Sim akan memiliki mood, kekenyangan, dan kesehatan di angka 80 dan uang awal berjumlah 100");
        System.out.println("3. Setiap Sim akan memiliki 1 buah rumah dengan 1 ruangan berukuran 6x6 yang akan digenerate setelah SIM dibuat");
        System.out.println("4. 1 hari dalam Sim-Plicity sama dengan 12 menit di dunia nyata. Setiap harinya, Sim dapat melakukan berbagai aktivitas yang dapat mempengaruhi mood, kekenyangan, kesehatan, dan uang.");
        System.out.println("5. Setiap Sim dapat melakukan aksi aktif, non-aktif, upgrade, atau menambah SIM");
        System.out.println("6. Aksi aktif → aksi-aksi yang memerlukan waktu dan keterlibatan Sim di dalamnya, contohnya kerja, olahraga, tidur, dan lain-lain ");
        System.out.println("7. Aksi non-aktif → aksi-aksi yang tidak memerlukan waktu, contohnya berpindah ruangan, melihat inventory, memasang barang, dan lain-lain ");
        System.out.println("8. Aksi upgrade → aksi-aksi untuk melakukan upgrade yang memerlukan uang dan waktu, tetapi waktunya berjalan beriringan dengan aksi aktif (ketika tidak ada aksi aktif, waktu tidak berjalan), contohnya beli barang dan upgrade rumah (menambah ruangan)");
        System.out.println("9. Aksi menambah Sim → aksi untuk membuat Sim baru yang hanya bisa dilakukan 1 hari sekali");
        System.out.println();
        System.out.println("Peraturan Sim-Plicity:");
        System.out.println("1. Sim akan mati jika salah satu nilai dari mood, kekenyangan, atau kesehatan mencapai 0");
        System.out.println("2. Setiap aksi aktif memiliki efek samping terhadap mood, kekenyangan, dan kesehatan");
        System.out.println("3. Sim harus tidur minimal 3 menit setiap harinya, jika tidak dilakukan akan mengakibatkan efek samping pada kesehatan dan mood");
        System.out.println("4. Sim harus buang air setidaknya 1 kali setiap habis makan, jika tidak dilakukan dalam 4 menit dari selesai makan akan mengakibatkan efek samping pada kesehatan dan mood");
        System.out.println("5. Sim dapat bekerja maksimal 4 menit setiap harinya dan baru bisa berganti pekerjaan setelah bekerja 12 menit pada pekerjaan saat ini serta membayar ½ dari gaji pekerjaan baru");

    }

    public void exit() {
        setGameStarted(false);
        System.out.println("Terima kasih telah bermain Sim-Plicity!");
    }

    public void viewSimInfo() {
        System.out.println("Info Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Job: " + currentSim.getJob().getName());
        System.out.println("Money: " + currentSim.getMoney());
        System.out.println("Fullness: " + currentSim.getFullness());
        System.out.println("Mood: " + currentSim.getMood());
        System.out.println("Health: " + currentSim.getHealth());
        System.out.println("Current Location: " + currentSim.getCurrentRoom().getNameRoom());
    }

    public void addSim(Sim sim) {
        simList.add(sim);
    }

    public void changeSim(String name) {
        for (int i = 0; i < simList.size(); i++) {
            if (simList.get(i).getName().equals(name)) {
                currentSim = simList.get(i);
            }
        }
    }

    public void viewSimList() {
        System.out.println("Berikut adalah daftar SIMS yang kamu miliki:");
        for (int i = 0; i < simList.size(); i++) {
            System.out.println(simList.get(i).getName());
        }
    }

    public void viewSimStatus() {
        System.out.println("Status Sim:");
        System.out.println("Nama: " + currentSim.getName());
        System.out.println("Status: " + currentSim.getStatus());
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(Boolean start) {
        gameStarted = start;
    }

    public Sim getCurrentSim() {
        return currentSim;
    }

    public void setCurrentSim(Sim sim) {
        currentSim = sim;
    }

    public ArrayList<Sim> getSimList() {
        return simList;
    }

}
