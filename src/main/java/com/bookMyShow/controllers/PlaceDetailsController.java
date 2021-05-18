package com.bookMyShow.controllers;

import com.bookMyShow.dao.PlaceDetails;
import com.bookMyShow.repos.PlaceDetailsRepository;
import com.bookMyShow.service.PlaceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class PlaceDetailsController {

    private PlaceDetailsService placeDetailsService;

    @Autowired
    private PlaceDetailsController(PlaceDetailsService placeDetailsService) {
        this.placeDetailsService = placeDetailsService;
    }

    @RequestMapping(value = "/placeDetails", method = RequestMethod.GET)
    public List<PlaceDetails> findAllPlaceDetails(HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("session-id", "1a");
        cookie.setMaxAge(999999);
        //cookie.setSecure(true);
        cookie.setHttpOnly(true);
        //cookie.setDomain("http://localhost:3000");

        cookie.setPath("/");

        //httpServletResponse.addHeader("Access-Control-Expose-Headers", "set-cookie");
        httpServletResponse.addCookie(cookie);
        return placeDetailsService.findAllPlaceDetails();
    }
}
