package com.neo4j.MoviesDB.result.Individual;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class DirectorsCooperations {

    Individual director;
    Long cooperations;

    public DirectorsCooperations() {
    }

    public DirectorsCooperations(Individual director, Long cooperations) {
        this.director = director;
        this.cooperations = cooperations;
    }

    public Individual getDirector() {
        return director;
    }

    public void setDirector(Individual director) {
        this.director = director;
    }

    public Long getCooperations() {
        return cooperations;
    }

    public void setCooperations(Long cooperations) {
        this.cooperations = cooperations;
    }
}
