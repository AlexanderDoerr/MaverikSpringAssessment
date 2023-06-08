package com.example.maverikspringassesment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findOneByTitle(String title);
    Movie findOneByImdbID(String imdbID);
    List<Movie> findAllByGenreContains(String genre);
    List<Movie> findAllByActorsContains(String actors);

}
