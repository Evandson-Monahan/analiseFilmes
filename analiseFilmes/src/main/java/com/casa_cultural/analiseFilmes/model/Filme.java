package com.casa_cultural.analiseFilmes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private int anoLancamento;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("filme")
    private List<Analise> analises;

    // Getters e Setters
    public Integer getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public List<Analise> getAnalises() { return analises; }
    public void setAnalises(List<Analise> analises) { this.analises = analises; }
}