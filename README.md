# Neo4j-MovieDB

# Problematic movie ids

- some lines had NULL bytes
- missing quotes on overview:
    - line: 19765-19766, id: 82663 
    - line: 29574-29575, id: 122662 
    - line: 35673-35674, id: 249260

# Populating DB

- Copy & paste the resulting .csv files from parser, to the neo4j import folder
- Run the following script

- Set constraints on label keys
```
// Set constraints on label keys
CREATE CONSTRAINT ON (m:Movie) ASSERT m.tmdb_id IS UNIQUE;
CREATE CONSTRAINT ON (c:Company) ASSERT c.id IS UNIQUE;
CREATE CONSTRAINT ON (c:Country) ASSERT c.code IS UNIQUE;
CREATE CONSTRAINT ON (l:Language) ASSERT l.code IS UNIQUE;
CREATE CONSTRAINT ON (i:Individual) ASSERT i.id IS UNIQUE;
CREATE CONSTRAINT ON (c:Collection) ASSERT c.id IS UNIQUE;
CREATE CONSTRAINT ON (g:Genre) ASSERT g.id IS UNIQUE;
CREATE CONSTRAINT ON (r:Rating) ASSERT (r.user_id, r.movie_id) IS NODE KEY;

CREATE INDEX ON :Rating(movie_id);
```

- Populate Labels
```
:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie.csv' AS row
CREATE 
(m:Movie { 
    adult: row.adult, 
    budget: toFloat(row.budget), 
    homepage: row.homepage, 
    tmdb_id: toInteger(row.tmdb_id), 
    imdb_id: row.imdb_id, 
    original_language: row.original_language, 
    original_title: row.original_title,
    overview: row.overview,
    popularity: toFloat(row.popularity),
    poster_path: row.poster_path,
    release_date: date(row.release_date),
    revenue: toFloat(row.revenue),
    runtime: toFloat(row.runtime), 
    status: row.status, 
    tagline: row.tagline,
    title: row.title, 
    video: row.video, 
    vote_average: toFloat(row.vote_average), 
    vote_count: toInteger(row.vote_count)
})
RETURN count(m);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///genre.csv' AS row
CREATE 
(g:Genre { 
	id: toInteger(row.id),
    name: row.name
})
RETURN count(g);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///collection.csv' AS row
CREATE 
(c:Collection { 
	id: toInteger(row.id),
    name: row.name,
    poster_path: row.poster_path,
    backdrop_path: row.backdrop_path
})
RETURN count(c);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///individual.csv' AS row
CREATE 
(i:Individual { 
    id: toInteger(row.id),
    gender: toInteger(row.gender),
    name: row.name,
    profile_path: row.profile_path
})
RETURN count(i);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///production_company.csv' AS row
CREATE 
(c:Company { 
    id: toInteger(row.id),
    name: row.name
})
RETURN count(c);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///production_country.csv' AS row
CREATE 
(c:Country { 
    code: row.iso_3166_1,
    name: row.name
})
RETURN count(c);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///spoken_language.csv' AS row
CREATE 
(l:Language { 
    code: row.iso_639_1,
    name: row.name
})
RETURN count(l);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///rating.csv' AS row
CREATE 
(r:Rating { 
    user_id: toInteger(row.user_id),
    movie_id: toInteger(row.movie_id),
    rating: toFloat(row.rating),
    timestamp: row.timestamp
})
RETURN count(r);
```

- Relationships
```
:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_cast.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (individual:Individual {id: toInteger(row.cast_id)})
CREATE (individual)-[:PLAYED_IN {character: row.character, credit_id: row.credit_id} ]->(movie);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_crew.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (individual:Individual {id: toInteger(row.crew_id)})
CREATE (individual)-[:WORKED_AT {department: row.department, job: row.job, credit_id: row.credit_id} ]->(movie);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_collection.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (collection:Collection {id: toInteger(row.collection_id)})
CREATE (movie)-[:BELONGS_TO]->(collection);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_genre.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (genre:Genre {id: toInteger(row.genre_id)})
CREATE (movie)-[:BELONGS_TO]->(genre);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_company.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (company:Company {id: toInteger(row.company_id)})
CREATE (movie)-[:PRODUCED_BY]->(company);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_country.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (country:Country {code: row.country_id})
CREATE (movie)-[:PRODUCED_BY]->(country);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_language.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.movie_id)}), (language:Language {code: row.language_id})
CREATE (movie)-[:SPEAKS]->(language);

:auto USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM 'file:///movie_rating.csv' AS row
MATCH (movie:Movie {tmdb_id: toInteger(row.tmdb_id)}), (rating:Rating {movie_id: toInteger(row.movie_id)})
CREATE (movie)-[:HAS_RATING]->(rating);
```
