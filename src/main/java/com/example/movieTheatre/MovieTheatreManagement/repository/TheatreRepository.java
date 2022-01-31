package com.example.movieTheatre.MovieTheatreManagement.repository;

import com.example.movieTheatre.MovieTheatreManagement.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
}
