package com.example.JDBCSongs.service;
import com.example.JDBCSongs.domain.Song;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SongManagerTest {


    SongManagerJDBC SongManager = new SongManagerJDBC();

    private final static String[] SONGNAME = {"Enter Sandman", "Nothing Else Matters", "Cowboys From Hell", "Enemy Inside", "Gone Away"};
    private final static String[] BANDNAME = {"Metallica", "Metallica", "Pantera", "Dream Theater", "Offspring"};
    private final static double[] COST = {12.99, 7.20, 18.75, 3.45, 17.20};
    private final static String[] YOR = {"1991", "1991", "1990", "2013", "1997"};

    @Test
    public void checkConnection() {
        assertNotNull(SongManager.getConnection());
    }

    @Test
    public void checkAdding() {
        if (SONGNAME.length == BANDNAME.length && BANDNAME.length == COST.length && BANDNAME.length == YOR.length) {
            int i = 0;
            Song[] Song = new Song[SONGNAME.length];
            SongManager.clearSongs();

            for (i = 0; i < SONGNAME.length - 3; i++) {
                Song[i] = new Song(SONGNAME[i], BANDNAME[i], COST[i], YOR[i]);
                assertEquals(1, SongManager.addSong(Song[i]));
            }

            List<Song> Songs = SongManager.getAllSongs();
            Song[] SongRetrieved = new Song[SONGNAME.length];

            for (i = 0; i < SONGNAME.length - 3; i++) {
                SongRetrieved[i] = Songs.get(i);

                assertEquals(SONGNAME[i], SongRetrieved[i].getSongname());
                assertEquals(BANDNAME[i], SongRetrieved[i].getBandname());
                assertEquals((int) COST[i], (int) SongRetrieved[i].getCost());
                assertEquals(YOR[i], SongRetrieved[i].getYor());
            }
        }
    }

    @Test
    public void checkAddingList() {
        SongManager.clearSongs();
        List<Song> listToAdd = new ArrayList<>();

        Song Song1 = new Song(SONGNAME[1], BANDNAME[2], COST[2], YOR[2]);
        Song Song2 = new Song(SONGNAME[3], BANDNAME[3], COST[3], YOR[3]);
        Song Song3 = new Song(SONGNAME[4], BANDNAME[4], COST[4], YOR[4]);

        listToAdd.add(Song1);
        listToAdd.add(Song2);
        listToAdd.add(Song3);

        SongManager.addAllSongs(listToAdd);
        List<Song> Songs = SongManager.getAllSongs();

        assertEquals(3, Songs.size());
        SongManager.clearSongs();
        listToAdd.clear();

        Song1.setSongname(SONGNAME[2]);
        listToAdd.add(Song1);
        listToAdd.add(Song2);
        listToAdd.add(Song3);

        SongManager.addAllSongs(listToAdd);
        Songs = SongManager.getAllSongs();

        assertEquals(3, Songs.size());

    }

    @Test
    public void checkDeleting() {
        SongManager.clearSongs();
        Song Song1 = new Song("test", "test", 29, "2017");
        SongManager.addSong(Song1);
        SongManager.removeSong("test");
        List<Song> Songs = SongManager.getAllSongs();
        assertEquals(0, Songs.size());
    }

    @Test
    public void checkDeletingSelected() {
    	Song Song1 = new Song(SONGNAME[2], BANDNAME[2], COST[2], YOR[2]);
    	Song Song2 = new Song(SONGNAME[3], BANDNAME[3], COST[3], YOR[3]);
    	Song Song3 = new Song(SONGNAME[4], BANDNAME[4], COST[4], YOR[4]);
        
    	SongManager.clearSongs();
        List<Song> SongList = new ArrayList<>();
        SongList.add(Song1);
        SongList.add(Song2);
        SongList.add(Song3);
        SongManager.addAllSongs(SongList);
        SongList.remove(Song1);
        SongList.remove(Song3);
        SongManager.removeSelectedSongs(SongList);
        List<Song> Songs = SongManager.getAllSongs();

        assertEquals(2, Songs.size());
        SongManager.removeSelectedSongs(SongList);
        Songs = SongManager.getAllSongs();

        assertEquals(2, Songs.size());
    }
}
