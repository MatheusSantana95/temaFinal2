package com.matheussantana.cloudnative.temafinal2.service;

import com.matheussantana.cloudnative.temafinal2.hystrix.AddSongHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.hystrix.GetAllSongsHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.hystrix.GetSongByIdHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Optional<Song> addSong(Song song) {
        return new AddSongHystrixCommand(song, songRepository).observe().toBlocking().first();
    }

    public Optional<Song> getSongById(int songId) {
        return new GetSongByIdHystrixCommand(songId, songRepository).observe().toBlocking().first();
    }

    public Iterable<Song> getAllSongs() {
        return new GetAllSongsHystrixCommand(songRepository).observe().toBlocking().first();
    }
}
