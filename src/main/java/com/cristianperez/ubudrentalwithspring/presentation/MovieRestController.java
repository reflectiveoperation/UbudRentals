package com.cristianperez.ubudrentalwithspring.presentation;

import com.cristianperez.ubudrentalwithspring.logic.exceptions.InvalidTokenException;
import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.models.Rental;
import com.cristianperez.ubudrentalwithspring.logic.services.CustomUserDetailsService;
import com.cristianperez.ubudrentalwithspring.logic.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<Movie>> displayAvailableMovies(@RequestParam(name = "token", required = true) String token) {
        try {
            customUserDetailsService.validateApiToken(token);
        } catch (InvalidTokenException exc) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(movieService.getAvailableMovies(), HttpStatus.OK);
    }

    @PostMapping("/rent-movie")
    public ResponseEntity<Rental> rentMovieThroughApi(@RequestBody Movie movie, @RequestParam(name = "token", required = true) String token) {
        try {
            customUserDetailsService.validateApiToken(token);
        } catch (InvalidTokenException exc) {
            return new ResponseEntity<>(new Rental(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(movieService.rentAMovie(movie.getTitle()), HttpStatus.OK);
    }

}
