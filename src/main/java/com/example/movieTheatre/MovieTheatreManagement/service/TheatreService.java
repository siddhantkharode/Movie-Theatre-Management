package com.example.movieTheatre.MovieTheatreManagement.service;

import com.example.movieTheatre.MovieTheatreManagement.model.Movie;
import com.example.movieTheatre.MovieTheatreManagement.model.Theatre;
import com.example.movieTheatre.MovieTheatreManagement.repository.MovieRepository;
import com.example.movieTheatre.MovieTheatreManagement.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Theatre getTheatreById(int t_id){
        return theatreRepository.findById(t_id).get();
    }

    public void deleteTheatreById(int t_id) {
        theatreRepository.deleteById(t_id);
    }

    public void addTheatre(Theatre theatre) {
        theatreRepository.save(theatre);
    }

    public Theatre updateTheatreById(int t_id, Theatre theatre) {
        Theatre _theatre = theatreRepository.findById(t_id).get();
        _theatre.setT_name(theatre.getT_name());
        _theatre.setAddress(theatre.getAddress());
        _theatre.setShows(theatre.getShows());
        theatreRepository.save(_theatre);
        return _theatre;
    }

    public void addMovieToTheatre(int t_id, int m_id) {
        Theatre _theatre = theatreRepository.findById(t_id).get();
        Movie _movie = movieRepository.findById(m_id).get();

        Set<Movie> theatreSet = _theatre.getMovies();
        theatreSet.add(_movie);

        Set<Theatre> movieSet = _movie.getTheatres();
        movieSet.add(_theatre);
    }

    public void deleteMovieFromTheatre(int t_id, int m_id) {
        Theatre _theatre = theatreRepository.getById(t_id);
        Movie _movie = movieRepository.getById(m_id);

        Set<Movie> theatreSet = _theatre.getMovies();
        theatreSet.remove(_movie);

        Set<Theatre> movieSet = _movie.getTheatres();
        movieSet.add(_theatre);
    }
}
