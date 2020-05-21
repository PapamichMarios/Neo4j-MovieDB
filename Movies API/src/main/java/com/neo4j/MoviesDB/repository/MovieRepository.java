package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

}
