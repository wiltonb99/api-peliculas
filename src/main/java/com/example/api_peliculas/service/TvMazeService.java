package com.example.api_peliculas.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Service
public class TvMazeService {

    private static final Logger logger =
            LoggerFactory.getLogger(TvMazeService.class);

    private final RestClient restClient;
    private final Counter busquedasTvMaze;

    public TvMazeService(MeterRegistry meterRegistry) {

        this.restClient = RestClient.builder()
                .baseUrl("https://api.tvmaze.com")
                .build();

        this.busquedasTvMaze = Counter.builder("tvmaze_busquedas_total")
                .description("Cantidad de búsquedas realizadas en TVmaze")
                .register(meterRegistry);
    }

    public String buscarSerie(String nombre) {

        // INFO: se registra cada búsqueda realizada
        logger.info("Buscando serie en TVmaze: {}", nombre);

        // Incrementar la métrica personalizada
        busquedasTvMaze.increment();

        try {

            String resultado = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/singlesearch/shows")
                            .queryParam("q", nombre)
                            .build())
                    .retrieve()
                    .body(String.class);

            // INFO: búsqueda exitosa
            logger.info("Serie encontrada en TVmaze: {}", nombre);

            return resultado;

        } catch (RestClientResponseException e) {

            // WARN: TVmaze no encontró la serie
            logger.warn("No se encontró la serie en TVmaze: {}", nombre);

            return "{\"error\":\"No se encontró la serie o ocurrió un error al consultar TVmaze\"}";

        } catch (Exception e) {

            // ERROR: ocurrió un problema diferente al consultar TVmaze
            logger.error("Error al consultar TVmaze para la serie: {}", nombre, e);

            return "{\"error\":\"No se encontró la serie o ocurrió un error al consultar TVmaze\"}";
        }
    }
}