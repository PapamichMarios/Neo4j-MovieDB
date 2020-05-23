package com.neo4j.MoviesDB.service;

import com.neo4j.MoviesDB.model.Individual;
import com.neo4j.MoviesDB.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class IndividualService {

    @Autowired
    private IndividualRepository individualRepository;

    public Individual getUserByName(String name) {
        return this.individualRepository.findByName(name);
    }
}
