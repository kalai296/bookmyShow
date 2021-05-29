package com.bookMyShow.service;

import com.bookMyShow.dao.MovieDetails;
import com.bookMyShow.dao.UserSessionDetails;
import com.bookMyShow.repos.UserSessionDetailsRepository;
import com.bookMyShow.requestDto.MovieDetailsRequest;
import com.bookMyShow.requestDto.MovieTheatreShowDetails;
import com.bookMyShow.requestDto.TheatreShowDetails;
import com.bookMyShow.repos.MovieDetailsRepository;
import com.bookMyShow.responseDto.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ResponseEntity<?> postMovieDetails(MovieDetailsRequest movieDetailsRequest) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            System.out.println("movieDetailsRequest.body :" + movieDetailsRequest.toString());
            System.out.println("movieDetailsRequest.getName().isEmpty() :" + movieDetailsRequest.getName().isEmpty() + "movieDetailsRequest.getName() == null :" + movieDetailsRequest.getName() == null);
            if ( (movieDetailsRequest.getName().isEmpty() || movieDetailsRequest.getName() == null)  || (movieDetailsRequest.getLanguage().isEmpty() || movieDetailsRequest.getLanguage() == null) || (Integer)movieDetailsRequest.getDuration() == null) {
                commonResponse.setMessage("Mandatory params missing");
                commonResponse.setStatus("Failed");
                return ResponseEntity.status(401).headers(new HttpHeaders()).contentType(MediaType.APPLICATION_JSON).body(commonResponse);
            }
            movieDetailsRepos.postMovieDetails(movieDetailsRequest.getName(), movieDetailsRequest.getLanguage(), movieDetailsRequest.getDuration());
            commonResponse.setMessage("Movies added successfully");
            commonResponse.setStatus("Success");
            return ResponseEntity.status(200).headers(new HttpHeaders()).contentType(MediaType.APPLICATION_JSON).body(commonResponse);
        } catch ( Exception e) {
            System.out.println("Exception is: " +  e);
            commonResponse.setMessage("Movies not added successfully");
            commonResponse.setStatus("Failed");
            return ResponseEntity.status(400).headers(new HttpHeaders()).contentType(MediaType.APPLICATION_JSON).body(commonResponse);
        }
    }

    public ResponseEntity<?> findMoviesDetails(String pageNo, String pageSize) {
        List<MovieDetails> movieDetails = new ArrayList<>();
        Page<MovieDetails> pageMovieDetails =  movieDetailsRepos.findAll( PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize)));
        movieDetails = pageMovieDetails.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("movieDetails", movieDetails);
        response.put("currentPage", pageMovieDetails.getNumber());
        response.put("totalItems", pageMovieDetails.getTotalElements());
        response.put("totalPages", pageMovieDetails.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> findSortedMoviesDetails(String pageNo, String pageSize, String[] sort) {

        System.out.println("sort Array: " + sort.length + sort[0] );

        try{
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains("-")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split("-");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<MovieDetails> movieDetails = new ArrayList<>();
            Page<MovieDetails> pageMovieDetails =  movieDetailsRepos.findAll( PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(orders)));
            movieDetails = pageMovieDetails.getContent();

            if (movieDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("movieDetails", movieDetails);
            response.put("currentPage", pageMovieDetails.getNumber());
            response.put("totalItems", pageMovieDetails.getTotalElements());
            response.put("totalPages", pageMovieDetails.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Sort.Direction getSortDirection(String direction) {
        if (direction.equalsIgnoreCase("asc")) return Sort.Direction.ASC;
        else if (direction.equalsIgnoreCase("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }


}
