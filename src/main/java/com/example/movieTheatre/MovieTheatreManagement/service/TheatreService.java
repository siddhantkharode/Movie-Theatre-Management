package com.example.movieTheatre.MovieTheatreManagement.service;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import com.example.movieTheatre.MovieTheatreManagement.model.Theatre;
import com.example.movieTheatre.MovieTheatreManagement.repository.MovieRepository;
import com.example.movieTheatre.MovieTheatreManagement.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    MovieRepository movieRepository;

    public List<Theatre> getAllTheatres() {
        List<Theatre> theatres = new ArrayList<Theatre>();
        theatreRepository.findAll().forEach(theatre -> theatres.add(theatre));
        return theatres;
    }

    public ResponseEntity getTheatreById(int t_id){
        Optional<Theatre> theatre = theatreRepository.findById(t_id);

        if(theatre.isPresent()) {
            return new ResponseEntity(theatre.get(), HttpStatus.OK);
        } else {
            CustomResponse res = new CustomResponse(new Date(), "id not found", HttpStatus.NOT_FOUND, "/theatre/" + t_id);
            return new ResponseEntity(res,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteTheatreById(int t_id) {
        try {
            theatreRepository.deleteById(t_id);
        } catch (Exception e) {
            CustomResponse res = new CustomResponse(new Date(), "id not found", HttpStatus.NOT_FOUND, "/theatre/" + t_id);
            return new ResponseEntity(res, HttpStatus.NOT_FOUND);
        }
        CustomResponse res = new CustomResponse(new Date(), "id successfully deleted", HttpStatus.OK, "/theatre/" + t_id);
        return new ResponseEntity(res,HttpStatus.OK);
    }

    public ResponseEntity addTheatre(Theatre theatre) {

        int length = theatre.getT_name().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(theatre.getT_name().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "theatre name should not contain digits", HttpStatus.BAD_REQUEST, "/theatre/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        theatreRepository.save(theatre);
        CustomResponse res = new CustomResponse(new Date(), "theatre successfully added", HttpStatus.CREATED, "/theatre/add");
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    public ResponseEntity updateTheatreById(int t_id, Theatre theatre) {

        int length = theatre.getT_name().length();
        for(int i = 0; i < length; i++){
            if(Character.isDigit(theatre.getT_name().charAt(i))) {
                CustomResponse res = new CustomResponse(new Date(), "theatre name should not contain digits", HttpStatus.BAD_REQUEST, "/theatre/add");
                return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
            }
        }

        try {
            Theatre _theatre = theatreRepository.findById(t_id).get();
            _theatre.setT_name(theatre.getT_name());
            _theatre.setShows(theatre.getShows());
            _theatre.setAddress(theatre.getAddress());
            theatreRepository.save(_theatre);
            CustomResponse res = new CustomResponse(new Date(), "theatre successfully updated", HttpStatus.OK, "/theatre/edit/" + t_id);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            CustomResponse res = new CustomResponse(new Date(), "check theatre id", HttpStatus.NOT_FOUND, "/movie/edit/" + t_id);
            return new ResponseEntity(res, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity addMovieToTheatre(int t_id, int m_id) {
        try {
            Theatre _theatre = theatreRepository.findById(t_id).get();
            Movie _movie = movieRepository.findById(m_id).get();

            Set<Movie> theatreSet = _theatre.getMovies();
            theatreSet.add(_movie);
            theatreRepository.save(_theatre);

            Set<Theatre> movieSet = _movie.getTheatres();
            movieSet.add(_theatre);
            movieRepository.save(_movie);

            CustomResponse res = new CustomResponse(new Date(), "movie added to theatre", HttpStatus.OK, "/theatre/" + t_id + "/movie/" + m_id);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            CustomResponse res = new CustomResponse(new Date(), "check theatre id and movie id", HttpStatus.NOT_FOUND, "/theatre/" + t_id + "/movie/" + m_id);
            return new ResponseEntity(res, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteMovieFromTheatre(int t_id, int m_id) {
        try {
            Theatre _theatre = theatreRepository.getById(t_id);
            Movie _movie = movieRepository.getById(m_id);

            Set<Movie> theatreSet = _theatre.getMovies();
            theatreSet.remove(_movie);
            theatreRepository.save(_theatre);

            Set<Theatre> movieSet = _movie.getTheatres();
            movieSet.remove(_theatre);
            movieRepository.save(_movie);

            CustomResponse res = new CustomResponse(new Date(), "movie removed from theatre", HttpStatus.OK, "/theatre/" + t_id + "/movie/" + m_id);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            CustomResponse res = new CustomResponse(new Date(), "check theatre id and movie id", HttpStatus.NOT_FOUND, "/theatre/" + t_id + "/movie/" + m_id);
            return new ResponseEntity(res, HttpStatus.NOT_FOUND);
        }
    }
}
