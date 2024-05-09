package com.ala.projet_filomtheque.service;

import com.ala.projet_filomtheque.entities.Film;
import com.ala.projet_filomtheque.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceFilm implements IServiceFilm{
    @Autowired
    FilmRepository filmRepository ;
    @Override
    public Film createFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public Film findFilmById(int id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film updateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public void deleteFilm(int id) {
        filmRepository.deleteById(id);
    }

    @Override
    public List<Film> findByTitreContaining(String titre) {
        return filmRepository.findByTitreContaining(titre);
    }

    @Override
    public List<Film> findAllFilmOrdred() {
        return filmRepository.findAllFilmOrdred();
    }
}
