package com.fawry.fawryTask.repo;

import com.fawry.fawryTask.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

    public Optional<Movie> findByTitle(String title);
}
