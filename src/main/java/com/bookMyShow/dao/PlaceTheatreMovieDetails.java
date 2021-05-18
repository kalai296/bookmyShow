package com.bookMyShow.dao;

import javax.persistence.*;

@Entity
@Table(name = "placeTheatreMovieDetails")
public class PlaceTheatreMovieDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int placeTheatreId;
    private int movieId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceTheatreId() {
        return placeTheatreId;
    }

    public void setPlaceTheatreId(int placeTheatreId) {
        this.placeTheatreId = placeTheatreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "placeTheatreMovieDetails{" +
                "id=" + id +
                ", placeTheatreId=" + placeTheatreId +
                ", movieId=" + movieId +
                '}';
    }
}
