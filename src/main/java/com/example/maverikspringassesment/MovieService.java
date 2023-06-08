package com.example.maverikspringassesment;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieService {

    private final WebClient webClient;
    private final MovieRepository movieRepository;
    String movieListAPI = "https://gateway.maverik.com/movie/api/movie/title/";
    String individualMovieAPI = "https://gateway.maverik.com/movie/api/movie/";



    public MovieService(WebClient webClient, MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.webClient = webClient;
    }

    public List<Movie> getMovies(String title){
        Mono<List<Movie>> response = webClient.get()
                .uri(movieListAPI + title + "?source=web")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Movie>>() {
                });
         List<Movie> movies = response.block();
         return movies;
    }

    public List<Movie> addMovies(List<Movie> movies){
        List<Movie> movieList = new ArrayList<>();
        for(Movie movie : movies){
            Mono<Movie> response = webClient.get()
                    .uri(individualMovieAPI + movie.getImdbID() + "?source=web")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Movie.class);
            Movie movieToAdd = response.block();
            movieList.add(movieToAdd);
        }
        return movieList;
    }

    public void dbSave(List<Movie> movieList){
        for(Movie movie : movieList){
            movieRepository.insert(movie);

        }

    }
}
