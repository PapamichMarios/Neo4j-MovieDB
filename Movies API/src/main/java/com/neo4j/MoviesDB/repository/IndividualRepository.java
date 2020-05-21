package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IndividualRepository extends Neo4jRepository<Individual, Long> {
}
