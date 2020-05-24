package com.neo4j.MoviesDB.result.Movie;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieCharacter {

    private String character;

    public MovieCharacter() {
    }

    public MovieCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
