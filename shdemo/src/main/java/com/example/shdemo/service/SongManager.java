package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Serial;
import com.example.shdemo.domain.Genre;
import com.example.shdemo.domain.Song;


public interface SongManager {
	
	void addSong(Song song);
	void deleteSong(Song song);
	List<Song> getAllSongs();
	void updateSong(Song oldsong, Song newsong);
	
	void addSerial(Serial serial);
	void deleteSerial(Serial serial);
	List<Serial> getAllSerials();
	
	void addGenre(Genre genre);
	void deleteGenre(Genre genre);
	List<Genre> getAllGenres();

}
