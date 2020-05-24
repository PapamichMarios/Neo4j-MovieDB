package com.neo4j.MoviesDB.result.Movie;

import com.neo4j.MoviesDB.model.Movie;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieMostRatings {

    private Movie movie;
    private Long total_ratings;

    public MovieMostRatings() {
    }

    public MovieMostRatings(Movie movie, Long total_ratings) {
        this.movie = movie;
        this.total_ratings = total_ratings;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getTotal_ratings() {
        return total_ratings;
    }

    public void setTotal_ratings(Long total_ratings) {
        this.total_ratings = total_ratings;
    }
}
