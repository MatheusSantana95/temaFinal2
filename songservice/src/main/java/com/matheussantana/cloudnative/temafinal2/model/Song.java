package com.matheussantana.cloudnative.temafinal2.model;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSong;
    private String name;

    public Song () {}

    public Song(int idSong, String name) {
        this.idSong = idSong;
        this.name = name;
    }

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
