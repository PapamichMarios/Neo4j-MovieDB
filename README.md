# Neo4j-MovieDB

# Problematic movie ids

- some lines had NULL bytes
- missing quotes on overview:
    - line: 19765-19766, id: 82663 
    - line: 29574-29575, id: 122662 
    - line: 35673-35674, id: 249260

# Populating DB

```
CREATE CONSTRAINT ON (m:Movie) ASSERT m.tmdb_id IS UNIQUE;
CREATE CONSTRAINT ON (c:Company) ASSERT c.id IS UNIQUE;
CREATE CONSTRAINT ON (c:Country) ASSERT c.code IS UNIQUE;
CREATE CONSTRAINT ON (l:Language) ASSERT l.code IS UNIQUE;
CREATE CONSTRAINT ON (c:Cast) ASSERT c.credit_id IS UNIQUE;
CREATE CONSTRAINT ON (c:Crew) ASSERT c.credit_id IS UNIQUE;
CREATE CONSTRAINT ON (c:Collection) ASSERT c.id IS UNIQUE;
CREATE CONSTRAINT ON (g:Genre) ASSERT g.id IS UNIQUE;
CREATE CONSTRAINT ON (r:Rating) ASSERT r.movie_id IS UNIQUE;

LOAD CSV WITH HEADERS FROM 'file:///movie.csv' AS row
CREATE 
(m:Movie { 
    adult: row.adult, 
    budget: toInteger(row.budget), 
    homepage: row.homepage, 
    tmdb_id: toInteger(row.tmdb_id), 
    imdb_id: row.imdb_id, 
    original_language: row.original_language, 
    original_title: row.original_title,
    overview: row.overview,
    popularity: toFloat(row.popularity),
    poster_path: row.poster_path,
    release_date: row.release_date,
    revenue: toInteger(row.revenue),
    runtime: toFloat(row.runtime), 
    status: row.status, 
    tagline: row.tagline,
    title: row.title, 
    video: row.video, 
    vote_average: toFloat(row.vote_average), 
    vote_count: toInteger(row.vote_count)
})
RETURN count(m);

LOAD CSV WITH HEADERS FROM 'file:///genre.csv' AS row
CREATE 
(g:Genre { 
	id: toInteger(row.id),
    name: row.name
})
RETURN count(g);

LOAD CSV WITH HEADERS FROM 'file:///collection.csv' AS row
CREATE 
(c:Collection { 
	id: toInteger(row.id),
    name: row.name,
    poster_path: row.poster_path,
    backdrop_path: row.backdrop_path
})
RETURN count(c);

LOAD CSV WITH HEADERS FROM 'file:///cast.csv' AS row
CREATE 
(c:Cast { 
    credit_it: row.credit_it,
    cast_id: toInteger(row.cast_id),
    character: row.character,
    gender: toInteger(row.gender),
    id: toInteger(row.id),
    name: row.name,
    order: toInteger(row.order),
    profile_path: row.profile_path
})
RETURN count(c);

LOAD CSV WITH HEADERS FROM 'file:///crew.csv' AS row
CREATE 
(c:Crew { 
    credit_it: row.credit_it,
    department: row.department,
    gender: toInteger(row.gender),
    id: toInteger(row.id),
    job: row.job,
    name: row.name,
    profile_path: row.profile_path
})
RETURN count(c);

LOAD CSV WITH HEADERS FROM 'file:///production_company.csv' AS row
CREATE 
(c:Company { 
    id: toInteger(row.id),
    name: row.name
})
RETURN count(c);

LOAD CSV WITH HEADERS FROM 'file:///production_country.csv' AS row
CREATE 
(c:Country { 
    code: row.iso_3166_1,
    name: row.name
})
RETURN count(c);

LOAD CSV WITH HEADERS FROM 'file:///spoken_language.csv' AS row
CREATE 
(l:Language { 
    code: row.iso_639_1,
    name: row.name
})
RETURN count(l);

```
