package com.matheussantana.cloudnative.temafinal2.repository;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer> {
}
