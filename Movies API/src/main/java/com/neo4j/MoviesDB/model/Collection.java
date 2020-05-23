package com.neo4j.MoviesDB.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
public class Collection {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Property(name = "poster_path")
    private String posterPath;

    @Property(name = "backdrop_path")
    private String backdropPath;

    @JsonIgnoreProperties("collections")
    @Relationship(type = "BELONGS_TO", direction = INCOMING)
    private Set<Movie> movies = new HashSet<>();

    public Collection() {
    }

    public Collection(Long id, String name, String posterPath, String backdropPath, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.movies = movies;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

}
