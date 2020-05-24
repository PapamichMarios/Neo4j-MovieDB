package com.neo4j.MoviesDB.service;

import com.neo4j.MoviesDB.enums.IndividualRoles;
import com.neo4j.MoviesDB.model.Individual;
import com.neo4j.MoviesDB.model.Language;
import com.neo4j.MoviesDB.repository.MovieRepository;
import com.neo4j.MoviesDB.result.Individual.*;
import com.neo4j.MoviesDB.result.Movie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;


    // Query 1
    public List<MovieBasicInfo> getMoviesAnIndividualWasPartOfCast(String name) {
        return this.movieRepository.getMoviesAnIndividualWasPartOfCast(name);
    }


    // Query 2
    public List<MovieBasicInfo> getMoviesAnIndividualWorkedAt(String name, IndividualRoles job) {
        return this.movieRepository.getMoviesAnIndividualWorkedAt(job.name(), name);
    }


    // Query 3
    public List<MovieCharacter> getCharacterAnActorEmbodied(String name, LocalDate dateFrom, LocalDate dateTo) {
        return this.movieRepository.getCharacterAnActorEmbodied(name, dateFrom, dateTo);
    }


    // Query 4
    public List<MovieOriginalLanguage> getOriginalLanguageOfMoviesIndividualPartOfCast(String name) {
        return this.movieRepository.getOriginalLanguageOfMoviesIndividualPartOfCast(name);
    }

    public List<Language> getLanguageOfMoviesIndividualPartOfCast(String name) {
        return this.movieRepository.getLanguageOfMoviesIndividualPartOfCast(name);
    }


    // Query 5
    public List<DirectorsRuntime> getTopKDirectorsBasedOnAvgRuntime(int k) {
        return this.movieRepository.getTopKDirectorsBasedOnAvgRuntime(k);
    }


    //Query 6
    public List<ProducerBudget> getTopKProducersBasedOnBudget(int k) {
        return this.movieRepository.getTopKProducersBasedOnBudget(k);
    }

    public List<ProducerRevenue> getTopKProducersBasedOnRevenue(int k) {
        return this.movieRepository.getTopKProducersBasedOnRevenue(k);
    }


    // Query 7
    public List<CoActors> getCoActorsInMoreThanOneMovies(int year) {
        return this.movieRepository.getCoActorsInMoreThanOneMovies(year);
    }


    // Query 8
    public List<Individual> getProducersAndDirectorsOfMovieInYear(int year) {
        return this.movieRepository.getProducersAndDirectorsOfMovieInYear(year);
    }


    // Query 9
    public List<Individual> getIndividualWhoActedDirectedWroteAMovieInYear(int year) {
        return this.movieRepository.getIndividualWhoActedDirectedWroteAMovieInYear(year);
    }


    // Query 10
    public List<Individual> getActorCoActingWithGivenActorButNotCoActingWithGiven(String name) {
        return this.movieRepository.getActorCoActingWithGivenActorButNotCoActingWithGiven(name);
    }


    // Query 11
    public List<Individual> getDirectorsAnIndividualHasWorkedWith(String name) {
        return this.movieRepository.getDirectorsAnIndividualHasWorkedWith(name);
    }


    // Query 12
    public List<DirectorsCooperations> getTopKDirectorsBasedOnCooperations(String name, int k) {
        return this.movieRepository.getTopKDirectorsBasedOnCooperations(name, k);
    }


    // Query 13
    public List<IndividualPair> getIndividualsThatDirectedEachOther() {
        return this.movieRepository.getIndividualsThatDirectedEachOther();
    }


    // Query 14
    public List<Individual> getDirectorsOfConsecutivelyReleasedMoviesWithYearsInBetween(int yearsInBetween) {
        return this.movieRepository.getDirectorsOfConsecutivelyReleasedMoviesWithYearsInBetween(yearsInBetween);
    }


    // Query 15
    public List<MovieMostRatings> getMoviesWithMostRatings() {
        return this.movieRepository.getMoviesWithMostRatings();
    }


    // Query 16
    public List<MovieBestAverageRating> getMoviesWithBestAverageRating() {
        return this.movieRepository.getMoviesWithBestAverageRating();
    }

    public List<MovieWorstAverageRating> getMoviesWithWorstAverageRating() {
        return this.movieRepository.getMoviesWithWorstAverageRating();
    }


    // Query 17
    public List<DirectorBestAverageRating> getDirectorWithBestMovieAverageRating() {
        return this.movieRepository.getDirectorWithBestMovieAverageRating();
    }

    public List<DirectorWorstAverageRating> getDirectorWithWorstMovieAverageRating() {
        return this.movieRepository.getDirectorWithWorstMovieAverageRating();
    }


    // Query 18
    public List<CoActorsTotalRatings> getCoActorsBasedOnTotalRatings() {
        return this.movieRepository.getCoActorsBasedOnTotalRatings();
    }
}
