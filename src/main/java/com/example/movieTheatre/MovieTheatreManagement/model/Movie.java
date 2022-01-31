package com.example.movieTheatre.MovieTheatreManagement.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    private int m_id;

    @Column(name = "m_name")
    private String m_name;

    @Column(name = "actor")
    private String actor;

    @Column(name = "duration")
    private int duration;

    @ManyToMany
    @JoinTable(name = "theatre_has_movie", joinColumns = {@JoinColumn(name = "m_id")}, inverseJoinColumns = {@JoinColumn(name = "t_id")})
    private Set<Theatre> theatres;

    public Movie() {
    }

    public Movie(String m_name, String actor, int duration) {
        this.m_name = m_name;
        this.actor = actor;
        this.duration = duration;
    }

    public Set<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(Set<Theatre> theatres) {
        this.theatres = theatres;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
