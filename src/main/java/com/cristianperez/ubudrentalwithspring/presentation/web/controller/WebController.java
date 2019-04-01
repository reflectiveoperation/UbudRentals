package com.cristianperez.ubudrentalwithspring.presentation.web.controller;

import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MovieService movieService;


    @GetMapping("/")
    public String ubudRentals(Model model) {
        model.addAttribute("movies", movieService.getAvailableMovies());
        return "index";
    }

    @GetMapping("/search-bar")
    public String searchBar(Model model) {
        return "searchBar";
    }

    @GetMapping("/movie-search-result")
    public String displayMovieSearchInDbResult(@RequestParam(name = "title", required = false) String title, Model model) {
        List<Movie> foundMovies = movieService.searchMoviesByTitle(title);
        model.addAttribute("movies", foundMovies);
        return "movieSearchResult";
    }

    @GetMapping("/display-available-movies")
    public String displayAvailableMovies(Model model) {
        List<Movie> availableMovies = movieService.getAvailableMovies();
        model.addAttribute("movies", availableMovies);
        return "displayAvailableMovies";
    }

    @GetMapping("/initiate-online-search")
    public String initiateOnlineSearch(Model model) {
        return "initiateOnlineSearch";
    }

    @GetMapping("/online-search-result")
    public String searchOmdbDatabase(@RequestParam(name = "title", required = false) String title, Model model) {
        Movie foundMovie = movieService.getMovieInfo(title);
        model.addAttribute("movie", foundMovie);
        return "onlineSearchResult";
    }

}
