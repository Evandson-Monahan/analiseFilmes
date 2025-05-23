package com.casa_cultural.analiseFilmes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties("analises")
    private Filme filme;

    @Column(nullable = false)
    private String comentario;
    
     @Column(nullable = false, updatable = false)
     private LocalDateTime dataCriacao;
     
     @PrePersist
      protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    @Column(nullable = false)
    private int nota;

    // Getters e Setters
    public Integer getId() { return id; }
    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
} 