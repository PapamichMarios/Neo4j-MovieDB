package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Language;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LanguageRepository extends Neo4jRepository<Language, Long> {
}
