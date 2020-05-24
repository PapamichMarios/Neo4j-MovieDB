package com.neo4j.MoviesDB.result.Movie;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class MovieOriginalLanguage {
    private String original_language;

    public MovieOriginalLanguage() {
    }

    public MovieOriginalLanguage(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
}
