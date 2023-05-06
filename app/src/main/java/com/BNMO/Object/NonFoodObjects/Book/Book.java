package com.BNMO.Object.NonFoodObjects.Book;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Book extends NonFoodObjects {
    private int numOfPages;
    private String author;

    public Book(String title) {
        super(title, 1, 1, 100);
        this.setType("Book");
        this.numOfPages = 400;
        this.author = "Rehan";
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void printBookInfo() {
        System.out.println("Title: " + getName());
        System.out.println("Price: " + getPrice());
        System.out.println("Number of pages: " + getNumOfPages());
        System.out.println("Author: " + getAuthor());
    }

    public void read(Time time, Sim sim, Book book) {
        // TODO read a book
        try {
            if (!getIsOccupied()) {
                setIsOccupied(true);
                if (sim.getStatus().equals("Nothing")) {
                    sim.setStatus("Reading");
                    System.out.println(sim.getName() + " is reading " + this.getName() + ".");
                    System.out.println();
                    printBookInfo();
                    System.out.println();
                    int duration = time.convertToSecond();
                    Thread.sleep(duration * 1000L);
                    sim.setMood(sim.getMood() + (10 * (duration / 240)));
                    sim.setFullness(sim.getFullness() - (10 * (duration / 240)));
                    System.out.println(sim.getName() + " has finished reading " + this.getName() + ".");
                } else {
                    System.out.println("You can't read the book while you are " + sim.getStatus());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setIsOccupied(false);
            sim.setStatus("Nothing");
        }
    }
}