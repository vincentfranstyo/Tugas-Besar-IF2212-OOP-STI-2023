package com.BNMO.Object.NonFoodObjects.Book;

import com.BNMO.Object.NonFoodObjects.NonFoodObjects;
import com.BNMO.SIMS.Sim;
import com.BNMO.Utilities.*;

public class Book extends NonFoodObjects {
    private int numOfPages;
    private String author;

    public Book(String title, int price, int numOfPages, String author, Point position) {
        super(title, 1, 1, price, position);
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

    public void read(Time time, Sim sim) {
        // TODO read a book
        int duration = time.convertToSecond();
        System.out.println(sim.getName() + " is reading " + this.getName() + ".");
    }
}