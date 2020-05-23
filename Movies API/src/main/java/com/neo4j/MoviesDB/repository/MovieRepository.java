package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Movie;
import com.neo4j.MoviesDB.result.MovieBasicInfo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    // 1. Find the movies (title, tagline, release date) that a particular person was part of their cast.
    @Query("MATCH (i:Individual)-[:PLAYED_IN]->(m:Movie) " +
            "WHERE i.name = $actorName " +
            "RETURN m.title AS title, m.tagline AS tagline, m.release_date AS release_date")
    List<MovieBasicInfo> getMoviesAnIndividualWasPartOfCast(@Param("actorName") String actorName);
}
