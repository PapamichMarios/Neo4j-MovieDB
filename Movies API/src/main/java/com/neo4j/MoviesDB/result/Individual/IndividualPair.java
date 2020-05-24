package com.neo4j.MoviesDB.result.Individual;

import com.neo4j.MoviesDB.model.Individual;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class IndividualPair {

    private Individual individual1;
    private Individual individual2;

    public IndividualPair() {
    }

    public IndividualPair(Individual individual1, Individual individual2) {
        this.individual1 = individual1;
        this.individual2 = individual2;
    }

    public Individual getIndividual1() {
        return individual1;
    }

    public void setIndividual1(Individual individual1) {
        this.individual1 = individual1;
    }

    public Individual getIndividual2() {
        return individual2;
    }

    public void setIndividual2(Individual individual2) {
        this.individual2 = individual2;
    }
}
