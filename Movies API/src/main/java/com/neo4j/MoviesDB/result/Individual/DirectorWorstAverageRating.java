package com.neo4j.MoviesDB.result.Individual;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class DirectorWorstAverageRating {
    private Individual director;
    private Double worst_average_rating;

    public DirectorWorstAverageRating() {
    }

    public DirectorWorstAverageRating(Individual director, Double worst_average_rating) {
        this.director = director;
        this.worst_average_rating = worst_average_rating;
    }

    public Individual getDirector() {
        return director;
    }

    public void setDirector(Individual director) {
        this.director = director;
    }

    public Double getWorst_average_rating() {
        return worst_average_rating;
    }

    public void setWorst_average_rating(Double worst_average_rating) {
        this.worst_average_rating = worst_average_rating;
    }
}
