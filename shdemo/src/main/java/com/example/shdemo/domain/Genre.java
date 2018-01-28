package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "genre.all", query = "Select g from Genre g")
})

public class Genre {

    private Long id;
    private String genre = "";

    public Genre() {
    }

    public Genre(String genre) {
        super();
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
