package com.neo4j.MoviesDB.result.Individual;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class CoActorsTotalRatings {

    private Individual actor;
    private Individual coactor;
    private Long total_ratings;

    public CoActorsTotalRatings() {
    }

    public CoActorsTotalRatings(Individual actor, Individual coactor, Long total_ratings) {
        this.actor = actor;
        this.coactor = coactor;
        this.total_ratings = total_ratings;
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

    public Long getTotal_ratings() {
        return total_ratings;
    }

    public void setTotal_ratings(Long total_ratings) {
        this.total_ratings = total_ratings;
    }
}
