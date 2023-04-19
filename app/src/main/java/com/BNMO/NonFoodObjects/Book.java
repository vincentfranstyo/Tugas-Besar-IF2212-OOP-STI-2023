package com.BNMO.NonFoodObjects;

public class Book extends NonFoodObjects {
    private String name;
    private int length;
    private int width;
    private int price;
    private boolean isBeingUsed;
    private int numOfPages;
    private String author;

    public Book(String name, int length, int width, int price, boolean isBeingUsed, int numOfPages, String author) {
        super(name, length, width, price);
        this.isBeingUsed = isBeingUsed;
        this.numOfPages = numOfPages;
        this.author = author;
    }

    public boolean getIsBeingUsed() {
        return isBeingUsed;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setStatus(boolean isBeingUsed) {
        this.isBeingUsed = isBeingUsed;
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
}