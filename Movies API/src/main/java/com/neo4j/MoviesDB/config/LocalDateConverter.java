package com.neo4j.MoviesDB.config;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.time.LocalDate;

public class LocalDateConverter implements AttributeConverter<LocalDate, LocalDate> {

    @Override public LocalDate toGraphProperty(LocalDate value) {
        return value;
    }

    @Override public LocalDate toEntityAttribute(LocalDate value) {
        return value;
    }
}
