package com.neo4j.MoviesDB.result.Movie;

import com.neo4j.MoviesDB.model.Movie;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieBestAverageRating {

    private Movie Movie;
    private Double best_average_rating;

    public MovieBestAverageRating() {
    }

    public MovieBestAverageRating(Movie movie, Double best_average_rating) {
        this.Movie = movie;
        this.best_average_rating = best_average_rating;
    }

    public com.neo4j.MoviesDB.model.Movie getMovie() {
        return Movie;
    }

    public void setMovie(com.neo4j.MoviesDB.model.Movie movie) {
        Movie = movie;
    }

    public Double getAverage_rating() {
        return best_average_rating;
    }

    public void setAverage_rating(Double best_average_rating) {
        this.best_average_rating = best_average_rating;
    }
}
