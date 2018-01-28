package com.example.JDBCSongs.service;

import com.example.JDBCSongs.domain.Song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongManagerJDBC implements SongManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableSong = "CREATE TABLE Song(id bigint GENERATED BY DEFAULT AS IDENTITY, songname varchar(50) unique, bandname varchar(50), cost double, yor varchar(4))";

	private PreparedStatement addSongStmt;
	private PreparedStatement deleteAllSongsStmt;
	private PreparedStatement getAllSongsStmt;
	private PreparedStatement removeSongStmt;

	private Statement statement;

	public SongManagerJDBC() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Song".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableSong);

			addSongStmt = connection
					.prepareStatement("INSERT INTO Song (songname, bandname, cost, yor) VALUES (?, ?, ?, ?)");
			deleteAllSongsStmt = connection
					.prepareStatement("DELETE FROM Song");
			getAllSongsStmt = connection
					.prepareStatement("SELECT id, songname, bandname, cost, yor FROM Song");
			removeSongStmt = connection
					.prepareStatement("DELETE FROM Song WHERE songname=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void clearSongs() {
		try {
			deleteAllSongsStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addSong(Song Song) {
		int count = 0;
		try {
			addSongStmt.setString(1, Song.getSongname());
			addSongStmt.setString(2, Song.getBandname());
			addSongStmt.setDouble(3, Song.getCost());
			addSongStmt.setString(4, Song.getYor());

			count = addSongStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void removeSong(String songname) {
		try {
			removeSongStmt.setString(1, songname);
			removeSongStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeSelectedSongs(List<Song> list) {
		try {
			connection.setAutoCommit(false);
			for (Song Song : list) {
				removeSongStmt.setString(1, Song.getSongname());
				removeSongStmt.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void addAllSongs(List<Song> list) {
		try {
			connection.setAutoCommit(false);
			for (Song Song : list) {
				addSongStmt.setString(1, Song.getSongname());
				addSongStmt.setString(2, Song.getBandname());
				addSongStmt.setDouble(3, Song.getCost());
				addSongStmt.setString(4, Song.getYor());
				addSongStmt.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public List<Song> getAllSongs() {
		List<Song> Songs = new ArrayList<Song>();

		try {
			ResultSet rs = getAllSongsStmt.executeQuery();

			while (rs.next()) {
				Song s = new Song();
				s.setId(rs.getInt("id"));
				s.setSongname(rs.getString("songname"));
				s.setBandname(rs.getString("bandname"));
				s.setCost(rs.getDouble("cost"));
				s.setYor(rs.getString("yor"));
				Songs.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Songs;
	}

}