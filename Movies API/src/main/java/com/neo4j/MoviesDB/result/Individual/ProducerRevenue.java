package com.neo4j.MoviesDB.result.Individual;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class ProducerRevenue {

    private String producer;
    private Double revenue;

    public ProducerRevenue() {
    }

    public ProducerRevenue(String producer, Double revenue) {
        this.producer = producer;
        this.revenue = revenue;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
