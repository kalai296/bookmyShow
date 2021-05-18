package com.bookMyShow.service;

import com.bookMyShow.dao.PlaceDetails;
import com.bookMyShow.repos.PlaceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class PlaceDetailsService {

    private PlaceDetailsRepository placeDetailsRepos;

    @Autowired
    private PlaceDetailsService(PlaceDetailsRepository placeDetailsRepo) {
        this.placeDetailsRepos = placeDetailsRepo;
    }

    public List<PlaceDetails> findAllPlaceDetails() {
        return placeDetailsRepos.findAll();
    }
}
