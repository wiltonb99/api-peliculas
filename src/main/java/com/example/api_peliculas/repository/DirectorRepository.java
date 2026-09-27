package com.example.api_peliculas.repository;

import com.example.api_peliculas.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}