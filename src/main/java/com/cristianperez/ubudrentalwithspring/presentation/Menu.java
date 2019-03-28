package com.cristianperez.ubudrentalwithspring.presentation;

import com.cristianperez.ubudrentalwithspring.logic.models.Customer;
import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.services.CustomerService;
import com.cristianperez.ubudrentalwithspring.logic.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu  {

    private MovieService movieService;
    private CustomerService customerService;

    @Autowired
    public Menu(MovieService movieService, CustomerService customerService) {
        this.customerService = customerService;
        this.movieService = movieService;
    }

    public void start() {
        System.out.println("Welcome to Ubud Movie Rentals!");
        System.out.println("------------------------------");
        System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        validateUser();
    }

    private void validateUser() {
        System.out.println("------------------------------");
        System.out.println("Please enter your first and last name to enter: ");
        String firstAndLastName = new Scanner(System.in).nextLine();
        Customer customer = customerService.obtainCustomerDetailsIfExists(firstAndLastName);
        try {
            if (customer.getCustomerFirstName().concat(" ").concat(customer.getCustomerLastName()).equals(firstAndLastName)) {
                showUserOptions();
                proceedToUserSelectedOption(new Scanner(System.in));
            }
        } catch (Exception exc) {
            System.out.println("Sorry, " + firstAndLastName + " does not exist. Please try again!" + exc);
        }
    }

    private void showUserOptions() {
        System.out.println("------------------------------");
        System.out.println("Press 1 to see a list of available movies");
        System.out.println("Press 2 to rent a movie");
        System.out.println("Press 0 to exit the store");
        System.out.println("------------------------------");
    }

    private void proceedToUserSelectedOption(Scanner scanner) {
        boolean isQuittingProgram = false;
        while (!isQuittingProgram) {
            switch (scanner.nextInt()) {
                case 1:
                    showAvailableMovieTitles();
                    showUserOptions();
                    break;
                case 2:
                    showAvailableMovieTitles();
                    selectMovieToRent();
                    showUserOptions();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Thank you for stopping by!");
                    isQuittingProgram = true;
                    break;
            }
        }
    }

    private void showAvailableMovieTitles() {
        System.out.println("These are our available movies:");
        System.out.println("------------------------------");
        for (Movie movie : movieService.getAvailableMovies()) {
            System.out.println("-> " + movie.getTitle());
        }
    }

    private void selectMovieToRent() {
        System.out.println("------------------------------");
        System.out.println("Type the Name of the movie you would like to rent: ");
        System.out.println("------------------------------");
        String movieToRent = new Scanner(System.in).nextLine();
        System.out.println();
        displayMovieInformation(movieToRent);
        Integer rentalMovieID = movieService.rentAMovie(movieToRent).getRentalMovieId();
        if (rentalMovieID == null) {
            System.out.println("Sorry, we are experiencing technical difficulties... try again later");
        } else {
            System.out.println("You have successfully rented: " + movieToRent);
            System.out.println("Back to Main Menu...");
        }
    }

    private void displayMovieInformation(String movieToRent) {
        System.out.println("------------------------------");
        System.out.println("The movie " + movieToRent + " was released on " + movieService
                .getMovieInfo(movieToRent).getMovieYear());
        System.out.println("The plot: " + movieService.getMovieInfo(movieToRent).getPlot());
        System.out.println("It has an IMDB rating of: " + movieService.getMovieInfo(movieToRent).getImdbRating());
        System.out.println("Enjoy!");
        System.out.println("------------------------------");
    }
}
