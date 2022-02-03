package com.example.movieTheatre.MovieTheatreManagement.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "theatre")
@Getter
@Setter
public class Theatre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int t_id;

    @Column(name = "t_name")
    @NotBlank
    private String t_name;

    @Column(name = "shows")
    @Min(value = 0)
    private int shows;

    @Column(name = "address")
    @NotBlank
    private String address;

    @ManyToMany(mappedBy = "theatres")
    private Set<Movie> movies;

}
