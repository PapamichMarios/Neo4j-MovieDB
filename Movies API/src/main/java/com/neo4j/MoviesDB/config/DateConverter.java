package com.neo4j.MoviesDB.config;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter implements AttributeConverter<Date, LocalDate> {

    @Override public LocalDate toGraphProperty(Date value) {
        return LocalDate.from(value.toInstant());
    }

    @Override public Date toEntityAttribute(LocalDate value) {
        return Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
