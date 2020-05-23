package com.neo4j.MoviesDB.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neo4j.MoviesDB.config.DateConverter;
import com.neo4j.MoviesDB.config.LocalDateConverter;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String adult;

    private Double budget;

    @Property(name = "imdb_id")
    private String imdbId;

    @Property(name = "original_language")
    private String originalLanguage;

    @Property(name = "original_title")
    private String originalTitle;

    private String overview;

    private Double popularity;

    @Property(name = "poster_path")
    private String posterPath;

    @Property(name = "release_date")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Convert(LocalDateConverter.class)
    private LocalDate releaseDate;

    private Double revenue;

    private Double runtime;

    private String status;

    private String tagline;

    private String title;

    @Property(name = "tmdb_id")
    private Long tmdbId;

    private String video;

    @Property(name = "vote_average")
    private Double voteAverage;

    @Property(name = "vote_count")
    private Double voteCount;

    @JsonIgnoreProperties("movies")
    @Relationship(type = "BELONGS_TO")
    private Set<Collection> collections = new HashSet<>();

    @JsonIgnoreProperties("movies")
    @Relationship(type = "PRODUCED_BY")
    private Set<Company> companies = new HashSet<>();

    @JsonIgnoreProperties("movies")
    @Relationship(type = "PRODUCED_BY")
    private Set<Country> countries = new HashSet<>();

    @JsonIgnoreProperties("movies")
    @Relationship(type = "BELONGS_TO")
    private Set<Genre> genres = new HashSet<>();

    @JsonIgnoreProperties("movie")
    @Relationship(type = "PLAYED_IN", direction = INCOMING)
    private Set<Crew> crew = new HashSet<>();

    @JsonIgnoreProperties("movie")
    @Relationship(type = "WORKED_AT", direction = INCOMING)
    private Set<Cast> cast = new HashSet<>();

    @JsonIgnoreProperties("movies")
    @Relationship(type = "SPEAKS")
    private Set<Language> spokenLanguages = new HashSet<>();

    @JsonIgnoreProperties("movies")
    @Relationship(type = "HAS_RATING")
    private Set<Rating> ratings = new HashSet<>();

    public Movie() {
    }

    public Movie(String tagline, String title) {
        this.tagline = tagline;
        this.title = title;
    }

    public Movie(Long id, String adult, Double budget, String imdbId, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, LocalDate releaseDate, Double revenue, Double runtime, String status, String tagline, String title, Long tmdbId, String video, Double voteAverage, Double voteCount, Set<Collection> collections, Set<Company> companies, Set<Country> countries, Set<Genre> genres, Set<Crew> crew, Set<Cast> cast, Set<Language> spokenLanguages, Set<Rating> ratings) {
        this.id = id;
        this.adult = adult;
        this.budget = budget;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.tmdbId = tmdbId;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.collections = collections;
        this.companies = companies;
        this.countries = countries;
        this.genres = genres;
        this.crew = crew;
        this.cast = cast;
        this.spokenLanguages = spokenLanguages;
        this.ratings = ratings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getRuntime() {
        return runtime;
    }

    public void setRuntime(Double runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Double voteCount) {
        this.voteCount = voteCount;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Crew> getCrew() {
        return crew;
    }

    public void setCrew(Set<Crew> crew) {
        this.crew = crew;
    }

    public Set<Cast> getCast() {
        return cast;
    }

    public void setCast(Set<Cast> cast) {
        this.cast = cast;
    }

    public Set<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(Set<Language> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
