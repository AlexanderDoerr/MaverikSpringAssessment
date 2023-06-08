package com.example.maverikspringassesment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieDBController {

    private final MovieRepository movieRepository;

    public MovieDBController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id){
        return movieRepository.findOneByImdbID(id);
    }

    @GetMapping("/title/{title}")
    public Movie getMovieByTitle(@PathVariable String title){
        return movieRepository.findOneByTitle(title);
    }

    @GetMapping("/actors/{actors}")
    public List<Movie> getAllMoviesByActors(@PathVariable String actors){
        System.out.println(actors);
        return movieRepository.findAllByActorsContains(actors);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getAllMoviesByGenre(@PathVariable String genre){
        return movieRepository.findAllByGenreContains(genre);
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable String id, @RequestBody Movie movie){
        if(!id.equals(movie.getImdbID())){
            throw new IllegalArgumentException("ID in path does not match ID in movie object");
        }else {
            movie.setImdbID(id);
            return movieRepository.save(movie);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable String id){
        movieRepository.deleteById(id);
    }


}
