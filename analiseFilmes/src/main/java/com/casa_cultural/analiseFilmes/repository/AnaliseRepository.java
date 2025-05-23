package com.casa_cultural.analiseFilmes.repository;

import com.casa_cultural.analiseFilmes.model.Analise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer> {

    // Adicionando um método para buscar análises por filme
    List<Analise> findByFilmeId(Integer filmeId);
}