package com.cristianperez.ubudrentalwithspring.logic.interfaces;

import com.cristianperez.ubudrentalwithspring.logic.models.Movie;

public interface MovieInformationRepository {
    Movie getMovieInfo(String movieToRent);
}
