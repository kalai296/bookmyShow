package com.bookMyShow.requestDto;

import java.util.List;


public class MovieTheatreShowDetails {


    private String movieName;
    private List<TheatreShowDetails> theatreShowDetailsList;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<TheatreShowDetails> getTheatreShowDetailsList() {
        return theatreShowDetailsList;
    }

    public void setTheatreShowDetailsList(List<TheatreShowDetails> theatreShowDetailsList) {
        this.theatreShowDetailsList = theatreShowDetailsList;
    }

    @Override
    public String toString() {
        return "MovieTheatreShowDetails{" +
                "movieName='" + movieName + '\'' +
                ", theatreShowDetailsList=" + theatreShowDetailsList +
                '}';
    }
}
