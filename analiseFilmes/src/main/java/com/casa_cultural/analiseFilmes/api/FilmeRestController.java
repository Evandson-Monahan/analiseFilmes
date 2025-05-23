/*package com.casa_cultural.analiseFilmes.api;

import com.casa_cultural.analiseFilmes.model.Filme;
import com.casa_cultural.analiseFilmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class FilmeRestController {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    @GetMapping
    public List<Filme> listarTodosOsFilmes(){
        return filmeRepository.findAll();
    }
    
    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme){
        return filmeRepository.save(filme);
    }
    
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable Integer id, @RequestBody Filme novoFilme){
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(novoFilme.getTitulo());
            filme.setSinopse(novoFilme.getSinopse());
            filme.setGenero(novoFilme.getGenero());
            filme.setAnoLancamento(novoFilme.getAnoLancamento());
            return filmeRepository.save(filme);
        }).orElse(null);
    }
    
    @DeleteMapping("/{id}")
    public void deletarFilme(@PathVariable Integer id){
        filmeRepository.deleteById(id);
    }
}*/