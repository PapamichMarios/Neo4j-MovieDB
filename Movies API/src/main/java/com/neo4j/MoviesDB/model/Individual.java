package com.neo4j.MoviesDB.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Individual {

    @Id
    @GeneratedValue
    private Long id;

    private Integer gender;

    @Property(name = "person_id")
    private Long personId;

    private String name;

    @JsonIgnoreProperties("individual")
    @Relationship(type = "PLAYED_IN")
    private Set<Cast> playedIn = new HashSet<>();

    @JsonIgnoreProperties("individual")
    @Relationship(type = "WORKED_AT")
    private Set<Cast> workedAt = new HashSet<>();

    public Individual() {
    }

    public Individual(Long id, Integer gender, Long personId, String name) {
        this.id = id;
        this.gender = gender;
        this.personId = personId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getPerson_id() {
        return personId;
    }

    public void setPerson_id(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
