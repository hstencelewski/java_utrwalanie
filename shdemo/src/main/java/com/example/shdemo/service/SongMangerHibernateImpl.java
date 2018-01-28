package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Serial;
import com.example.shdemo.domain.Genre;
import com.example.shdemo.domain.Song;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class SongMangerHibernateImpl implements SongManager {

  @Autowired
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  
  @Override
  public void addGenre(Genre genre) {
    sessionFactory.getCurrentSession().persist(genre);
  }

  @Override
  public void deleteGenre(Genre genre) {
    sessionFactory.getCurrentSession().delete(genre);
  }



  @Override
  @SuppressWarnings("unchecked")
  public List<Genre> getAllGenres() {
    return sessionFactory.getCurrentSession().getNamedQuery("genre.all").list();
  }

  @Override
  public void addSong(Song song) {
    sessionFactory.getCurrentSession().persist(song);

  }

  @Override
  public void deleteSong(Song song) {
    sessionFactory.getCurrentSession().delete(song);
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<Song> getAllSongs() {
    return sessionFactory.getCurrentSession().getNamedQuery("song.all").list();
  }
  
  @Override
  public void updateSong(Song oldsong, Song newsong) {
      oldsong = (Song) sessionFactory.getCurrentSession().get(Song.class, oldsong.getId());

      oldsong.setSongname(newsong.getSongname());
      oldsong.setBandname(newsong.getBandname());
      oldsong.setCost(newsong.getCost());
      oldsong.setYor(newsong.getYor());

      sessionFactory.getCurrentSession().update(oldsong);
  }

  @Override
  public void addSerial(Serial serial) {
    sessionFactory.getCurrentSession().persist(serial);
  }

  @Override
  public void deleteSerial(Serial serial) {
    sessionFactory.getCurrentSession().delete(serial);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Serial> getAllSerials() {
    return sessionFactory.getCurrentSession().getNamedQuery("serial.all").list();
  }
}