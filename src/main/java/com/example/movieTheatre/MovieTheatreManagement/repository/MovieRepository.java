package com.example.movieTheatre.MovieTheatreManagement.repository;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
