package com.neo4j.MoviesDB.service;

import com.neo4j.MoviesDB.model.Movie;
import com.neo4j.MoviesDB.repository.MovieRepository;
import com.neo4j.MoviesDB.result.MovieBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieBasicInfo> getMoviesAnIndividualWasPartOfCast(String actorName) {
        return this.movieRepository.getMoviesAnIndividualWasPartOfCast(actorName);
    }
}
