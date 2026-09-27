package com.example.api_peliculas.repository;

import com.example.api_peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByGeneroIgnoreCase(String genero);
}