package com.BNMO.NonFoodObjects.Book;

import com.BNMO.NonFoodObjects.NonFoodObjects;

public class Book extends NonFoodObjects {
    private String name;
    private int length;
    private int width;
    private int price;
    private int numOfPages;
    private String author;

    public Book(String name, int length, int width, int price, int numOfPages, String author) {
        super(name, length, width, price);
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
}