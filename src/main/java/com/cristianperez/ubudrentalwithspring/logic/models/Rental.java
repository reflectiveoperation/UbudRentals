package com.cristianperez.ubudrentalwithspring.logic.models;


public class Rental {
    private Integer rentalId;
    private Integer rentalMovieId;
    private Integer rentalCustomerId;
    private String rentalDateStart;
    private String rentalDateFinish;
    private String movieReturned;

    public String getMovieReturned() {
        return movieReturned;
    }

    public void setMovieReturned(String movieReturned) {
        this.movieReturned = movieReturned;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public Integer getRentalMovieId() {
        return rentalMovieId;
    }

    public void setRentalMovieId(Integer rentalMovieId) {
        this.rentalMovieId = rentalMovieId;
    }

    public Integer getRentalCustomerId() {
        return rentalCustomerId;
    }

    public void setRentalCustomerId(Integer rentalCustomerId) {
        this.rentalCustomerId = rentalCustomerId;
    }

    public String getRentalDateStart() {
        return rentalDateStart;
    }

    public void setRentalDateStart(String rentalDateStart) {
        this.rentalDateStart = rentalDateStart;
    }

    public String getRentalDateFinish() {
        return rentalDateFinish;
    }

    public void setRentalDateFinish(String rentalDateFinish) {
        this.rentalDateFinish = rentalDateFinish;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", rentalMovieId=" + rentalMovieId +
                ", rentalCustomerId=" + rentalCustomerId +
                ", rentalDateStart='" + rentalDateStart + '\'' +
                ", rentalDateFinish='" + rentalDateFinish + '\'' +
                '}';
    }
}
