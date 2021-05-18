package com.bookMyShow.service;

import com.bookMyShow.dao.PlaceTheatreMovieDetails;
import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import com.bookMyShow.repos.PlaceTheatreMovieDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PlaceTheatreMovieDetailsService {
    private PlaceTheatreMovieDetailsRepository placeTheatreMovieDetailsRepos;

    @Autowired
    private PlaceTheatreMovieDetailsService(PlaceTheatreMovieDetailsRepository placeTheatreMovieDetailsRepos) {
        this.placeTheatreMovieDetailsRepos = placeTheatreMovieDetailsRepos;
    }

    @RequestMapping(value = "/placeTheatreMovieDetails", method = RequestMethod.GET)
        public List<PlaceTheatreMovieDetails> findPlaceTheatreMoviesDetails() {
            return placeTheatreMovieDetailsRepos.findAll();
    }

    @RequestMapping(value = "/movieByPlaceAndDate/{place}/{showDate}", method = RequestMethod.GET)
    public List<String> findMovieByPlaceAndDate(@PathVariable String place, @PathVariable String showDate) {
        return placeTheatreMovieDetailsRepos.findMovieByDateAndPlace(place, showDate);
    }
}
