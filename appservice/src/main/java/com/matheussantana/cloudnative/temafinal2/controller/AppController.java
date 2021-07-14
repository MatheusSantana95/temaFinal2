package com.matheussantana.cloudnative.temafinal2.controller;

import com.matheussantana.cloudnative.temafinal2.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appService")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/searchPlaylist/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity findPlaylistById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(appService.findPlaylistById(id));
    }

    @GetMapping(value = "/searchSong/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public @ResponseBody
    ResponseEntity findSongById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(appService.findSongById(id));
    }
}
