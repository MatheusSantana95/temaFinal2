package com.matheussantana.cloudnative.temafinal2.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "playlist", orphanRemoval = true, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<PlaylistSong> songIds = new ArrayList<>();

    public Playlist() {}

    public Playlist(int id, List<PlaylistSong> songIds) {
        this.id = id;
        this.songIds = songIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PlaylistSong> getSongsId() {
        return songIds;
    }

    public void setSongsId(List<PlaylistSong> songsId) {
        this.songIds = songsId;
    }
}
