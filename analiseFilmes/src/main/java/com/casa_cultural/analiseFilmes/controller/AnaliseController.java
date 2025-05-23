package com.casa_cultural.analiseFilmes.controller;

import com.casa_cultural.analiseFilmes.model.Analise;
import com.casa_cultural.analiseFilmes.model.Filme;
import com.casa_cultural.analiseFilmes.dto.AnaliseDTO;
import com.casa_cultural.analiseFilmes.repository.AnaliseRepository;
import com.casa_cultural.analiseFilmes.repository.FilmeRepository;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/analises")
public class AnaliseController {
    private final AnaliseRepository analiseRepository;
    private final FilmeRepository filmeRepository;

    public AnaliseController(AnaliseRepository analiseRepository, FilmeRepository filmeRepository) {
        this.analiseRepository = analiseRepository;
        this.filmeRepository = filmeRepository;
    }

    @PostMapping
@ResponseBody
public ResponseEntity<?> salvarAnalise(@RequestBody AnaliseDTO analiseDTO) {
    try {
        Filme filme = filmeRepository.findById(analiseDTO.getFilmeId())
            .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));

        Analise analise = new Analise();
        analise.setFilme(filme);
        analise.setComentario(analiseDTO.getComentario());
        analise.setNota(analiseDTO.getNota());
        analise.setDataCriacao(LocalDateTime.now());

        Analise analiseSalva = analiseRepository.save(analise);
        
        // Retorne um DTO simplificado em vez da entidade completa
        return ResponseEntity.ok(Map.of(
            "id", analiseSalva.getId(),
            "comentario", analiseSalva.getComentario(),
            "nota", analiseSalva.getNota(),
            "dataCriacao", analiseSalva.getDataCriacao()
        ));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of(
            "message", "Erro ao salvar análise: " + e.getMessage()
        ));
    }
}
}