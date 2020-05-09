"""
This program is used for parsing the data of the input .csv files
and constructing in an appropriate way to import to neo4j db

csv files:
    movies_metadata.csv
    credits.csv
    ratings.csv
    links.csv
"""

import ast
import csv

if __name__ == "__main__":
    path = './'
    batch = 500

    with open(path + 'movies_metadata.csv') as movies_metadataCSVFile:
        movies_metadataCSV = csv.DictReader(movies_metadataCSVFile, delimiter=",")

        # movies.csv
        movieCSV = open(path + 'movie.csv', 'w')
        fieldnames = ['adult', 'budget', 'homepage', 'tmdb_id', 'imdb_id', 'original_language', 'original_title',
                      'overview', 'popularity', 'poster_path', 'release_date', 'revenue', 'runtime', 'status',
                      'tagline', 'title', 'video', 'vote_average', 'vote_count']
        movieWriter = csv.DictWriter(movieCSV, fieldnames=fieldnames)
        movieWriter.writeheader()

        # movie_collection.csv
        movie_collectionCSV = open(path + 'movie_collection.csv', 'w')
        fieldnames = ['movie_id', 'collection_id']
        movie_collectionWriter = csv.DictWriter(movie_collectionCSV, fieldnames=fieldnames)
        movie_collectionWriter.writeheader()

        # collection.csv
        collectionCSV = open(path + 'collection.csv', 'w')
        fieldnames = ['id', 'name', 'poster_path', 'backdrop_path']
        collectionWriter = csv.DictWriter(collectionCSV, fieldnames=fieldnames)
        collectionWriter.writeheader()

        # genre.csv
        genreCSV = open(path + 'genre.csv', 'w')
        fieldnames = ['name', 'id']
        genreWriter = csv.DictWriter(genreCSV, fieldnames=fieldnames)
        genreWriter.writeheader()

        # movie_genre.csv
        movie_genreCSV = open(path + 'movie_genre.csv', 'w')
        fieldnames = ['movie_id', 'genre_id']
        movie_genreWriter = csv.DictWriter(movie_genreCSV, fieldnames=fieldnames)
        movie_genreWriter.writeheader()

        # production_company.csv
        companyCSV = open(path + 'production_company.csv', 'w')
        fieldnames = ['id', 'name']
        companyWriter = csv.DictWriter(companyCSV, fieldnames=fieldnames)
        companyWriter.writeheader()

        # movie_company.csv
        movie_companyCSV = open(path + 'movie_company.csv', 'w')
        fieldnames = ['movie_id', 'company_id']
        movie_companyWriter = csv.DictWriter(movie_companyCSV, fieldnames=fieldnames)
        movie_companyWriter.writeheader()

        # production_country.csv
        countryCSV = open(path + 'production_country.csv', 'w')
        fieldnames = ['iso_3166_1', 'name']
        countryWriter = csv.DictWriter(countryCSV, fieldnames=fieldnames)
        countryWriter.writeheader()

        # movie_country.csv
        movie_countryCSV = open(path + 'movie_country.csv', 'w')
        fieldnames = ['movie_id', 'country_id']
        movie_countryWriter = csv.DictWriter(movie_countryCSV, fieldnames=fieldnames)
        movie_countryWriter.writeheader()

        # spoken_language.csv
        languageCSV = open(path + 'spoken_language.csv', 'w')
        fieldnames = ['iso_639_1', 'name']
        languageWriter = csv.DictWriter(languageCSV, fieldnames=fieldnames)
        languageWriter.writeheader()

        # movie_language.csv
        movie_languageCSV = open(path + 'movie_language.csv', 'w')
        fieldnames = ['movie_id', 'language_id']
        movie_languageWriter = csv.DictWriter(movie_languageCSV, fieldnames=fieldnames)
        movie_languageWriter.writeheader()

        index = 0
        movie_data = []
        movie_collection_data = []
        movie_genre_data = []
        movie_company_data = []
        movie_country_data = []
        movie_language_data = []

        collection_data = []
        genre_data = []
        company_data = []
        country_data = []
        language_data = []

        for row in movies_metadataCSV:

            index += 1

            # movie
            movie = {'adult': row['adult'], 'budget': row['budget'], 'homepage': row['homepage'], 'tmdb_id': row['id'],
                     'imdb_id': row['imdb_id'], 'original_language': row['original_language'], 'original_title': row['original_title'],
                     'overview': row['overview'], 'popularity': row['popularity'], 'poster_path': row['poster_path'],
                     'release_date': row['release_date'], 'revenue': row['revenue'], 'runtime': row['runtime'],
                     'status': row['status'], 'tagline': row['tagline'], 'title': row['title'], 'video': row['video'],
                     'vote_average': row['vote_average'], 'vote_count': row['vote_count']}
            movie_data.append(movie)

            # collection
            if row['belongs_to_collection']:
                collection = ast.literal_eval(row['belongs_to_collection'])
                collection_data.append(collection)

                movie_collection = {
                    'movie_id': movie['tmdb_id'],
                    'collection_id': collection['id']
                }
                movie_collection_data.append(movie_collection)

            # genre & movie_genre
            genres = ast.literal_eval(row['genres'])
            for genre in genres:
                genre_data.append(genre)

                movie_genre = {
                    'movie_id': movie['tmdb_id'],
                    'genre_id': genre['id']
                }
                movie_genre_data.append(movie_genre)

            # production_company.csv
            if row['production_companies']:
                companies = ast.literal_eval(row['production_companies'])
                for company in companies:
                    company_data.append(company)

                    movie_company = {
                        'movie_id': movie['tmdb_id'],
                        'company_id': company['id']
                    }
                    movie_company_data.append(movie_company)

            # production_country.csv
            if row['production_countries']:
                countries = ast.literal_eval(row['production_countries'])
                for country in countries:
                    country_data.append(country)

                    movie_country = {
                        'movie_id': movie['tmdb_id'],
                        'country_id': country['iso_3166_1']
                    }
                    movie_country_data.append(movie_country)

            # spoken_language.csv
            if row['spoken_languages']:
                languages = ast.literal_eval(row['spoken_languages'])
                for language in languages:
                    language_data.append(language)

                    movie_language = {
                        'movie_id': movie['tmdb_id'],
                        'language_id': language['iso_639_1']
                    }
                    movie_language_data.append(movie_language)

            if index == batch:
                movieWriter.writerows(movie_data)
                movie_collectionWriter.writerows(movie_collection_data)
                movie_genreWriter.writerows(movie_genre_data)
                movie_companyWriter.writerows(movie_company_data)
                movie_countryWriter.writerows(movie_country_data)
                movie_languageWriter.writerows(movie_language_data)

                index = 0
                movie_data = []
                movie_collection_data = []
                movie_genre_data = []
                movie_company_data = []
                movie_country_data = []
                movie_language_data = []

        movieWriter.writerows(movie_data)
        movie_collectionWriter.writerows(movie_collection_data)
        movie_genreWriter.writerows(movie_genre_data)
        movie_companyWriter.writerows(movie_company_data)
        movie_countryWriter.writerows(movie_country_data)
        movie_languageWriter.writerows(movie_language_data)

        collections = list({x['id']: x for x in collection_data}.values())
        collectionWriter.writerows(collections)

        genres = list({x['id']: x for x in genre_data}.values())
        genreWriter.writerows(genres)

        companies = list({x['id']: x for x in company_data}.values())
        companyWriter.writerows(companies)

        countries = list({x['iso_3166_1']: x for x in country_data}.values())
        countryWriter.writerows(countries)

        languages = list({x['iso_639_1']: x for x in language_data}.values())
        languageWriter.writerows(language_data)

        movieCSV.close()
        collectionCSV.close()
        movie_collectionCSV.close()
        genreCSV.close()
        movie_genreCSV.close()
        companyCSV.close()
        movie_companyCSV.close()
        countryCSV.close()
        movie_countryCSV.close()
        languageCSV.close()
        movie_languageCSV.close()

    with open(path + 'credits.csv') as creditsCSVFile:
        creditsCSV = csv.DictReader(creditsCSVFile, delimiter=",")

        # cast.csv
        castCSV = open(path + 'cast.csv', 'w')
        fieldnames = ['cast_id', 'character', 'credit_id', 'gender', 'id', 'name', 'order', 'profile_path']
        castWriter = csv.DictWriter(castCSV, fieldnames=fieldnames)

        # movie_cast.csv
        movie_castCSV = open(path + 'movie_cast.csv', 'w')
        fieldnames = ['movie_id', 'cast_id']
        movie_castWriter = csv.DictWriter(movie_castCSV, fieldnames=fieldnames)

        # crew.csv
        crewCSV = open(path + 'crew.csv', 'w')
        fieldnames = ['credit_id', 'department', 'gender', 'id', 'job', 'name', 'profile_path']
        crewWriter = csv.DictWriter(crewCSV, fieldnames=fieldnames)

        # movie_crew.csv
        movie_crewCSV = open(path + 'movie_crew.csv', 'w')
        fieldnames = ['movie_id', 'crew_id']
        movie_crewWriter = csv.DictWriter(movie_crewCSV, fieldnames=fieldnames)

        index = 0
        cast_data = []
        crew_data = []

        movie_cast_data = []
        movie_crew_data = []

        for row in creditsCSV:

            index += 1

            # casts
            casts = ast.literal_eval(row["cast"])
            for cast in casts:
                cast_data.append(cast)

                movie_cast = {
                    'movie_id': movie['tmdb_id'],
                    'cast_id': cast['credit_id']
                }
                movie_cast_data.append(movie_cast)

            # crews
            crews = ast.literal_eval(row["crew"])
            for crew in crews:
                crew_data.append(crew)

                movie_crew = {
                    'movie_id': movie['tmdb_id'],
                    'crew_id': crew['credit_id']
                }
                movie_crew_data.append(movie_crew)

            if index == batch:
                movie_castWriter.writerows(movie_cast_data)
                movie_crewWriter.writerows(movie_crew_data)

                index = 0
                movie_cast_data = []
                movie_crew_data = []

        movie_castWriter.writerows(movie_cast_data)
        movie_crewWriter.writerows(movie_crew_data)

        crews = list({x['credit_id']: x for x in crew_data}.values())
        crewWriter.writerows(crews)

        casts = list({x['credit_id']: x for x in cast_data}.values())
        castWriter.writerows(casts)

        castCSV.close()
        crewCSV.close()
        movie_castCSV.close()
        movie_crewCSV.close()
