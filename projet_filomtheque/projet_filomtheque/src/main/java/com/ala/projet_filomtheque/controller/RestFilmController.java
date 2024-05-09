package com.ala.projet_filomtheque.controller;

import com.ala.projet_filomtheque.entities.Film;
import com.ala.projet_filomtheque.service.IServiceFilm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return iServiceFilm.findFilmById(id) ;
    }
}
