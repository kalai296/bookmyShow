package com.bookMyShow.controllers;

import com.bookMyShow.dao.MovieDetails;
import com.bookMyShow.requestDto.MovieDetailsRequest;
import com.bookMyShow.requestDto.MovieTheatreShowDetails;
import com.bookMyShow.service.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MovieDetailsController {

    private MovieDetailsService movieDetailsService;

    @Autowired
    private MovieDetailsController(MovieDetailsService movieDetailsService) {
        this.movieDetailsService = movieDetailsService;
    }

    @RequestMapping(value = "/moviesDetails", method = RequestMethod.GET)
    public List<MovieDetails> findAllMoviesDetails(HttpServletRequest httpServletRequest) {
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                System.out.println("Header: " + httpServletRequest.getHeader(headerNames.nextElement()));
            }
        }
        String rawCookie = httpServletRequest.getHeader("Cookie");
        String[] rawCookieParams = rawCookie.split(";");
        String[] rawCookieNameAndValuePair = new String[100];
        for(String rawCookieNameAndValue :rawCookieParams)
        {
             rawCookieNameAndValuePair = rawCookieNameAndValue.split("=");
        }
        return movieDetailsService.findAllMoviesDetails(rawCookieNameAndValuePair[1]);
    }


    @RequestMapping(value = "/moviesDetails/{movieName}/{date}", method = RequestMethod.GET)
    public MovieTheatreShowDetails findAllMoviesDetailsByNameAndDate(@PathVariable String movieName, @PathVariable String date) {
        return movieDetailsService.findAllMoviesDetailsByNameAndDate(movieName, date);
    }


    @RequestMapping(value = "/moviesDetails", method = RequestMethod.POST)
    public ResponseEntity<?> postMovieDetails(@RequestBody MovieDetailsRequest movieDetailsRequest) throws ClassNotFoundException, NoSuchFieldException {
//        CommonResponse commonResponse = new CommonResponse();
//        System.out.println("movieDetailsRequest object:" + movieDetailsRequest.toString());
//        Class clazz = Class.forName("com/bookMyShow/requestDto/MovieDetailsRequest");
//        Field nameField = clazz.getField("name");
//        Field languageField = clazz.getField("language");
//        Field durationField = clazz.getField("duration");
//        if ( nameField == null || languageField == null || durationField == null) {
//            commonResponse.setMessage("Mandatory params missing");
//            commonResponse.setStatus("Failed");
//            return ResponseEntity.status(400).headers(new HttpHeaders()).contentType(MediaType.APPLICATION_JSON).body(commonResponse);
//        }
        return movieDetailsService.postMovieDetails(movieDetailsRequest);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> findMoviesDetails(@RequestParam(value = "pageNo", defaultValue = "0") String pagNo, @RequestParam(value = "pageSize", defaultValue = "5") String size) {
        return movieDetailsService.findMoviesDetails(pagNo, size);
    }

    @RequestMapping(value = "/sortedMovies", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> findSortedMoviesDetails(@RequestParam(value = "pageNo", defaultValue = "0") String pagNo, @RequestParam(value = "pageSize", defaultValue = "5") String size, @RequestParam(value = "sort", defaultValue = "id,desc") String[] sort) {
        System.out.println("pagNo, size, sort      :" + pagNo + " " + size + " " + sort);
        return movieDetailsService.findSortedMoviesDetails(pagNo, size, sort);
    }
}
