package com.example.movieTheatre.MovieTheatreManagement.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse {

    private Date timestamp;
    private String message;
    private HttpStatus status;
    private String path;

}
