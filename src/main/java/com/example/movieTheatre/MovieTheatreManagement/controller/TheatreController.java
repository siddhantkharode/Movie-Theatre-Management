package com.example.movieTheatre.MovieTheatreManagement.controller;

import com.example.movieTheatre.MovieTheatreManagement.model.Theatre;
import com.example.movieTheatre.MovieTheatreManagement.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @GetMapping("/theatres")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    @GetMapping("/theatre/{t_id}")
    public ResponseEntity<Theatre> getTheatre(@PathVariable("t_id") int t_id){
        Theatre theatre = theatreService.getTheatreById(t_id);
        return new ResponseEntity<>(theatre, HttpStatus.OK);
    }

    @DeleteMapping("/theatre/{t_id}")
    public ResponseEntity<HttpStatus> deleteTheatre(@PathVariable("t_id") int t_id){
        theatreService.deleteTheatreById(t_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/theatre/add")
    public ResponseEntity<Theatre> saveTheatre(@RequestBody Theatre theatre){
        theatreService.addTheatre(theatre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/theatre/edit/{t_id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable("t_id") int t_id, @RequestBody Theatre theatre){
        Theatre _theatre = theatreService.updateTheatreById(t_id, theatre);
        return new ResponseEntity<>(_theatre, HttpStatus.OK);
    }

    @PostMapping("/theatre/{t_id}/movie/{m_id}")
    public ResponseEntity<HttpStatus> addMovieToTheatre(@PathVariable("t_id") int t_id, @PathVariable("m_id") int m_id) {
        theatreService.addMovieToTheatre(t_id, m_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/theatre/{t_id}/movie/{m_id}")
    public ResponseEntity<HttpStatus> deleteMovieFromTheatre(@PathVariable("t_id") int t_id, @PathVariable("m_id") int m_id) {
        theatreService.deleteMovieFromTheatre(t_id,m_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
