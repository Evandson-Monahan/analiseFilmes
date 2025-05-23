package com.casa_cultural.analiseFilmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class AnaliseFilmesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnaliseFilmesApplication.class, args);
    }
}