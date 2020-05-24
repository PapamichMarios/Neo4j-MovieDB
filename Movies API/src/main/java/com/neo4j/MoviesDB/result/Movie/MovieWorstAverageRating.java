package com.neo4j.MoviesDB.result.Movie;

import com.neo4j.MoviesDB.model.Movie;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieWorstAverageRating {

    private Movie movie;
    private Double worst_average_rating;

    public MovieWorstAverageRating() {
    }

    public MovieWorstAverageRating(Movie movie, Double worst_average_rating) {
        this.movie = movie;
        this.worst_average_rating = worst_average_rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Double getWorst_average_rating() {
        return worst_average_rating;
    }

    public void setWorst_average_rating(Double worst_average_rating) {
        this.worst_average_rating = worst_average_rating;
    }
}
