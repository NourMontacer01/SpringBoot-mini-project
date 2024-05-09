package com.ala.projet_filomtheque.service;

import com.ala.projet_filomtheque.entities.Categorie;
import com.ala.projet_filomtheque.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategorie implements IServiceCategorie{
   @Autowired
    CategorieRepository categorieRepository;
    @Override
    public Categorie createCategorie(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public Categorie GetCategorieById(int id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public List<Categorie> GetAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie updateCategorie(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public void deleteCategorie(int id) {
        categorieRepository.deleteById(id);
    }
}
