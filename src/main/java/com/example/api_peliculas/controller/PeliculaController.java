package com.example.api_peliculas.controller;

import com.example.api_peliculas.dto.PeliculaDTO;
import com.example.api_peliculas.model.Pelicula;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(
                1L,
                "Titanic",
                "Romance",
                1997
        ));

        peliculas.add(new Pelicula(
                2L,
                "Matrix",
                "Ciencia ficción",
                1999
        ));

        peliculas.add(new Pelicula(
                3L,
                "Avatar",
                "Ciencia ficción",
                2009
        ));
    }

    // 1. Endpoint GET
    @GetMapping
    public ResponseEntity<List<Pelicula>> obtenerPeliculas() {
        return ResponseEntity.ok(peliculas);
    }

    // 2. GET utilizando PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(
            @PathVariable Long id) {

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                return ResponseEntity.ok(pelicula);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // 3. GET utilizando RequestParam
    @GetMapping("/buscar")
    public ResponseEntity<List<Pelicula>> buscarPorGenero(
            @RequestParam String genero) {

        List<Pelicula> resultado = peliculas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase(genero))
                .toList();

        return ResponseEntity.ok(resultado);
    }

    // 4. POST utilizando RequestBody y DTO
    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(
            @RequestBody PeliculaDTO datos) {

        Long nuevoId = peliculas.size() + 1L;

        Pelicula nuevaPelicula = new Pelicula(
                nuevoId,
                datos.titulo(),
                datos.genero(),
                datos.anio()
        );

        peliculas.add(nuevaPelicula);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaPelicula);
    }
}
