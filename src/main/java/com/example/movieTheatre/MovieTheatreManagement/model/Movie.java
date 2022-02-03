package com.example.movieTheatre.MovieTheatreManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "theatres"})
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_id;

    @Column(name = "m_name")
    @NotBlank
    private String m_name;

    @Column(name = "actor")
    @NotBlank
    private String actor;

    @Column(name = "duration")
    @Min(value = 1)
    private int duration;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "theatre_has_movie", joinColumns = {@JoinColumn(name = "m_id")}, inverseJoinColumns = {@JoinColumn(name = "t_id")})
    private Set<Theatre> theatres;
}
