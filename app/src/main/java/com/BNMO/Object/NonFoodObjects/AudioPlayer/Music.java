package com.BNMO.Object.NonFoodObjects.AudioPlayer;

import com.BNMO.Utilities.Time;

public class Music {
    private String name;
    private String artist;
    private int musicID;
    private String genre;
    private Time length;
    private int price;

    public Music(String name, String artist, int musicID, String genre, Time length, int price) {
        this.name = name;
        this.artist = artist;
        this.musicID = musicID;
        this.genre = genre;
        this.length = length;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getMusicID() {
        return musicID;
    }

    public String getGenre() {
        return genre;
    }

    public Time getLength() {
        return length;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setMusicID(int musicID) {
        this.musicID = musicID;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}