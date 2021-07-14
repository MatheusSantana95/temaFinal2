package com.matheussantana.cloudnative.temafinal2.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private List<Song> songIds = new ArrayList<>();

    public Playlist(int id, List<Song> songIds) {
        this.id = id;
        this.songIds = songIds;
    }

    public Playlist() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Song> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Song> songsId) {
        this.songIds = songsId;
    }
}

