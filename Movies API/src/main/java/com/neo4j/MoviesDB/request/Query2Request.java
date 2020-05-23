package com.neo4j.MoviesDB.request;

import com.neo4j.MoviesDB.enums.IndividualRoles;

public class Query2Request {

    private String actorName;

    private IndividualRoles role;

    public Query2Request() {
    }

    public Query2Request(String actorName, IndividualRoles role) {
        this.actorName = actorName;
        this.role = role;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public IndividualRoles getRole() {
        return role;
    }

    public void setRole(IndividualRoles role) {
        this.role = role;
    }
}
