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

    /**
     * This method is used to return all the movies in the database.
     * @return List of all movies.
     */
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    /**
     * This method is used to return details of a movie.
     * @param m_id {movie id}
     * @return movie name, actor name and duration of movie in minutes.
     */
    public ResponseEntity getMovieById(int m_id){
        Optional<Movie> movie = movieRepository.findById(m_id);

        if(movie.isPresent()) {
            return new ResponseEntity(movie.get(), HttpStatus.OK);
        } else {
            CustomResponse res = new CustomResponse(new Date(), "id not found", HttpStatus.NOT_FOUND, "/movie/" + m_id);
            return new ResponseEntity(res,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to delete a movie from database.
     * @param m_id {movie id}
     */
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

    /**
     * This method is used to add movie to database.
     * The user has to pass movie name, actor name and duration in the request body to successfully add a movie.
     * @param movie {@link Movie}
     */
    public ResponseEntity addMovie(Movie movie) {

        // Check whether movie name has digits.
        int length = movie.getM_name().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(movie.getM_name().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "movie name should not contain digits", HttpStatus.BAD_REQUEST, "/movie/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        // Check whether actor name has digits.
        length = movie.getActor().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(movie.getActor().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "actor name should not contain digits", HttpStatus.BAD_REQUEST, "/movie/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        movieRepository.save(movie);
        CustomResponse res = new CustomResponse(new Date(), "movie successfully added", HttpStatus.CREATED, "/movie/add");
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    /**
     * This method is used to update details of existing movie.
     * The user has to pass movie name, actor name, duration in request body to successfully update a record.
     * @param m_id {movie id}
     * @param movie {@link Movie}
     */
    public ResponseEntity updateMovieById(int m_id, Movie movie) {

        // Check whether movie name has digits.
        int length = movie.getM_name().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(movie.getM_name().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "movie name should not contain digits", HttpStatus.BAD_REQUEST, "/movie/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        // Check whether actor name has digits.
        length = movie.getActor().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(movie.getActor().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "actor name should not contain digits", HttpStatus.BAD_REQUEST, "/movie/add");
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
