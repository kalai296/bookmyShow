package com.bookMyShow.service;

import com.bookMyShow.dao.PlaceTheatreDetails;
import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import com.bookMyShow.repos.PlaceTheatreDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class PlaceTheatreDetailsService {
    private PlaceTheatreDetailsRepository placeTheatreDetailsRepository;

    @Autowired
    private PlaceTheatreDetailsService(PlaceTheatreDetailsRepository placeTheatreDetailsRepository) {
        this.placeTheatreDetailsRepository = placeTheatreDetailsRepository;
    }

    public List<PlaceTheatreDetails> findPlaceTheatreDetails() {
        return placeTheatreDetailsRepository.findAll();
    }
}
