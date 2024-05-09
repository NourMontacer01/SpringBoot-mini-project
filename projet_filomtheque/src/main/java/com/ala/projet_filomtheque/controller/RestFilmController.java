package com.ala.projet_filomtheque.controller;

import com.ala.projet_filomtheque.entities.Film;
import com.ala.projet_filomtheque.exception.FilmNotFoundException;
import com.ala.projet_filomtheque.service.IServiceFilm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class RestFilmController {
    private final IServiceFilm iServiceFilm ;
    @GetMapping("")
    public List<Film> getAll(){
        return iServiceFilm.findAllFilms();
    }
    @GetMapping("/{id}")
    public Film getParId(@PathVariable int id){
        if(!iServiceFilm.filmExist(id)) throw new FilmNotFoundException();
        return iServiceFilm.findFilmById(id) ;
    }
    @PostMapping("/add")
    public ResponseEntity<Film> AddFilm(@RequestBody Film film){
        return new ResponseEntity<>(iServiceFilm.createFilm(film) , HttpStatus.CREATED)  ;
    }
    @DeleteMapping("{id}")
    public void deleteFilm(@PathVariable int id){
        if(!iServiceFilm.filmExist(id)) throw new FilmNotFoundException();
        iServiceFilm.deleteFilm(id);
    }
    @PutMapping("update")
    public Film updateFilm(@RequestBody Film film){
        if(!iServiceFilm.filmExist(film.getId())) throw new FilmNotFoundException();
        return iServiceFilm.updateFilm(film) ;
    }
}
