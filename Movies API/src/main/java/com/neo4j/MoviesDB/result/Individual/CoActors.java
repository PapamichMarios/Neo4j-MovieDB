package com.neo4j.MoviesDB.result.Individual;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class CoActors {

    private Individual actor;
    private Individual coactor;

    public CoActors() {
    }

    public CoActors(Individual actor, Individual coactor) {
        this.actor = actor;
        this.coactor = coactor;
    }

    public Individual getActor() {
        return actor;
    }

    public void setActor(Individual actor) {
        this.actor = actor;
    }

    public Individual getCoactor() {
        return coactor;
    }

    public void setCoactor(Individual coactor) {
        this.coactor = coactor;
    }
}
