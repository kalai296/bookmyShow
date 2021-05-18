package com.bookMyShow.dao;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "placeTheatreMovieShowDetails")
public class PlaceTheatreMovieShowDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int placeTheatreMovieId;
    private String showType;
    private Date  showDateAndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceTheatreMovieId() {
        return placeTheatreMovieId;
    }

    public void setPlaceTheatreMovieId(int placeTheatreMovieId) {
        this.placeTheatreMovieId = placeTheatreMovieId;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public Date getShowDateAndTime() {
        return showDateAndTime;
    }

    public void setShowDateAndTime(Date showDateAndTime) {
        this.showDateAndTime = showDateAndTime;
    }

    @Override
    public String toString() {
        return "placeTheatreMovieShowDetails{" +
                "id=" + id +
                ", placeTheatreMovieId=" + placeTheatreMovieId +
                ", showType='" + showType + '\'' +
                ", showDateAndTime=" + showDateAndTime +
                '}';
    }
}
