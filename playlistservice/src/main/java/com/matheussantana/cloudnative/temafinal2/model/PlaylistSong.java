package com.matheussantana.cloudnative.temafinal2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "playlist_song")
public class PlaylistSong {

    @Id
    private int id;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "playlist_id"))
    private Playlist playlist;

    public PlaylistSong(int id, Playlist playlist) {
        this.id = id;
        this.playlist = playlist;
    }

    public PlaylistSong() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
