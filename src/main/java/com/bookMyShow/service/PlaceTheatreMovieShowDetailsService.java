package com.bookMyShow.service;

import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import com.bookMyShow.repos.PlaceTheatreMovieShowDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class PlaceTheatreMovieShowDetailsService {
    private PlaceTheatreMovieShowDetailsRepository placeTheatreMovieShowDetailsRepository;

    @Autowired
    private PlaceTheatreMovieShowDetailsService(PlaceTheatreMovieShowDetailsRepository placeTheatreMovieShowDetailsRepository) {
        this.placeTheatreMovieShowDetailsRepository = placeTheatreMovieShowDetailsRepository;
    }

    @RequestMapping(value = "/placeTheatreMovieShowDetails", method = RequestMethod.GET)
    public List<PlaceTheatreMovieShowDetails> findPlaceTheatreMovieShowDetails() {
        return placeTheatreMovieShowDetailsRepository.findAll();
    }
}
