package com.neo4j.MoviesDB.model;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "WORKED_AT")
public class Crew {

    @Id
    @GeneratedValue
    private Long id;

    private String department;
    private String job;

    @Property(name = "credit_id")
    private String creditId;

    @StartNode
    private Individual individual;

    @EndNode
    private Movie movie;

    public Crew() {
    }

    public Crew(Long id, String department, String job, String creditId, Individual individual, Movie movie) {
        this.id = id;
        this.department = department;
        this.job = job;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
