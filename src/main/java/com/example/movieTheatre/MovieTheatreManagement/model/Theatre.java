package com.example.movieTheatre.MovieTheatreManagement.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "theatre")
public class Theatre {

    @Id
    private int t_id;

    @Column(name = "t_name")
    private String t_name;

    @Column(name = "shows")
    private int shows;

    @Column(name = "address")
    private String address;

    @ManyToMany(mappedBy = "theatres")
    private Set<Movie> movies;

    public Theatre() {
    }

    public Theatre(String t_name, int shows, String address) {
        this.t_name = t_name;
        this.shows = shows;
        this.address = address;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public int getShows() {
        return shows;
    }

    public void setShows(int shows) {
        this.shows = shows;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
