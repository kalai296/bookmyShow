package com.bookMyShow.service;

import com.bookMyShow.dao.TheatreDetails;
import com.bookMyShow.repos.TheatreDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class TheatreDetailsService {
    private TheatreDetailsRepository theatreDetailsRepos;

    @Autowired
    private TheatreDetailsService(TheatreDetailsRepository theatreDetailsRepos) {
        this.theatreDetailsRepos = theatreDetailsRepos;
    }

    public List<TheatreDetails> findAllTheatreDetails() {
        return theatreDetailsRepos.findAll();
    }
}
