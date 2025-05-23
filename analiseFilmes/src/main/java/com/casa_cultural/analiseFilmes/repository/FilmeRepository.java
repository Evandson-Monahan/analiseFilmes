package com.casa_cultural.analiseFilmes.repository;

import com.casa_cultural.analiseFilmes.model.Filme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    
    // Adicionando um método para buscar filmes por título
    List<Filme> findByTituloContainingIgnoreCase(String titulo);
}