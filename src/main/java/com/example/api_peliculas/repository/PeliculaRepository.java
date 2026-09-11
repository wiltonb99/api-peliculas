package com.example.api_peliculas.repository;

import com.example.api_peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByGeneroIgnoreCase(String genero);
}