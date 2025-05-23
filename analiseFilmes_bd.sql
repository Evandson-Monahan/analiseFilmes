CREATE DATABASE analiseFilmes_bd;
USE analiseFilmes_bd;

CREATE TABLE filme (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    sinopse TEXT,
    genero VARCHAR(255),
    ano_lancamento INT
);
CREATE TABLE analise (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comentario VARCHAR (255),
    nota INT,
    filme_id INT,
    FOREIGN KEY (filme_id) REFERENCES filme(id)
);

SELECT * FROM filme;
DROP TABLE filme;