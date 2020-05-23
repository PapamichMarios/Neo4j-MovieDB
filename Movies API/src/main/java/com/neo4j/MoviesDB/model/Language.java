package com.neo4j.MoviesDB.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
public class Language {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String name;

    @JsonIgnoreProperties("spokenLanguages")
    @Relationship(type = "SPEAKS", direction = INCOMING)
    private Set<Movie> movies = new HashSet<>();

    public Language() {
    }

    public Language(Long id, String code, String name, Set<Movie> movies) {
        this.id = id;
        this.code = code;
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
