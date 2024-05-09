package com.ala.projet_filomtheque.service;

import com.ala.projet_filomtheque.entities.Film;

import java.util.List;

public interface IServiceFilm {
    public Film createFilm(Film f) ;
    public Film findFilmById(int id);
    public List<Film> findAllFilms();
    public Film updateFilm(Film f) ;
    public void deleteFilm(int id) ;
    public List<Film> findByTitreContaining(String titre);
    public  List<Film> findAllFilmOrdred() ;
}
