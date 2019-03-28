package com.cristianperez.ubudrentalwithspring.data.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonMovie {
    private Integer id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Actors")
    private String movieMainActor;
    @JsonProperty("Year")
    private Integer movieYear;
    @JsonProperty("Genre")
    private String movieGenre;
    @JsonProperty("Plot")
    private String plot;
    private String imdbRating;
    @JsonProperty("Poster")
    private String poster;

    public JsonMovie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "JsonMovie{" +
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
