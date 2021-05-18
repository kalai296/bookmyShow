package com.bookMyShow.controllers;

import com.bookMyShow.dao.PlaceTheatreMovieDetails;
import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import com.bookMyShow.repos.PlaceTheatreMovieDetailsRepository;
import com.bookMyShow.service.PlaceTheatreMovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceTheatreMovieDetailsController {
    private PlaceTheatreMovieDetailsService placeTheatreMovieDetailsService;

    @Autowired
    private PlaceTheatreMovieDetailsController(PlaceTheatreMovieDetailsService placeTheatreMovieDetailsService) {
        this.placeTheatreMovieDetailsService = placeTheatreMovieDetailsService;
    }

    @RequestMapping(value = "/placeTheatreMovieDetails", method = RequestMethod.GET)
        public List<PlaceTheatreMovieDetails> findPlaceTheatreMoviesDetails() {
            return placeTheatreMovieDetailsService.findPlaceTheatreMoviesDetails();
    }

    @RequestMapping(value = "/movieByPlaceAndDate/{place}/{showDate}", method = RequestMethod.GET)
    public List<String> findMovieByPlaceAndDate(@PathVariable String place, @PathVariable String showDate) {
        return placeTheatreMovieDetailsService.findMovieByPlaceAndDate(place, showDate);
    }
}
