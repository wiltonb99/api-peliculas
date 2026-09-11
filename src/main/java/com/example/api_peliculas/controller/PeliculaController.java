package com.example.api_peliculas.controller;

import com.example.api_peliculas.dto.PeliculaDTO;
import com.example.api_peliculas.model.Pelicula;
import com.example.api_peliculas.repository.PeliculaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaRepository repository;

    public PeliculaController(PeliculaRepository repository) {
        this.repository = repository;
    }

    // 1. GET - Obtener todas las películas
    @GetMapping
    public ResponseEntity<List<Pelicula>> obtenerPeliculas() {
        return ResponseEntity.ok(repository.findAll());
    }

    // 2. GET - Obtener película por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(
            @PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. GET - Buscar películas por género
    @GetMapping("/buscar")
    public ResponseEntity<List<Pelicula>> buscarPorGenero(
            @RequestParam String genero) {

        List<Pelicula> resultado =
                repository.findByGeneroIgnoreCase(genero);

        return ResponseEntity.ok(resultado);
    }

    // 4. POST - Crear película
    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(
            @RequestBody PeliculaDTO datos) {

        Pelicula nuevaPelicula = new Pelicula(
                null,
                datos.titulo(),
                datos.genero(),
                datos.anio()
        );

        Pelicula guardada = repository.save(nuevaPelicula);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardada);
    }

    // 5. PUT - Actualizar película
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(
            @PathVariable Long id,
            @RequestBody PeliculaDTO datos) {

        return repository.findById(id)
                .map(pelicula -> {

                    pelicula.setTitulo(datos.titulo());
                    pelicula.setGenero(datos.genero());
                    pelicula.setAnio(datos.anio());

                    Pelicula actualizada = repository.save(pelicula);

                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 6. DELETE - Eliminar película
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(
            @PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}