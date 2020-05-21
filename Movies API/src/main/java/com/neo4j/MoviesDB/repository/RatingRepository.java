package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Rating;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RatingRepository extends Neo4jRepository<Rating, Long> {
}
