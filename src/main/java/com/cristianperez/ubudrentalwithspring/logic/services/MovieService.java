package com.cristianperez.ubudrentalwithspring.logic.services;

import com.cristianperez.ubudrentalwithspring.logic.interfaces.MovieInformationRepository;
import com.cristianperez.ubudrentalwithspring.logic.interfaces.MovieRepository;
import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.models.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private MovieInformationRepository movieInformationRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieInformationRepository movieInformationRepository) {
        this.movieRepository = movieRepository;
        this.movieInformationRepository = movieInformationRepository;
    }

    public List<Movie> getAvailableMovies() {
        return movieRepository.getListOfAvailableMovies();
    }

    public Rental rentAMovie(String movieTitle) {
        return movieRepository.rentAMovie(movieTitle);
    }

    public Movie getMovieInfo(String movieToRent) {
        return movieInformationRepository.getMovieInfo(movieToRent);
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.searchMoviesByTitle(title);
    }
}
