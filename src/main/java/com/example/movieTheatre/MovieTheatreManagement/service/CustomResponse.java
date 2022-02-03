package com.example.movieTheatre.MovieTheatreManagement.service;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class CustomResponse {

    private Date timestamp;
    private String message;
    private HttpStatus status;
    private String path;

    public CustomResponse(Date timestamp, String message, HttpStatus status, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
