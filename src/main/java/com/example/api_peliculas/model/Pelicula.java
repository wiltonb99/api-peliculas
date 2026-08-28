package com.example.api_peliculas.model;

public class Pelicula {

    private Long id;
    private String titulo;
    private String genero;
    private Integer anio;

    public Pelicula() {
    }

    public Pelicula(Long id, String titulo, String genero, Integer anio) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
