package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Genre;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GenreRepository extends Neo4jRepository<Genre, Long> {
}
