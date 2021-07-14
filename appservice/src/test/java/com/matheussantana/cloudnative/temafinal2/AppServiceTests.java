package com.matheussantana.cloudnative.temafinal2;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.service.AppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppServiceTests {

	@Spy
	private AppService appService;

	@Test
	public void shouldFindPlaylistById() {
		List<Song> songs = new ArrayList<>();
		Song songTest1 = new Song(1, "Clocks");
		Song songTest2 = new Song(2, "Paradise");
		songs.add(songTest1);
		songs.add(songTest2);
		Playlist playlistTest1 = new Playlist(1, songs);
		Mockito.doReturn(playlistTest1).when(appService).findPlaylistById(playlistTest1.getId());
		Playlist playlistTest = appService.findPlaylistById(1);
		assertEquals(playlistTest1.getId(), playlistTest.getId());
	}

	@Test
	public void shouldFindSongById() {
		Song songTest1 = new Song(1, "Clocks");
		Mockito.doReturn(songTest1).when(appService).findSongById(songTest1.getIdSong());
		Song songFound = appService.findSongById(1);
		assertEquals(songTest1.getIdSong(), songFound.getIdSong());
	}
}