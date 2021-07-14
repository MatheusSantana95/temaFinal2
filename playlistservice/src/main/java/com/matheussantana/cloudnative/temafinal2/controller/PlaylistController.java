package com.matheussantana.cloudnative.temafinal2.controller;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping(value = "/add-playlist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity addSong(@RequestBody Playlist playlist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.addPlaylist(playlist));
    }

    @GetMapping(value = "/searchPlaylist/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity getSongById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getPlaylistById(id));
    }

    @GetMapping(value = "/AllPlaylists", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity getAllSongs() {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAllPlaylists());
    }
}
