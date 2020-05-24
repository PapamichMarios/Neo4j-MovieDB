package com.neo4j.MoviesDB.result.Movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo4j.MoviesDB.config.LocalDateConverter;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.annotation.typeconversion.DateString;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@QueryResult
public class MovieBasicInfo {
    private String title;
    private String tagline;

    @Convert(LocalDateConverter.class)
    private LocalDate release_date;

    public MovieBasicInfo() {
    }

    public MovieBasicInfo(String title, String tagline, LocalDate release_date) {
        this.title = title;
        this.tagline = tagline;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }
}
