package com.neo4j.MoviesDB.model;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "PLAYED_IN")
public class Cast {

    @Id
    @GeneratedValue
    private Long id;

    private String character;

    @Property(name = "credit_id")
    private String creditId;

    @StartNode
    private Individual individual;

    @EndNode
    private Movie movie;

    public Cast() {
    }

    public Cast(Long id, String character, String creditId, Individual individual, Movie movie) {
        this.id = id;
        this.character = character;
        this.creditId = creditId;
        this.individual = individual;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCredit_id() {
        return creditId;
    }

    public void setCredit_id(String creditId) {
        this.creditId = creditId;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
