package com.fawry.fawryTask.service;

import com.fawry.fawryTask.model.Movie;
import com.fawry.fawryTask.repo.MovieRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo repo;


    private final String API_KEY = "eb6a5bb"; // replace with your actual OMDb API key
    private final String OMDB_URL = "https://www.omdbapi.com/?apikey=" + API_KEY + "&i=";
    public Movie getMovie(String imdbId) {
        System.out.println("Hello");
        RestTemplate restTemplate = new RestTemplate();
        String url = OMDB_URL + imdbId;
        return restTemplate.getForObject(url, Movie.class);
    }

    public void addMovieAdmin(Movie movie) {
        System.out.println(movie);

         repo.save(movie);
    }

    public void deleteMovieAdmin(Long id) {
        repo.deleteById(id);
    }

    public Movie getMovieBytitle(String title) {
        return repo.findByTitle(title).
        orElseThrow(() -> new RuntimeException("Movie not found with title: " + title));
    }

    public List<Movie> getAllMovies() {
        return repo.findAll();
    }
}
