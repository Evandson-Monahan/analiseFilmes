package com.casa_cultural.analiseFilmes.controller;

import com.casa_cultural.analiseFilmes.model.Filme;
import com.casa_cultural.analiseFilmes.repository.FilmeRepository;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        return "lista-filmes";
    }
    
    @GetMapping("/novo")
    public String mostrarFormularioNovoFilme(@RequestParam(required = false) Integer id, Model model) {
        Filme filme = id == null ? new Filme() : filmeRepository.findById(id).orElse(new Filme());
        model.addAttribute("filme", filme);
        return "form-filme";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Integer id, Model model) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isEmpty()) {
            return "erro"; // Se não encontrar, redireciona para erro
        }
        model.addAttribute("filme", filme.get());
        return "detalhes-filme";
    }

    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Integer id, Model model) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de filme inválido: " + id));
        model.addAttribute("filme", filme);
        return "form-filme"; // Mesmo template usado para novo filme
    }

    @PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<?> salvarFilme(@RequestBody Filme filme) {
    try {
        // Validação básica
        if (filme.getTitulo() == null || filme.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "O título do filme é obrigatório"));
        }
        
        if (filme.getGenero() == null || filme.getGenero().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "O gênero do filme é obrigatório"));
        }
        
        Filme filmeSalvo = filmeRepository.save(filme);
        return ResponseEntity.ok(filmeSalvo);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Erro ao salvar filme: " + e.getMessage()));
    }
}

    @PostMapping("/excluir/{id}")
    public String excluirFilme(@PathVariable Integer id) {
        filmeRepository.deleteById(id);
        return "redirect:/filmes";
    }
}