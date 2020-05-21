package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Collection;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CollectionRepository extends Neo4jRepository<Collection, Long> {

}
