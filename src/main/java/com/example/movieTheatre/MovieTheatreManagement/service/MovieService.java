package com.example.movieTheatre.MovieTheatreManagement.service;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import com.example.movieTheatre.MovieTheatreManagement.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public Movie getMovieById(int m_id){
        return movieRepository.findById(m_id).get();
    }

    public void deleteMovieById(int m_id) {
        movieRepository.deleteById(m_id);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie updateMovieById(int m_id, Movie movie) {
        Movie _movie = movieRepository.findById(m_id).get();
        _movie.setM_name(movie.getM_name());
        _movie.setActor(movie.getActor());
        _movie.setDuration(movie.getDuration());
        movieRepository.save(_movie);
        return _movie;
    }
}
