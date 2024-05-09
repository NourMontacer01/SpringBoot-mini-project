package com.ala.projet_filomtheque.service;

import com.ala.projet_filomtheque.entities.Categorie;

import java.util.List;

public interface IServiceCategorie {
    public Categorie createCategorie(Categorie c);
    public Categorie GetCategorieById(int id);
    public List<Categorie> GetAllCategories();
    public Categorie updateCategorie(Categorie c);
    public void deleteCategorie(int id) ;
}
