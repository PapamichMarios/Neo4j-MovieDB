package com.neo4j.MoviesDB.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "movie_id")
    private Long movieId;

    @Property(name = "user_id")
    private Long userId;

    private Double rating;

    private String timestamp;

    @Relationship(type = "HAS_RATING", direction = INCOMING)
    private Set<Movie> movies = new HashSet<>();

    public Rating() {
    }

    public Rating(Long id, Long movieId, Long userId, Double rating, String timestamp, Set<Movie> movies) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
        this.timestamp = timestamp;
        this.movies = movies;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
