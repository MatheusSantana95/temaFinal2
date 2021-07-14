package com.matheussantana.cloudnative.temafinal2.service;

import com.matheussantana.cloudnative.temafinal2.hystrix.AddPlaylistHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.hystrix.GetAllPlaylistsHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.hystrix.GetPlaylistByIdHystrixCommand;
import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public Optional<Playlist> addPlaylist(Playlist playlist) {
        return new AddPlaylistHystrixCommand(playlist, playlistRepository).observe().toBlocking().first();
    }

    public Optional<Playlist> getPlaylistById(int playlistId) {
        return new GetPlaylistByIdHystrixCommand(playlistId, playlistRepository).observe().toBlocking().first();
    }

    public Iterable<Playlist> getAllPlaylists() {
        return new GetAllPlaylistsHystrixCommand(playlistRepository).observe().toBlocking().first();
    }
}
