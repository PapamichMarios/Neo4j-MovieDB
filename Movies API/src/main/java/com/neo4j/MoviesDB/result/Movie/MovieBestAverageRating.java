package com.neo4j.MoviesDB.result.Movie;

import com.neo4j.MoviesDB.model.Movie;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieBestAverageRating {

    private Movie movie;
    private Double best_average_rating;

    public MovieBestAverageRating() {
    }

    public MovieBestAverageRating(Movie movie, Double best_average_rating) {
        this.movie = movie;
        this.best_average_rating = best_average_rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Double getBest_average_rating() {
        return best_average_rating;
    }

    public void setBest_average_rating(Double best_average_rating) {
        this.best_average_rating = best_average_rating;
    }
}
