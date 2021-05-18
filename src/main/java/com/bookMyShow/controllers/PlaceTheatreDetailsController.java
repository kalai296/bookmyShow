package com.bookMyShow.controllers;

import com.bookMyShow.dao.PlaceTheatreDetails;
import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import com.bookMyShow.repos.PlaceTheatreDetailsRepository;
import com.bookMyShow.service.PlaceTheatreDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceTheatreDetailsController {
    private PlaceTheatreDetailsService placeTheatreDetailsService;

    @Autowired
    private PlaceTheatreDetailsController(PlaceTheatreDetailsService placeTheatreDetailsService) {
        this.placeTheatreDetailsService = placeTheatreDetailsService;
    }

    @RequestMapping(value = "/placeTheatreDetails", method = RequestMethod.GET)
    public List<PlaceTheatreDetails> findPlaceTheatreDetails() {
        return placeTheatreDetailsService.findPlaceTheatreDetails();
    }
}
