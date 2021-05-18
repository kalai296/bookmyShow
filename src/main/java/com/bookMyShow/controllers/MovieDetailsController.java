package com.bookMyShow.controllers;

import com.bookMyShow.dao.MovieDetails;
import com.bookMyShow.requestDto.MovieTheatreShowDetails;
import com.bookMyShow.service.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:3000")
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
}
