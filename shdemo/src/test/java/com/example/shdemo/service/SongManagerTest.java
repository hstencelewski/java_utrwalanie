package com.example.shdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import com.example.shdemo.domain.Song;
import com.example.shdemo.domain.Serial;
import com.example.shdemo.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class SongManagerTest {

    @Autowired
    SongManager songManager;
    
    private final String SONGNAME_1 = "Sad but True";
    private final String BANDNAME_1 = "Metallica";
    private final double COST_1 = 15.99;
    private final int YOR_1 = 1991;
    
    private final String SONGNAME_2 = "Enter Sandman";
    private final String BANDNAME_2 = "Metallica";
    private final double COST_2 = 2.99;
    private final int YOR_2 = 1991;
    
    private final String SONGNAME_3 = "Nothing else Matters";
    private final String BANDNAME_3 = "Metallica";
    private final double COST_3 = 30.99;
    private final int YOR_3 = 1991;
    
    Serial serial1 = new Serial("qwert");
    Serial serial2 = new Serial("asdfg");
    Serial serial3 = new Serial("zxcvb");
    
    Genre genre1 = new Genre("Heavy Metal");
    Genre genre2 = new Genre("Metal");
    Genre genre3 = new Genre("Thrash Metal");
    
    @Test
    public void addSongCheck() {
        Song song = new Song();

        song.setSongname(SONGNAME_1);
        song.setBandname(BANDNAME_1);
        song.setCost(COST_1);
        song.setYor(YOR_1);

        song.setSerial(serial1);
        song.setGenre(genre1);

        List<Song> before = songManager.getAllSongs();
        songManager.addSong(song);
        List<Song> after = songManager.getAllSongs();
        assertEquals(before.size() + 1, after.size());
    }

    @Test
    public void deleteSongCheck(){
    	Song song = new Song();

        song.setSongname(SONGNAME_2);
        song.setBandname(BANDNAME_2);
        song.setCost(COST_2);
        song.setYor(YOR_2);

        song.setSerial(serial2);
        song.setGenre(genre2);

        songManager.addSong(song);
        List<Song> before = songManager.getAllSongs();
        songManager.deleteSong(song);
        List<Song> after = songManager.getAllSongs();
        assertEquals(before.size(), after.size()+1);
    }

    @Test
    public void addSerialCheck() {
        Serial serial = new Serial("werty");

        List<Serial> before = songManager.getAllSerials();

        songManager.addSerial(serial);

        List<Serial> after = songManager.getAllSerials();

        assertEquals(before.size() + 1, after.size());
    }

    @Test
    public void deleteSerialCheck() {
    	Serial serial = new Serial("sdfgh");

    	songManager.addSerial(serial);

        List<Serial> before = songManager.getAllSerials();

        songManager.deleteSerial(serial);

        List<Serial> after = songManager.getAllSerials();

        assertEquals(before.size(), after.size() + 1);
    }
    @Test
    public void addGenreCheck() {
        Genre genre = new Genre("Pop");

        List<Genre> before = songManager.getAllGenres();

        songManager.addGenre(genre);

        List<Genre> after = songManager.getAllGenres();

        assertEquals(before.size() + 1, after.size());
    }


    @Test
    public void deleteGenreCheck() {
    	Genre genre = new Genre("Rock");

    	songManager.addGenre(genre);

    	List<Genre> before = songManager.getAllGenres();

        songManager.deleteGenre(genre);

        List<Genre> after = songManager.getAllGenres();

        assertEquals(before.size(), after.size() + 1);
    }



    
}
