package com.example.movieTheatre.MovieTheatreManagement.controller;

import com.example.movieTheatre.MovieTheatreManagement.model.Theatre;
import com.example.movieTheatre.MovieTheatreManagement.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity getTheatre(@PathVariable("t_id") int t_id){
        return theatreService.getTheatreById(t_id);
    }

    @DeleteMapping("/theatre/{t_id}")
    public ResponseEntity deleteTheatre(@PathVariable("t_id") int t_id){
        return theatreService.deleteTheatreById(t_id);
    }

    @PostMapping("/theatre/add")
    public ResponseEntity saveTheatre(@Valid @RequestBody Theatre theatre){
        return theatreService.addTheatre(theatre);
    }

    @PutMapping("/theatre/edit/{t_id}")
    public ResponseEntity updateTheatre(@PathVariable("t_id") int t_id, @Valid @RequestBody Theatre theatre){
        return theatreService.updateTheatreById(t_id, theatre);
    }

    @PostMapping("/theatre/{t_id}/movie/{m_id}")
    public ResponseEntity addMovieToTheatre(@PathVariable("t_id") int t_id, @PathVariable("m_id") int m_id) {
        return theatreService.addMovieToTheatre(t_id, m_id);
    }

    @DeleteMapping("/theatre/{t_id}/movie/{m_id}")
    public ResponseEntity deleteMovieFromTheatre(@PathVariable("t_id") int t_id, @PathVariable("m_id") int m_id) {
        return theatreService.deleteMovieFromTheatre(t_id, m_id);
    }
}