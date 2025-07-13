package controller;

import com.fawry.fawryTask.model.Movie;
import com.fawry.fawryTask.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class MovieController {

    @Autowired
    private MovieService service;


    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("movie/{imdbId}")
    public Movie getMovieAdmin(@PathVariable String imdbId){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = OMDB_URL + imdbId;
//        return restTemplate.getForObject(url, Movie.class);
        return service.getMovie(imdbId);
    }



    @PreAuthorize("hasRole('Admin')")
    @PostMapping("movie")
    public void addMovie(@RequestBody Movie movie){
        service.addMovieAdmin(movie);
    }

    @GetMapping("movie/user/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        Movie movie = service.getMovieBytitle(title);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("movie/user/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = service.getMovieById(id);
        return ResponseEntity.ok(movie);
    }


    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("movie/{id}")
    public void deleteMovie(@PathVariable long id){
        service.deleteMovieAdmin(id);
    }
    @GetMapping("movies")
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }



}
