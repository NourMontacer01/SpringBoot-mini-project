package com.ala.projet_filomtheque.controller;


import com.ala.projet_filomtheque.entities.Film;
import com.ala.projet_filomtheque.service.IServiceCategorie;
import com.ala.projet_filomtheque.service.IServiceFilm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/film/")
@RequiredArgsConstructor
public class FilmController {

    private final IServiceFilm iServiceFilm ;

    private final IServiceCategorie iServiceCategorie ;
    @GetMapping("all")
    public String listeFilms(Model model){
        model.addAttribute("films",iServiceFilm.findAllFilmOrdred() );
        model.addAttribute("categories" ,iServiceCategorie.GetAllCategories()) ;
        return "affiche";
    }
    @PostMapping("getByTitle")
    public String findByTitle(String titre, Model model){
        model.addAttribute("films", iServiceFilm.findByTitreContaining(titre));
        return "affiche";
    }
    @PostMapping("getByCategorie")
    public String getByCategorie(@RequestParam int id_categorie , Model model){
        if (id_categorie == 0 ){
            model.addAttribute("films" , iServiceFilm.findAllFilmOrdred());
        } else {
            model.addAttribute("films" , iServiceFilm.findByCategorieId(id_categorie));
        }
        model.addAttribute("categories" ,iServiceCategorie.GetAllCategories()) ;
        model.addAttribute("id",id_categorie);
        return "affiche" ;
    }

    @GetMapping("new")
    public String afficheNewForm(Model model){
        model.addAttribute("categories" ,iServiceCategorie.GetAllCategories()) ;
        return "ajout" ;
    }
    @PostMapping("add")
    public String add(Film f){
        iServiceFilm.createFilm(f) ;
        return "redirect:/film/all" ;
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        iServiceFilm.deleteFilm(id);
        return "redirect:/film/all" ;
    }

    @GetMapping("update/{id}")
    public String updateFilmModel(Model model , @PathVariable int id){
        model.addAttribute("film" ,iServiceFilm.findFilmById(id)) ;
        model.addAttribute("categories" ,iServiceCategorie.GetAllCategories()) ;
        return "modif" ;
    }
    @PostMapping("update")
    public String updateFilm(Film film) {

        iServiceFilm.updateFilm(film) ;
        return "redirect:/film/all" ;
    }


}
