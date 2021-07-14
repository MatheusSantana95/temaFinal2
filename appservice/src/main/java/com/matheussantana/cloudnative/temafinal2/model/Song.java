package com.matheussantana.cloudnative.temafinal2.model;

public class Song {

    private int idSong;
    private String name;

    public Song(int idSong, String name) {
        this.idSong = idSong;
        this.name = name;
    }

    public Song() {}

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

