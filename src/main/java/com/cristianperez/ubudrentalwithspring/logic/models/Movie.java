package com.cristianperez.ubudrentalwithspring.logic.models;


public class Movie {
    private Integer id;
    private String title;
    private String movieMainActor;
    private Integer movieYear;
    private String movieGenre;
    private String plot;
    private String imdbRating;
    private String poster;

    public Movie() {
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getId() {
        return id;
    }

    public Integer setId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieMainActor() {
        return movieMainActor;
    }

    public void setMovieMainActor(String movieMainActor) {
        this.movieMainActor = movieMainActor;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", movieMainActor='" + movieMainActor + '\'' +
                ", movieYear=" + movieYear +
                ", movieGenre='" + movieGenre + '\'' +
                ", plot='" + plot + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
