package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {
}
