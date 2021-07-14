package com.matheussantana.cloudnative.temafinal2;

import com.matheussantana.cloudnative.temafinal2.model.Song;
import com.matheussantana.cloudnative.temafinal2.service.SongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = com.matheussantana.cloudnative.temafinal2.SongServiceApplication.class)
class SongServiceTests {

	@Spy
	private SongService songService;

	@Test
	public void shouldAddSong() {
		Song songTest1 = new Song(1, "Clocks");
		Mockito.doReturn(Optional.of(songTest1)).when(songService).addSong(songTest1);
		Optional<Song> addedSong1 = songService.addSong(songTest1);
		Assertions.assertEquals(songTest1, addedSong1.get());
	}

	@Test
	public void shouldGetSongById() {
		Song songTest2 = new Song(2, "Paradise");
		songService.addSong(songTest2);
		Mockito.doReturn(Optional.of(songTest2)).when(songService).getSongById(songTest2.getId());
		Optional<Song> addedSong2 = songService.getSongById(2);
		Assertions.assertEquals(songTest2.getId(), addedSong2.get().getId());
		Assertions.assertEquals(songTest2, addedSong2.get());
	}

	@Test
	public void shouldGetAllSongs() {
		Song songTest1 = new Song(1, "Clocks");
		Song songTest2 = new Song(2, "Paradise");
		songService.addSong(songTest1);
		songService.addSong(songTest2);
		List<Song> addedSongsListTest = new ArrayList<>();
		addedSongsListTest.add(songTest1);
		addedSongsListTest.add(songTest2);
		Iterable<Song> iterableSongs = addedSongsListTest;
		Mockito.doReturn(iterableSongs).when(songService).getAllSongs();
		Iterable<Song> iterableAddedSongs = songService.getAllSongs();
		Assertions.assertEquals(iterableSongs, iterableAddedSongs);
	}
}
