package com.neo4j.MoviesDB.result.Individual;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class ProducerBudget {

    private String producer;
    private Double budget;

    public ProducerBudget() {
    }

    public ProducerBudget(String producer, Double budget) {
        this.producer = producer;
        this.budget = budget;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
