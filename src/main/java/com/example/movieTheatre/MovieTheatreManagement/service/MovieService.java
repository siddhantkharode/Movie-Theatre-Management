package com.example.movieTheatre.MovieTheatreManagement.service;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import com.example.movieTheatre.MovieTheatreManagement.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public ResponseEntity getMovieById(int m_id){
        Optional<Movie> movie = movieRepository.findById(m_id);

        if(movie.isPresent()) {
            return new ResponseEntity(movie.get(), HttpStatus.OK);
        } else {
            CustomResponse res = new CustomResponse(new Date(), "id not found", HttpStatus.NOT_FOUND, "/movie/" + m_id);
            return new ResponseEntity(res,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteMovieById(int m_id) {
        try {
            movieRepository.deleteById(m_id);
        } catch (Exception e) {
            CustomResponse res = new CustomResponse(new Date(), "id not found", HttpStatus.NOT_FOUND, "/movie/" + m_id);
            return new ResponseEntity(res, HttpStatus.NOT_FOUND);
        }
        CustomResponse res = new CustomResponse(new Date(), "id successfully deleted", HttpStatus.OK, "/movie/" + m_id);
        return new ResponseEntity(res,HttpStatus.OK);
    }

    public ResponseEntity addMovie(Movie movie) {

        int length = movie.getM_name().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(movie.getM_name().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "movie name should not contain digits", HttpStatus.BAD_REQUEST, "/movie/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        movieRepository.save(movie);
        CustomResponse res = new CustomResponse(new Date(), "movie successfully added", HttpStatus.CREATED, "/movie/add");
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    public ResponseEntity updateMovieById(int m_id, Movie movie) {

        int length = movie.getM_name().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(movie.getM_name().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "movie name should not contain digits", HttpStatus.BAD_REQUEST, "/movie/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        try {
            Movie _movie = movieRepository.findById(m_id).get();
            _movie.setM_name(movie.getM_name());
            _movie.setActor(movie.getActor());
            _movie.setDuration(movie.getDuration());
            movieRepository.save(_movie);
            CustomResponse res = new CustomResponse(new Date(), "movie successfully updated", HttpStatus.OK, "/movie/edit/" + m_id);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            CustomResponse res = new CustomResponse(new Date(), "check movie id", HttpStatus.NOT_FOUND, "/movie/edit/" + m_id);
            return new ResponseEntity(res, HttpStatus.NOT_FOUND);
        }
    }
}
