package com.example.JDBCSongs.domain;

public class Song {
	
	private long id;
	
	private String songname;
	private String bandname;
	private double cost;
	private String yor;
	
	public Song() {
	}
	
	public Song(String songname, String bandname, double cost, String yor) {
		super();
		this.songname = songname;
		this.bandname = bandname;
		this.cost = cost;
		this.yor = yor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getBandname() {
		return bandname;
	}
	public void setBandname(String bandname) {
		this.bandname = bandname;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getYor() {
		return yor;
	}
	public void setYor(String yor) {
		this.yor = yor;
	}
}
