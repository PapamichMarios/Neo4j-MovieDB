package com.neo4j.MoviesDB.result.Individual;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class DirectorBestAverageRating {

    private Individual director;
    private Double best_average_rating;

    public DirectorBestAverageRating() {
    }

    public DirectorBestAverageRating(Individual director, Double best_average_rating) {
        this.director = director;
        this.best_average_rating = best_average_rating;
    }

    public Individual getDirector() {
        return director;
    }

    public void setDirector(Individual director) {
        this.director = director;
    }

    public Double getBest_average_rating() {
        return best_average_rating;
    }

    public void setBest_average_rating(Double best_average_rating) {
        this.best_average_rating = best_average_rating;
    }
}
