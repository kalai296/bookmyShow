package com.bookMyShow.dao;

import javax.persistence.*;

@Entity
@Table(name = "placeTheatreDetails")
public class PlaceTheatreDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int placeId;
    private int theatreId;
    private String placeTheatreName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getPlaceTheatreName() {
        return placeTheatreName;
    }

    public void setPlaceTheatreName(String placeTheatreName) {
        this.placeTheatreName = placeTheatreName;
    }

    @Override
    public String toString() {
        return "placeTheatreDetails{" +
                "id=" + id +
                ", placeId=" + placeId +
                ", theatreId=" + theatreId +
                ", placeTheatreName='" + placeTheatreName + '\'' +
                '}';
    }
}
