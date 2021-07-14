package com.matheussantana.cloudnative.temafinal2.service;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.ribbon.PlaylistRibbon;
import com.matheussantana.cloudnative.temafinal2.ribbon.SongRibbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppService {

    @Autowired
    private PlaylistRibbon playlistRibbon;

    @Autowired
    private SongRibbon songRibbon;

    public Playlist findPlaylistById(int id) {
        Playlist playlist = playlistRibbon.findPlaylistById(id).toBlocking().first();
        List<Song> songsList = playlist.getSongIds().stream().map(song -> {
            Song findSongById = songRibbon.findSongById(song.getIdSong()).toBlocking().first();
            return findSongById;
        }).collect(Collectors.toList());
        playlist.setSongIds(songsList);
        return playlist;
    }

    public Song findSongById(int id) {
        return songRibbon.findSongById(id).toBlocking().first();
    }
}
