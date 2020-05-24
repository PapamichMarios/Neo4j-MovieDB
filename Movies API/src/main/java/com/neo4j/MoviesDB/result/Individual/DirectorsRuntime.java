package com.neo4j.MoviesDB.result.Individual;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class DirectorsRuntime {

    private String director;
    private Double average_runtime;

    public DirectorsRuntime() {
    }

    public DirectorsRuntime(String director, Double average_runtime) {
        this.director = director;
        this.average_runtime = average_runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getAverage_runtime() {
        return average_runtime;
    }

    public void setAverage_runtime(Double average_runtime) {
        this.average_runtime = average_runtime;
    }
}
