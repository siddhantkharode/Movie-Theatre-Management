package com.example.movieTheatre.MovieTheatreManagement.controller;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import com.example.movieTheatre.MovieTheatreManagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movie/{m_id}")
    public ResponseEntity getMovie(@PathVariable("m_id") int m_id){
        return movieService.getMovieById(m_id);
    }

    @DeleteMapping("/movie/{m_id}")
    public ResponseEntity deleteMovie(@PathVariable("m_id") int m_id){
        return movieService.deleteMovieById(m_id);
    }

    @PostMapping("/movie/add")
    public ResponseEntity saveMovie(@Valid @RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @PutMapping("/movie/edit/{m_id}")
    public ResponseEntity updateMovie(@PathVariable("m_id") int m_id, @Valid @RequestBody Movie movie){
        return movieService.updateMovieById(m_id, movie);
    }
}
