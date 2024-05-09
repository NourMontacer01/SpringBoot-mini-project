package com.ala.projet_filomtheque.repository;

import com.ala.projet_filomtheque.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Integer> {
    List<Film> findByTitreContaining(String titre);
    @Query("SELECT f FROM Film f ORDER BY f.titre")
    List<Film> findAllFilmOrdred() ;

    List<Film> findByCategorieId(int id) ;
}
