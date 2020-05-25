package com.neo4j.MoviesDB.result.Movie;

import com.neo4j.MoviesDB.model.Movie;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieMostRatings {

    private Movie top_movie;
    private Long most_ratings;

    public MovieMostRatings() {
    }

    public MovieMostRatings(Movie top_movie, Long most_ratings) {
        this.top_movie = top_movie;
        this.most_ratings = most_ratings;
    }

    public Movie getTop_movie() {
        return top_movie;
    }

    public void setTop_movie(Movie top_movie) {
        this.top_movie = top_movie;
    }

    public Long getMost_ratings() {
        return most_ratings;
    }

    public void setMost_ratings(Long most_ratings) {
        this.most_ratings = most_ratings;
    }
}
