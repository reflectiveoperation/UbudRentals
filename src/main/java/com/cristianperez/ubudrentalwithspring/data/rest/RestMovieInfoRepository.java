package com.cristianperez.ubudrentalwithspring.data.rest;

import com.cristianperez.ubudrentalwithspring.data.rest.models.JsonMovie;
import com.cristianperez.ubudrentalwithspring.logic.interfaces.MovieInformationRepository;
import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class RestMovieInfoRepository implements MovieInformationRepository {

    private RestTemplate restTemplate;


    private static final Logger log = LoggerFactory.getLogger(RestMovieInfoRepository.class);
    private static final String apiKey = "151714b6";
    private static final String apiCall = "http://www.omdbapi.com/?apikey=".concat(apiKey).concat("&t="); //plus title

    @Autowired
    public RestMovieInfoRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Movie getMovieInfo(String movieToRent) {
        JsonMovie jsonMovie = restTemplate.getForObject(apiCall.concat("\"".concat(movieToRent).concat("\"")),JsonMovie.class);
        return parseJsonMovieObjectToMovie(jsonMovie, movieToRent);
    }

    private Movie parseJsonMovieObjectToMovie(JsonMovie jsonMovie, String movieToRent) {
        Movie movie = new Movie();
        movie.setTitle(movieToRent);
        movie.setImdbRating(jsonMovie.getImdbRating());
        movie.setMovieYear(jsonMovie.getMovieYear());
        movie.setPlot(jsonMovie.getPlot());
        movie.setPoster(jsonMovie.getPoster());
        return movie;
    }
}
