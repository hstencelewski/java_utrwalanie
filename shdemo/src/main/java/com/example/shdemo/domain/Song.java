package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "song.all", query = "Select s from Song s")
})
public class Song {

    private Long id;

    private String songname;
    private String bandname;
    private double cost;
    private int yor;

    private Serial serial;
    private Genre genre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getYor() {
        return yor;
    }

    public void setYor(int yor) {
        this.yor = yor;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}