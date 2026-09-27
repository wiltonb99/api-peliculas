package com.example.api_peliculas.filter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestTracingFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestTracingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Crear un identificador único para la solicitud
        String requestId = UUID.randomUUID().toString();

        // Agregar el identificador a la respuesta
        response.setHeader("X-Request-ID", requestId);

        // Registrar la solicitud
        logger.info(
                "Solicitud recibida: {} {} - Request ID: {}",
                request.getMethod(),
                request.getRequestURI(),
                requestId
        );

        try {

            filterChain.doFilter(request, response);

        } finally {

            // Registrar cuando termina la solicitud
            logger.info(
                    "Solicitud terminada: {} {} - Estado: {} - Request ID: {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    requestId
            );
        }
    }
}
