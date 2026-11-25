package com.ala.projet_filomtheque.controller;

import com.ala.projet_filomtheque.entities.Film;
import com.ala.projet_filomtheque.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RestFilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmRepository filmRepository;

    @BeforeEach
    void cleanDatabase() {
        filmRepository.deleteAll();
    }

    private Film saveFilm(String titre, int annee) {
        Film film = new Film();
        film.setTitre(titre);
        film.setDescription("Description of " + titre);
        film.setAnneeparution(annee);
        return filmRepository.save(film);
    }

    @Test
    void listsAllFilms() throws Exception {
        saveFilm("Inception", 2010);
        saveFilm("Interstellar", 2014);

        mockMvc.perform(get("/api/films"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void returnsOneFilmById() throws Exception {
        Film film = saveFilm("Inception", 2010);

        mockMvc.perform(get("/api/films/{id}", film.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre", is("Inception")))
                .andExpect(jsonPath("$.anneeparution", is(2010)));
    }

    @Test
    void unknownIdReturns404() throws Exception {
        mockMvc.perform(get("/api/films/{id}", 9999))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Film not found"));
    }

    @Test
    void createsAFilm() throws Exception {
        String body = """
                {"titre": "Dune", "description": "Sci-fi", "anneeparution": 2021}
                """;

        mockMvc.perform(post("/api/films/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titre", is("Dune")));

        assertThat(filmRepository.findAll()).hasSize(1);
    }

    @Test
    void updatesAnExistingFilm() throws Exception {
        Film film = saveFilm("Dune", 2021);
        String body = """
                {"id": %d, "titre": "Dune: Part One", "description": "Sci-fi", "anneeparution": 2021}
                """.formatted(film.getId());

        mockMvc.perform(put("/api/films/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre", is("Dune: Part One")));
    }

    @Test
    void updatingAnUnknownFilmReturns404() throws Exception {
        String body = """
                {"id": 9999, "titre": "Ghost", "anneeparution": 2000}
                """;

        mockMvc.perform(put("/api/films/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletesAFilm() throws Exception {
        Film film = saveFilm("Tenet", 2020);

        mockMvc.perform(delete("/api/films/{id}", film.getId()))
                .andExpect(status().isOk());

        assertThat(filmRepository.existsById(film.getId())).isFalse();
    }

    @Test
    void deletingAnUnknownFilmReturns404() throws Exception {
        mockMvc.perform(delete("/api/films/{id}", 9999))
                .andExpect(status().isNotFound());
    }

    @Test
    void repositoryOrdersFilmsByTitle() {
        saveFilm("Zodiac", 2007);
        saveFilm("Amelie", 2001);
        saveFilm("Memento", 2000);

        assertThat(filmRepository.findAllFilmOrdred())
                .extracting(Film::getTitre)
                .containsExactly("Amelie", "Memento", "Zodiac");
    }

    @Test
    void repositorySearchesByTitle() {
        saveFilm("The Dark Knight", 2008);
        saveFilm("Dark City", 1998);
        saveFilm("Up", 2009);

        assertThat(filmRepository.findByTitreContaining("Dark")).hasSize(2);
    }
}
