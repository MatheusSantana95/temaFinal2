package com.matheussantana.cloudnative.temafinal2.feign;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import feign.Param;
import feign.RequestLine;

public interface SongFeign {

    @RequestLine(value = "GET /searchSong/{id}")
    public Song findSongById(@Param(value = "id") int id);
}
