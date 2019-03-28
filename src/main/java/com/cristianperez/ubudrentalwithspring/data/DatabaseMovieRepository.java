package com.cristianperez.ubudrentalwithspring.data;

import com.cristianperez.ubudrentalwithspring.logic.interfaces.MovieRepository;
import com.cristianperez.ubudrentalwithspring.logic.models.Movie;
import com.cristianperez.ubudrentalwithspring.logic.models.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DatabaseMovieRepository implements MovieRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcMovieTemplate;

    @Autowired
    public DatabaseMovieRepository(NamedParameterJdbcTemplate jdbcMovieTemplate) {
        this.jdbcMovieTemplate = jdbcMovieTemplate;
    }

    @Override
    public List<Movie> getListOfAvailableMovies() {
        String sqlQueryToFindAvailableMovies = "SELECT * FROM movies LEFT JOIN rentals ON (movies.m_id = rentals.r_m_id) WHERE rentals.r_m_id IS NULL;";
        return jdbcMovieTemplate.query(sqlQueryToFindAvailableMovies, (resultSet, i) -> {
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("m_title"));
            movie.setMovieYear(resultSet.getInt("m_year"));
            movie.setPoster(resultSet.getString("m_poster"));
            movie.setMovieGenre(resultSet.getString("m_genre"));
            return movie;
        });
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        String sqlToSearchForMoviesInDb = "SELECT * FROM movies WHERE m_title LIKE :title;";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("title", "%" + title + "%");
        return jdbcMovieTemplate.query(sqlToSearchForMoviesInDb, sqlParameterSource, (resultSet, i) -> {
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("m_title"));
            movie.setMovieYear(resultSet.getInt("m_year"));
            movie.setPoster(resultSet.getString("m_poster"));
            return movie;
        });
    }

    @Override
    public Rental rentAMovie(String movieTitle) {
        Rental rental = createRentalObjectAndSetProperties(movieTitle);
        String sqlQueryToInsertMovieAsRented = "INSERT INTO rentals (r_m_id,r_c_id,r_date_start," +
                "r_date_finish,r_movie_returned) " +
                "VALUES (:rentalMovieId,:rentalCustomerId,:rentalDateStart,:rentalDateFinish,:movieReturned);";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("rentalMovieId",rental.getRentalMovieId())
                .addValue("rentalCustomerId",rental.getRentalCustomerId())
                .addValue("rentalDateStart",rental.getRentalDateStart())
                .addValue("rentalDateFinish",rental.getRentalDateFinish())
                .addValue("movieReturned","NO");
        jdbcMovieTemplate.update(sqlQueryToInsertMovieAsRented,namedParameters);
        return rental;
    }


    private Rental createRentalObjectAndSetProperties(String movieTitle) {
        Rental rental = new Rental();
        rental.setRentalDateStart(getStartDateAsToday().toString());
        rental.setRentalDateFinish(getReturnDateAsTwoDaysAfterToday().toString());
        //TODO: rentalCustomerId is hardcoded to 8 until login is implemented
        rental.setRentalCustomerId(8);
        rental.setRentalMovieId(findMovieIdBasedOnTitle(movieTitle));
        return rental;
    }

    private Integer findMovieIdBasedOnTitle(String movieTitle) {
        String sqlQueryToGetIdBasedOnTitle = "SELECT * FROM movies WHERE m_title = \"" + movieTitle + "\";";
        List<Integer> integerList = jdbcMovieTemplate.query(sqlQueryToGetIdBasedOnTitle,(resultSet, i) -> resultSet.getInt("m_id"));
        return integerList.get(0);
    }

    private Date getReturnDateAsTwoDaysAfterToday() {
        String date = LocalDate.now().plusDays(2).toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date dateStr = formatter.parse(date);
            return new java.sql.Date(dateStr.getTime());
        } catch (ParseException exc) {
            System.out.println("Exception while parsing Date " + exc);
            return null;
        }
    }

    private Date getStartDateAsToday() {
        String date = LocalDate.now().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date dateStr = formatter.parse(date);
            return new java.sql.Date(dateStr.getTime());
        } catch (ParseException exc) {
            System.out.println("Exception while parsing Date " + exc);
            return null;
        }
    }
}
