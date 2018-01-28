package com.example.JDBCSongs.service;

import java.util.List;

import com.example.JDBCSongs.domain.Song;

public interface SongManager {

    public int addSong(Song song);

    public List<Song> getAllSongs();

    public void addAllSongs(List<Song> list);

    public void removeSong(String songname);

    public void removeSelectedSongs(List<Song> list);

}
