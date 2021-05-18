package com.bookMyShow.controllers;

import com.bookMyShow.dao.TheatreDetails;
import com.bookMyShow.repos.TheatreDetailsRepository;
import com.bookMyShow.service.TheatreDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TheatreDetailsController {
    private TheatreDetailsService theatreDetailsService;

    @Autowired
    private TheatreDetailsController(TheatreDetailsService theatreDetailsService) {
        this.theatreDetailsService = theatreDetailsService;
    }

    @RequestMapping(value = "/theatreDetails", method = RequestMethod.GET)
    public List<TheatreDetails> findAllMoviesDetails() {
        return theatreDetailsService.findAllTheatreDetails();
    }
}
