package com.bookMyShow.controllers;

import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import com.bookMyShow.repos.PlaceTheatreMovieDetailsRepository;
import com.bookMyShow.repos.PlaceTheatreMovieShowDetailsRepository;
import com.bookMyShow.service.PlaceTheatreMovieShowDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceTheatreMovieShowDetailsController {
    private PlaceTheatreMovieShowDetailsService placeTheatreMovieShowDetailsService;

    @Autowired
    private PlaceTheatreMovieShowDetailsController(PlaceTheatreMovieShowDetailsService placeTheatreMovieShowDetailsService) {
        this.placeTheatreMovieShowDetailsService = placeTheatreMovieShowDetailsService;
    }

    @RequestMapping(value = "/placeTheatreMovieShowDetails", method = RequestMethod.GET)
    public List<PlaceTheatreMovieShowDetails> findPlaceTheatreMovieShowDetails() {
        return placeTheatreMovieShowDetailsService.findPlaceTheatreMovieShowDetails();
    }
}
