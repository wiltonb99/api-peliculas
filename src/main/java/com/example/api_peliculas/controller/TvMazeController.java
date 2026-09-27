package com.example.api_peliculas.controller;

import com.example.api_peliculas.service.TvMazeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class TvMazeController {

    private final TvMazeService tvMazeService;

    public TvMazeController(TvMazeService tvMazeService) {
        this.tvMazeService = tvMazeService;
    }

    @GetMapping("/buscar")
    public String buscarSerie(@RequestParam String nombre) {

        return tvMazeService.buscarSerie(nombre);
    }
}