package com.BNMO.Object.NonFoodObjects.Book;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.Time;

public class Book extends NonFoodObjects {
    private int numOfPages;
    private String author;

    public Book(String title, int price, int numOfPages, String author) {
        super(title, 1, 1, price);
        this.numOfPages = numOfPages;
        this.author = author;
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

    public void use(Time time, Sim sim) {
        // read a book
        int duration = time.convertToSecond();
        System.out.println(sim.getName() + " is reading " + this.getName() + ".");
    }
}