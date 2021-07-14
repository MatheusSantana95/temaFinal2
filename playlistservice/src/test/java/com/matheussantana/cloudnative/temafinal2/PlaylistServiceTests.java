package com.matheussantana.cloudnative.temafinal2;

import com.matheussantana.cloudnative.temafinal2.model.Playlist;
import com.matheussantana.cloudnative.temafinal2.model.PlaylistSong;
import com.matheussantana.cloudnative.temafinal2.service.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = com.matheussantana.cloudnative.temafinal2.PlaylistServiceApplication.class)
class PlaylistServiceTests {

	@Spy
	private PlaylistService playlistService;

	@Test
	public void shouldAddPlaylist() {
		List<PlaylistSong> playlistSongs = new ArrayList<>();
		Playlist playlistTest1 = new Playlist(1,null);
		PlaylistSong playlistSong1 = new PlaylistSong(1, playlistTest1);
		PlaylistSong playlistSong2 = new PlaylistSong(2, playlistTest1);
		playlistSongs.add(playlistSong1);
		playlistSongs.add(playlistSong2);
		playlistTest1.setSongsId(playlistSongs);
		Mockito.doReturn(Optional.of(playlistTest1)).when(playlistService).addPlaylist(playlistTest1);
		Optional<Playlist> playlistAddedTest = playlistService.addPlaylist(playlistTest1);
		Assertions.assertEquals(playlistTest1, playlistAddedTest.get());
	}

	@Test
	public void shouldGetPlaylistById() {
		List<PlaylistSong> playlistSongs = new ArrayList<>();
		Playlist playlistTest1 = new Playlist(1,null);
		PlaylistSong playlistSong1 = new PlaylistSong(1, playlistTest1);
		PlaylistSong playlistSong2 = new PlaylistSong(2, playlistTest1);
		playlistSongs.add(playlistSong1);
		playlistSongs.add(playlistSong2);
		playlistTest1.setSongsId(playlistSongs);
		Mockito.doReturn(Optional.of(playlistTest1)).when(playlistService).getPlaylistById(playlistTest1.getId());
		Optional<Playlist> playlistTest = playlistService.getPlaylistById(1);
		Assertions.assertEquals(playlistTest1.getId(), playlistTest.get().getId());
	}

	@Test
	public void shouldGetAllPlaylists() {
		List<PlaylistSong> playlistSongs = new ArrayList<>();
		Playlist playlistTest1 = new Playlist(1,null);
		PlaylistSong playlistSong1 = new PlaylistSong(1, playlistTest1);
		PlaylistSong playlistSong2 = new PlaylistSong(2, playlistTest1);
		playlistSongs.add(playlistSong1);
		playlistSongs.add(playlistSong2);
		playlistTest1.setSongsId(playlistSongs);
		List<PlaylistSong> playlistSongs2 = new ArrayList<>();
		Playlist playlistTest2 = new Playlist(2,null);
		PlaylistSong playlistSong3 = new PlaylistSong(3, playlistTest1);
		PlaylistSong playlistSong4 = new PlaylistSong(4, playlistTest1);
		playlistSongs2.add(playlistSong3);
		playlistSongs2.add(playlistSong4);
		playlistTest2.setSongsId(playlistSongs2);
		List<Playlist> playlistsTests = new ArrayList<>();
		Iterable<Playlist> playlistIterable = playlistsTests;
		Mockito.doReturn(playlistIterable).when(playlistService).getAllPlaylists();
		Iterable<Playlist> playlistIterableTest = playlistService.getAllPlaylists();
		Assertions.assertEquals(playlistIterable, playlistIterableTest);
	}
}
