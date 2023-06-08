package com.example.maverikspringassesment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imdb")
public class MovieImdbApiController {

    private final MovieService movieService;
    private String alex = "alex";

    public MovieImdbApiController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("/{movieName}")
    @ResponseStatus(HttpStatus.OK)
    public void getMovieRest(@PathVariable String movieName){
        movieService.dbSave(movieService.addMovies(movieService.getMovies(movieName)));
    }

}

