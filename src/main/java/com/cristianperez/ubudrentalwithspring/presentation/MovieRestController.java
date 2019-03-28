package com.cristianperez.ubudrentalwithspring.presentation;

import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.models.Rental;
import com.cristianperez.ubudrentalwithspring.logic.services.CustomUserDetails;
import com.cristianperez.ubudrentalwithspring.logic.services.CustomUserDetailsService;
import com.cristianperez.ubudrentalwithspring.logic.services.CustomerService;
import com.cristianperez.ubudrentalwithspring.logic.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/available-movies")
    public List<Movie> displayAvailableMovies() {
        return movieService.getAvailableMovies();
    }

    @PostMapping("/rent-movie")
    public Rental rentMovieThroughApi(@RequestBody Movie movie) {
        return movieService.rentAMovie(movie.getTitle());
    }

}
