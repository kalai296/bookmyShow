package com.bookMyShow.service;

import com.bookMyShow.dao.MovieDetails;
import com.bookMyShow.dao.UserSessionDetails;
import com.bookMyShow.repos.UserSessionDetailsRepository;
import com.bookMyShow.requestDto.MovieTheatreShowDetails;
import com.bookMyShow.requestDto.TheatreShowDetails;
import com.bookMyShow.repos.MovieDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieDetailsService {

    private MovieDetailsRepository movieDetailsRepos;
    private UserSessionDetailsRepository userSessionDetailsRepository;

    @Autowired
    private MovieDetailsService(MovieDetailsRepository movieDetailsRepo, UserSessionDetailsRepository userSessionDetailsRepository) {
        this.movieDetailsRepos = movieDetailsRepo;
        this.userSessionDetailsRepository = userSessionDetailsRepository;
    }

    public List<MovieDetails> findAllMoviesDetails(String sessionId) {
        //MovieDetails movieDetails = new MovieDetails();
        System.out.println("sessionId  :" + sessionId);
        List<MovieDetails> movieDetailsList = new ArrayList<MovieDetails>();
        UserSessionDetails userSessionDetails = userSessionDetailsRepository.findBySessionId(sessionId);
        if(userSessionDetails == null) {
            return movieDetailsList;
        }
        int sessionTimeDiff = userSessionDetailsRepository.getCreatedAtTimeStampDiff(sessionId);
        System.out.println("sessionTimeDiff :" + sessionTimeDiff);
        if (userSessionDetails.getStatus().toString().equals("Active") && sessionTimeDiff <= 2)
            return movieDetailsRepos.findAll();
        else
            return movieDetailsList;
    }

    public MovieTheatreShowDetails findAllMoviesDetailsByNameAndDate(String movieName, String date) {

                List<Object[]> object = movieDetailsRepos.findAllMoviesDetailsByNameAndDate(movieName, date);
                MovieTheatreShowDetails movieTheatreShowDetails = new MovieTheatreShowDetails() ;

                List<TheatreShowDetails> theatreShowDetailsList = new ArrayList<TheatreShowDetails>();
                object.forEach( currentObj -> {

                    TheatreShowDetails theatreShowDetails = new TheatreShowDetails();
                    theatreShowDetails.setShowType((String)currentObj[1]);
                    theatreShowDetails.setDateAndTime( ((Timestamp)currentObj[2]).toString() );
                    theatreShowDetails.setTheatreName((String)currentObj[3]);
                    theatreShowDetailsList.add(theatreShowDetails);
                    movieTheatreShowDetails.setMovieName((String)currentObj[0]);

                });
                movieTheatreShowDetails.setTheatreShowDetailsList(theatreShowDetailsList);
                return movieTheatreShowDetails;
    }



}
