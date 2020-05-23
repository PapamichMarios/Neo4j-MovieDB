package com.neo4j.MoviesDB.controller;

import com.neo4j.MoviesDB.model.Individual;
import com.neo4j.MoviesDB.model.Movie;
import com.neo4j.MoviesDB.result.MovieBasicInfo;
import com.neo4j.MoviesDB.service.IndividualService;
import com.neo4j.MoviesDB.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/query1")
    public ResponseEntity<?> getMoviesAnIndividualWasPartOfCast(@RequestParam String actorName) {
        List<MovieBasicInfo> movies = this.movieService.getMoviesAnIndividualWasPartOfCast(actorName);

        return ResponseEntity.ok(movies);
    }

}
