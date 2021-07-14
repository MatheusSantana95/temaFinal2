package com.matheussantana.cloudnative.temafinal2.controller;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping(value = "/addSong", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity addSong(@RequestBody Song song) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(song));
    }

    @GetMapping(value = "/searchSong/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity getSongById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(songService.getSongById(id));
    }

    @GetMapping(value = "/AllSongs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity getAllSongs() {
        return ResponseEntity.status(HttpStatus.OK).body(songService.getAllSongs());
    }
}
