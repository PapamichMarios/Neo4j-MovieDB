package com.neo4j.MoviesDB.controller;

import com.neo4j.MoviesDB.enums.IndividualRoles;
import com.neo4j.MoviesDB.model.Individual;
import com.neo4j.MoviesDB.model.Language;
import com.neo4j.MoviesDB.result.Individual.*;
import com.neo4j.MoviesDB.result.Movie.*;
import com.neo4j.MoviesDB.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;


    // 1. Find the movies (title, tagline, release date) that a particular person was part of their cast.
    @GetMapping("/query1")
    public ResponseEntity<?> getMoviesAnIndividualWasPartOfCast(@RequestParam String name) {

        List<MovieBasicInfo> movies = this.movieService.getMoviesAnIndividualWasPartOfCast(name);
        return ResponseEntity.ok(movies);
    }


    // 2. Find the movies (title, tagline, release date) a particular person directed / wrote / produced.
    @GetMapping("/query2")
    public ResponseEntity<?> getMoviesAnIndividualWasPartOfCast(@RequestParam String name, @RequestParam IndividualRoles job) {

        List<MovieBasicInfo> movies = this.movieService.getMoviesAnIndividualWorkedAt(name, job);
        return ResponseEntity.ok(movies);
    }


    // 3. What characters has a particular actor embodied between a particular time range of release dates?
    @GetMapping("/query3")
    public ResponseEntity<?> getCharacterAnActorEmbodied(@RequestParam String name,
                                                         @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateFrom,
                                                         @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateTo) {

        List<MovieCharacter> characters = this.movieService.getCharacterAnActorEmbodied(name, dateFrom, dateTo);
        return ResponseEntity.ok(characters);
    }


    // 4. Find the original / spoken languages of movies that a particular person was part of their cast.
    // Original Language
    @GetMapping("/query4/original")
    public ResponseEntity<?> getOriginalLanguageOfMoviesIndividualPartOfCast(@RequestParam String name) {

        List<MovieOriginalLanguage> languages = this.movieService.getOriginalLanguageOfMoviesIndividualPartOfCast(name);
        return ResponseEntity.ok(languages);
    }

    // Spoken Language
    @GetMapping("/query4/spoken")
    public ResponseEntity<?> getLanguageOfMoviesIndividualPartOfCast(@RequestParam String name) {

        List<Language> languages = this.movieService.getLanguageOfMoviesIndividualPartOfCast(name);
        return ResponseEntity.ok(languages);
    }


    // 5. Find the top-K directors with regard to largest average movie runtime.
    @GetMapping("/query5")
    public ResponseEntity<?> getTopKDirectorsBasedOnAvgRuntime(@RequestParam int k) {

        List<DirectorsRuntime> directors = this.movieService.getTopKDirectorsBasedOnAvgRuntime(k);
        return ResponseEntity.ok(directors);
    }


    // 6. Find the top-K producers with regard to largest budget / revenue.
    @GetMapping("/query6/budget")
    public ResponseEntity<?> getTopKProducersBasedOnBudget(@RequestParam int k) {

        List<ProducerBudget> producersBudget = this.movieService.getTopKProducersBasedOnBudget(k);
        return ResponseEntity.ok(producersBudget);
    }

    // Revenue
    @GetMapping("/query6/revenue")
    public ResponseEntity<?> getTopKProducersBasedOnRevenue(@RequestParam int k) {

        List<ProducerRevenue> producersRevenue = this.movieService.getTopKProducersBasedOnRevenue(k);
        return ResponseEntity.ok(producersRevenue);
    }


    // 7. Find the actors that have co-acted in more than one movies released in a given single year.
    @GetMapping("/query7")
    public ResponseEntity<?> getCoActorsInMoreThanOneMovies(@RequestParam int year) {

        List<CoActors> coactors = this.movieService.getCoActorsInMoreThanOneMovies(year);
        return ResponseEntity.ok(coactors);
    }


    // 8. Find those that have directed and produced a movie in a particular year.
    @GetMapping("/query8")
    public ResponseEntity<?> getProducersAndDirectorsOfMovieInYear(@RequestParam int year) {

        List<Individual> individuals = this.movieService.getProducersAndDirectorsOfMovieInYear(year);
        return ResponseEntity.ok(individuals);
    }


    // 9. Find those that have acted, directed and written a movie in a particular year.
    @GetMapping("/query9")
    public ResponseEntity<?> getIndividualWhoActedDirectedWroteAMovieInYear(@RequestParam int year) {

        List<Individual> individuals = this.movieService.getIndividualWhoActedDirectedWroteAMovieInYear(year);
        return ResponseEntity.ok(individuals);
    }


    // 10. Find the actors that co-acted with an actor that has acted with a given actor, but have not co-acted with the given actor.
    @GetMapping("/query10")
    public ResponseEntity<?> getActorCoActingWithGivenActorButNotCoActingWithGiven(@RequestParam String name) {

        List<Individual> actors = this.movieService.getActorCoActingWithGivenActorButNotCoActingWithGiven(name);
        return ResponseEntity.ok(actors);
    }


    // 11. Find the directors that a given actor has worked with.
    @GetMapping("/query11")
    public ResponseEntity<?> getDirectorsAnIndividualHasWorkedWith(@RequestParam String name) {

        List<Individual> directors = this.movieService.getDirectorsAnIndividualHasWorkedWith(name);
        return ResponseEntity.ok(directors);
    }


    // 12. Find the top-K directors that a given actor has not worked with, with regard to most cooperations with actors that the given actor has worked with.
    @GetMapping("/query12")
    public ResponseEntity<?> getTopKDirectorsBasedOnCooperations(@RequestParam String name, @RequestParam int k) {

        List<DirectorsCooperations> directorsCooperations = this.movieService.getTopKDirectorsBasedOnCooperations(name, k);
        return ResponseEntity.ok(directorsCooperations);
    }


    // 13. Find the pairs of people that have directed each other in at least one movie.
    @GetMapping("/query13")
    public ResponseEntity<?> getIndividualsThatDirectedEachOther() {

        List<IndividualPair> individuals = this.movieService.getIndividualsThatDirectedEachOther();
        return ResponseEntity.ok(individuals);
    }


    // 14. Find the directors of consecutively released movies with more than a given amount of years between them.
    @GetMapping("/query14")
    public ResponseEntity<?> getDirectorsOfConsecutivelyReleasedMoviesWithYearsInBetween(@RequestParam int yearsInBetween) {

        List<Individual> directors = this.movieService.getDirectorsOfConsecutivelyReleasedMoviesWithYearsInBetween(yearsInBetween);
        return ResponseEntity.ok(directors);
    }


    // 15. Find the movie with the most ratings.
    @GetMapping("/query15")
    public ResponseEntity<?> getMoviesWithMostRatings() {

        List<MovieMostRatings> moviesMostRatings = this.movieService.getMoviesWithMostRatings();
        return ResponseEntity.ok(moviesMostRatings);
    }


    // 16. Find the movie with the best / worst average rating.
    // Best Average Rating
    @GetMapping("/query16/best")
    public ResponseEntity<?> getMoviesWithBestAverageRating() {

        List<MovieBestAverageRating> movieBestAverageRatings = this.movieService.getMoviesWithBestAverageRating();
        return ResponseEntity.ok(movieBestAverageRatings);
    }

    // Worst Average Rating
    @GetMapping("/query16/worst")
    public ResponseEntity<?> getMoviesWithWorstAverageRating() {

        List<MovieWorstAverageRating> movieWorstAverageRatings = this.movieService.getMoviesWithWorstAverageRating();
        return ResponseEntity.ok(movieWorstAverageRatings);
    }


    // 17. Find the director with the best / worst average rating.
    // Best Average Rating
    @GetMapping("/query17/best")
    public ResponseEntity<?> getDirectorWithBestMovieAverageRating() {

        List<DirectorBestAverageRating> directorBestAverageRatings = this.movieService.getDirectorWithBestMovieAverageRating();
        return ResponseEntity.ok(directorBestAverageRatings);
    }

    // Worst Average Rating
    @GetMapping("/query17/worst")
    public ResponseEntity<?> getDirectorWithWorstMovieAverageRating() {

        List<DirectorWorstAverageRating> directorWorstAverageRatings = this.movieService.getDirectorWithWorstMovieAverageRating();
        return ResponseEntity.ok(directorWorstAverageRatings);
    }


    // 18. Find the pair of actors that co-acted in movies with regard to the largest count of user ratings.
    @GetMapping("/query18")
    public ResponseEntity<?> getCoActorsBasedOnTotalRatings() {

        List<CoActorsTotalRatings> coActorsBasedOnTotalRatings = this.movieService.getCoActorsBasedOnTotalRatings();
        return ResponseEntity.ok(coActorsBasedOnTotalRatings);
    }
}
