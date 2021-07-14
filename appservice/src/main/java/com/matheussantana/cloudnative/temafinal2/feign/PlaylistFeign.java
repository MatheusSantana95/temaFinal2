package com.matheussantana.cloudnative.temafinal2.feign;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import feign.Param;
import feign.RequestLine;

public interface PlaylistFeign {

    @RequestLine(value = "GET /searchPlaylist/{id}")
    public Playlist findPlaylistById(@Param(value = "id") int id);
}
