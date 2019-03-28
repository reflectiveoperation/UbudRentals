package com.cristianperez.ubudrentalwithspring.presentation.web.controller;

import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.services.CustomUserDetailsService;
import com.cristianperez.ubudrentalwithspring.logic.services.MovieService;
import com.cristianperez.ubudrentalwithspring.presentation.web.model.MovieSearchRequest;
import com.cristianperez.ubudrentalwithspring.presentation.web.model.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @GetMapping("/")
    public String ubudRentals(Model model) {
        model.addAttribute("movies", movieService.getAvailableMovies());
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        return "index";
    }

    @GetMapping("/search-bar")
    public String searchBar(Model model) {
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        return "searchBar";
    }

    @GetMapping("/movie-search-result")
    public String displayMovieSearchInDbResult(@RequestParam(name = "title", required = false) String title, Model model) {
        List<Movie> foundMovies = movieService.searchMoviesByTitle(title);
        model.addAttribute("movies", foundMovies);
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        return "movieSearchResult";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/display-available-movies")
    public String displayAvailableMovies(Model model) {
        List<Movie> availableMovies = movieService.getAvailableMovies();
        model.addAttribute("movies", availableMovies);
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        return "displayAvailableMovies";
    }

    @GetMapping("/login")
    public String populateLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String populateRegisterPage(Model model) {
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        model.addAttribute("newUser", new NewUser());
        return "register";
    }

    @PostMapping("/successfullyRegistered")
    public String populateSuccessfullyRegistered(Model model, @ModelAttribute NewUser newUser) {
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        customUserDetailsService.registerUser(newUser);
        return "successfullyRegistered";
    }

    //TODO: ONLINE SEARCH BELOW IS THROWING EXCEPTIONS

    @GetMapping("/initiate-online-search")
    public String initiateOnlineSearch(Model model) {
        model.addAttribute("movieSearchRequest", new MovieSearchRequest());
        return "initiateOnlineSearch";
    }

    @GetMapping("/online-search-result")
    public String searchOmdbDatabase(@RequestParam(name = "title", required = false) String title, Model model) {
        Movie foundMovie = movieService.getMovieInfo(title);
        model.addAttribute("movie", foundMovie);
        model.addAttribute("movieSearchResult", new MovieSearchRequest());
        return "onlineSearchResult";
    }



}