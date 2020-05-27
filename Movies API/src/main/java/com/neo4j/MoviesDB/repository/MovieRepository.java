package com.neo4j.MoviesDB.repository;

import com.neo4j.MoviesDB.model.Individual;
import com.neo4j.MoviesDB.model.Language;
import com.neo4j.MoviesDB.model.Movie;
import com.neo4j.MoviesDB.result.Individual.*;
import com.neo4j.MoviesDB.result.Movie.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    // 1. Find the movies (title, tagline, release date) that a particular person was part of their cast.
    @Query ("MATCH (i:Individual)-[:PLAYED_IN]->(m:Movie) " +
            "WHERE i.name = $name " +
            "RETURN m.title AS title, m.tagline AS tagline, m.release_date AS release_date")
    List<MovieBasicInfo> getMoviesAnIndividualWasPartOfCast(@Param("name") String name);


    // 2. Find the movies (title, tagline, release date) a particular person directed / wrote / produced.
    @Query ("MATCH (i:Individual)-[rel:WORKED_AT]->(m:Movie) " +
            "WHERE rel.job=$job AND i.name=$name " +
            "RETURN m.title AS title, m.tagline AS tagline, m.release_date AS release_date")
    public List<MovieBasicInfo> getMoviesAnIndividualWorkedAt(@Param("job") String job, @Param("name") String name);


    // 3. What characters has a particular actor embodied between a particular time range of release dates?
    @Query ("MATCH (i:Individual)-[rel:PLAYED_IN]->(m:Movie) " +
            "WHERE i.name=$name AND m.release_date>=date($dateFrom) AND m.release_date<=date($dateTo) " +
            "RETURN rel.character AS character")
    public List<MovieCharacter> getCharacterAnActorEmbodied(@Param("name") String name, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);


    // 4. Find the original / spoken languages of movies that a particular person was part of their cast.
    @Query ("MATCH (i:Individual)-[:PLAYED_IN]->(m:Movie) " +
            "WHERE i.name=$name " +
            "RETURN DISTINCT m.original_language AS original_language;")
    public List<MovieOriginalLanguage> getOriginalLanguageOfMoviesIndividualPartOfCast(@Param("name") String name);

    @Query ("MATCH (i:Individual)-[:PLAYED_IN]->(m:Movie)-[:SPEAKS]->(l:Language) " +
            "WHERE i.name=$name " +
            "RETURN DISTINCT l;")
    public List<Language> getLanguageOfMoviesIndividualPartOfCast(@Param("name") String name);


    // 5. Find the top-K directors with regard to largest average movie runtime.
    @Query ("MATCH (i:Individual)-[rel:WORKED_AT]->(m:Movie) " +
            "WHERE rel.job=\"Director\" AND m.runtime IS NOT NULL " +
            "RETURN i.name AS director, AVG(m.runtime) AS average_runtime " +
            "ORDER BY average_runtime DESC " +
            "LIMIT $k")
    public List<DirectorsRuntime> getTopKDirectorsBasedOnAvgRuntime(@Param("k") int k);


    // 6. Find the top-K producers with regard to largest budget / revenue.
    @Query ("MATCH (i:Individual)-[rel:WORKED_AT]->(m:Movie) " +
            "RETURN i.name AS producer, m.budget AS budget " +
            "ORDER BY budget DESC " +
            "LIMIT $k")
    public List<ProducerBudget> getTopKProducersBasedOnBudget(@Param("k") int k);

    @Query ("MATCH (i:Individual)-[rel:WORKED_AT]->(m:Movie) " +
            "RETURN i.name AS producer, m.revenue AS revenue " +
            "ORDER BY revenue DESC " +
            "LIMIT $k")
    public List<ProducerRevenue> getTopKProducersBasedOnRevenue(@Param("k") int k);


    // 7. Find the actors that have co-acted in more than one movies released in a given single year.
    @Query ("MATCH (actor:Individual)-[:PLAYED_IN]->(m:Movie)<-[:PLAYED_IN]-(coactor:Individual) " +
            "WHERE m.release_date.year = $year AND ID(actor) < ID(coactor) " +
            "WITH actor, coactor , COUNT(DISTINCT m) AS movies_together " +
            "WHERE movies_together > 1 " +
            "RETURN actor, coactor")
    public List<CoActors> getCoActorsInMoreThanOneMovies(@Param("year") int year);


    // 8. Find those that have directed and produced a movie in a particular year.
    @Query ("MATCH (i:Individual)-[producer:WORKED_AT]->(m1:Movie), (i)-[director:WORKED_AT]->(m2:Movie) " +
            "WHERE producer.job=\"Producer\" AND director.job=\"Director\" AND m1.release_date.year = $year = m2.release_date.year " +
            "RETURN DISTINCT i")
    public List<Individual> getProducersAndDirectorsOfMovieInYear(@Param("year") int year);


    // 9. Find those that have acted, directed and written a movie in a particular year.
    @Query ("MATCH (i:Individual)-[producer:WORKED_AT]->(m1:Movie) " +
            "MATCH (i)-[director:WORKED_AT]->(m2:Movie) " +
            "MATCH (i)-[:PLAYED_IN]->(m3:Movie) " +
            "WHERE producer.job=\"Producer\" AND director.job=\"Director\" AND m1.release_date.year = m2.release_date.year = m3.release_date.year = $year " +
            "RETURN DISTINCT i")
    public List<Individual> getIndividualWhoActedDirectedWroteAMovieInYear(@Param("year") int year);


    //10. Find the actors that co-acted with an actor that has acted with a given actor, but have not co-acted with the given actor.
    @Query ("MATCH (actor:Individual)-[:PLAYED_IN]->(:Movie)<-[:PLAYED_IN]-(coactor:Individual) " +
            "MATCH (coactor)-[:PLAYED_IN]->(:Movie)<-[:PLAYED_IN]-(given_actor:Individual) " +
            "WHERE given_actor.name = $name AND NOT (actor)-[:PLAYED_IN]->(:Movie)<-[:PLAYED_IN]-(given_actor) AND actor <> coactor " +
            "RETURN DISTINCT actor")
    public List<Individual> getActorCoActingWithGivenActorButNotCoActingWithGiven(@Param("name") String name);


    // 11. Find the directors that a given actor has worked with.
    @Query ("MATCH (actor:Individual)-[:PLAYED_IN]->(m:Movie)<-[directed:WORKED_AT {job: \"Director\"}]-(director:Individual) " +
            "WHERE actor.name=$name AND actor <> director " +
            "RETURN DISTINCT director")
    public List<Individual> getDirectorsAnIndividualHasWorkedWith(@Param("name") String name);


    // 12. Find the top-K directors that a given actor has not worked with, with regard to most cooperations with actors that the given actor has worked with.
    @Query ("MATCH (actor:Individual)-[:PLAYED_IN]->(:Movie)<-[:PLAYED_IN]-(given_actor) " +
            "MATCH (director)-[:WORKED_AT {job: \"Director\"}]->(:Movie)<-[:PLAYED_IN]-(actor) " +
            "WHERE NOT (director)-[:WORKED_AT {job:\"Director\"}]->(:Movie)<-[:PLAYED_IN]-(given_actor) AND given_actor.name=$name " +
            "RETURN director, COUNT(actor) AS cooperations " +
            "ORDER BY cooperations DESC " +
            "LIMIT $k")
    public List<DirectorsCooperations> getTopKDirectorsBasedOnCooperations(@Param("name") String name, @Param("k") int k);


    // 13. Find the pairs of people that have directed each other in at least one movie.
    @Query ("MATCH (individual1:Individual)-[:PLAYED_IN]->(m1:Movie)<-[:WORKED_AT {job:\"Director\"} ]-(individual2:Individual) " +
            "MATCH (individual2:Individual)-[:WORKED_AT {job:\"Director\"} ]->(m2:Movie)<-[:PLAYED_IN]-(individual1:Individual) " +
            "WHERE ID(individual1) < ID(individual2) " +
            "RETURN individual1, individual2")
    public List<IndividualPair> getIndividualsThatDirectedEachOther();


    // 14. Find the directors of consecutively released movies with more than a given amount of years between them.
    @Query ("MATCH (director:Individual)-[:WORKED_AT { job: \"Director\"}]->(m:Movie) " +
            "WITH m, director " +
            "ORDER BY m.release_date.year " +
            "WITH collect(m.release_date) AS consecutive_movies, director " +
            "UNWIND range (0, size(consecutive_movies)) AS i " +
            "WITH consecutive_movies[i] AS movie1, consecutive_movies[i+1] AS movie2, director " +
            "WHERE duration.between(movie1, movie2).years >= $yearsInBetween " +
            "WITH collect({movie1: movie1, movie2:movie2}) AS movies, director " +
            "WHERE size(movies) > 0 " +
            "RETURN director")
    public List<Individual> getDirectorsOfConsecutivelyReleasedMoviesWithYearsInBetween(@Param("yearsInBetween") int yearsInBetween);


    // 15. Find the movie with the most ratings.

    // Alternative query:
    //	MATCH (m:Movie)-[:HAS_RATING]->(r:Rating)
    //	RETURN m AS movie, COUNT(r) AS most_ratings
    //	ORDER BY most_ratings DESC
    //	LIMIT 1

    @Query ("MATCH (m:Movie)-[:HAS_RATING]->(r:Rating) " +
            "WITH m, COUNT(r) AS total_ratings " +
            "WITH collect({movie: m, total: total_ratings}) AS movies, MAX(total_ratings) AS most_ratings " +
            "UNWIND [movie in movies where movie.total = most_ratings] as m " +
            "RETURN m.movie AS top_movie, most_ratings")
    public List<MovieMostRatings> getMoviesWithMostRatings();


    // 16. Find the movie with the best / worst average rating.

    // a. BEST
    // Alternative query:
    // "MATCH (m:Movie)-[:HAS_RATING]->(r:Rating) " +
    // "RETURN m AS movie, AVG(r.rating) AS average_rating " +
    // "ORDER BY average_rating DESC " +
    // "LIMIT 1"

    @Query ("MATCH (m:Movie)-[:HAS_RATING]->(r:Rating) " +
            "WITH m, AVG(r.rating) AS average_ratings " +
            "WITH collect({movie: m, avg: average_ratings}) AS movies, MAX(average_ratings) AS best_average_rating " +
            "UNWIND [movie IN movies WHERE movie.avg = best_average_rating] AS movie " +
            "RETURN movie.movie AS movie, best_average_rating")
    public List<MovieBestAverageRating> getMoviesWithBestAverageRating();


    // b. WORST
    // Alternative query:
    // MATCH (m:Movie)-[:HAS_RATING]->(r:Rating)
    // RETURN m, AVG(r.rating) AS average_ratings
    // ORDER BY average_ratings ASC
    // LIMIT 1

    @Query ("MATCH (m:Movie)-[:HAS_RATING]->(r:Rating) " +
            "WITH m, AVG(r.rating) AS average_ratings " +
            "WITH collect({movie: m, avg: average_ratings}) AS movies, MIN(average_ratings) AS worst_average_rating " +
            "UNWIND [movie IN movies WHERE movie.avg = worst_average_rating] AS movie " +
            "RETURN movie.movie AS movie, worst_average_rating")
    public List<MovieWorstAverageRating> getMoviesWithWorstAverageRating();


    //17. Find the director with the best / worst average rating.

    // a. BEST
    // Alternative query:
    // MATCH (i:Individual)-[work:WORKED_AT]->(m:Movie)-[:HAS_RATING]->(r:Rating)
    // WHERE work.job="Director"
    // RETURN i AS director, AVG(r.rating) AS average_rating
    // ORDER BY average_rating DESC
    // LIMIT 1

    @Query ("MATCH (i:Individual)-[work:WORKED_AT]->(m:Movie)-[:HAS_RATING]->(r:Rating) " +
            "WHERE work.job=\"Director\" " +
            "WITH i AS director, AVG(r.rating) AS average_rating " +
            "WITH collect({director: director, avg: average_rating}) AS directors, MAX(average_rating) AS best_average_rating " +
            "UNWIND [director IN directors WHERE director.avg = best_average_rating] AS director " +
            "RETURN director.director AS director, best_average_rating")
    public List<DirectorBestAverageRating> getDirectorWithBestMovieAverageRating();

    // b. WORST
    // Alternative query
    // MATCH (i:Individual)-[work:WORKED_AT]->(m:Movie)-[:HAS_RATING]->(r:Rating)
    // WHERE work.job="Director"
    // RETURN i AS director, AVG(r.rating) AS average_rating
    // ORDER BY average_rating ASC
    // LIMIT 1

    @Query("MATCH (i:Individual)-[work:WORKED_AT]->(m:Movie)-[:HAS_RATING]->(r:Rating) " +
            "WHERE work.job=\"Director\" " +
            "WITH i AS director, AVG(r.rating) AS average_rating " +
            "WITH collect({director: director, avg: average_rating}) AS directors, MIN(average_rating) AS worst_average_rating " +
            "UNWIND [director IN directors WHERE director.avg = worst_average_rating] AS director " +
            "RETURN director.director AS director, worst_average_rating")
    public List<DirectorWorstAverageRating> getDirectorWithWorstMovieAverageRating();


    // 18. Find the pair of actors that co-acted in movies with regard to the largest count of user ratings.
    @Query ("MATCH (actor:Individual)-[:PLAYED_IN]->(m:Movie)<-[:PLAYED_IN]-(coactor:Individual), (m)-[:HAS_RATING]->(r:Rating) " +
            "WHERE ID(actor) < ID(coactor) " +
            "RETURN actor, coactor, COUNT(r.rating) AS total_ratings " +
            "ORDER BY total_ratings DESC")
    public List<CoActorsTotalRatings> getCoActorsBasedOnTotalRatings();
}
