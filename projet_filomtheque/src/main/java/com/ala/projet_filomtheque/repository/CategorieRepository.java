package com.ala.projet_filomtheque.repository;

import com.ala.projet_filomtheque.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
}
