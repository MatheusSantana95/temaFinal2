package com.matheussantana.cloudnative.temafinal2.repository;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {
}
