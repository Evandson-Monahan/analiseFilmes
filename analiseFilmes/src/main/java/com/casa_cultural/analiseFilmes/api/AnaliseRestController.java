/*package com.casa_cultural.analiseFilmes.api;

import com.casa_cultural.analiseFilmes.model.Analise;
import com.casa_cultural.analiseFilmes.model.Filme;
import com.casa_cultural.analiseFilmes.repository.AnaliseRepository;
import com.casa_cultural.analiseFilmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analises")
@CrossOrigin(origins = "*")
public class AnaliseRestController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public List<Analise> listarAnalises() {
        return analiseRepository.findAll();
    }

    @PostMapping
    public Analise criarAnalise(@RequestBody Analise analise) {
        Filme filme = filmeRepository.findById(analise.getFilme().getId()).orElse(null);
        if (filme != null) {
            analise.setFilme(filme);
            return analiseRepository.save(analise);
        }
        return null;
    }

    @PutMapping("/{id}")
    public Analise atualizarAnalise(@PathVariable Integer id, @RequestBody Analise novaAnalise) {
        return analiseRepository.findById(id).map(analise -> {
            analise.setNota(novaAnalise.getNota());
            analise.setComentario(novaAnalise.getComentario());
            return analiseRepository.save(analise);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarAnalise(@PathVariable Integer id) {
        analiseRepository.deleteById(id);
    }
}*/