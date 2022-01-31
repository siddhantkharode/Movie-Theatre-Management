package com.example.movieTheatre.MovieTheatreManagement.controller;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import com.example.movieTheatre.MovieTheatreManagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Movie> getMovie(@PathVariable("m_id") int m_id){
        Movie movie = movieService.getMovieById(m_id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/movie/{m_id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("m_id") int m_id){
        movieService.deleteMovieById(m_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/movie/add")
    public ResponseEntity<HttpStatus> saveMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/movie/edit/{m_id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("m_id") int m_id, @RequestBody Movie movie){
        Movie _movie = movieService.updateMovieById(m_id, movie);
        return new ResponseEntity<>(_movie, HttpStatus.OK);
    }
}
