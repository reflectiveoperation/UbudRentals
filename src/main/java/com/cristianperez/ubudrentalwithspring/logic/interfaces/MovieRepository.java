package com.cristianperez.ubudrentalwithspring.logic.interfaces;

import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.models.Rental;

import java.util.List;

public interface MovieRepository {
    List<Movie> getListOfAvailableMovies();
    Rental rentAMovie(String movieTitle);
    List<Movie> searchMoviesByTitle(String title);
}
