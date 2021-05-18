package com.bookMyShow.controllers;

import com.bookMyShow.dao.UserDetails;
import com.bookMyShow.requestDto.UserDetailsRequest;
import com.bookMyShow.responseDto.LoginResponse;
import com.bookMyShow.responseDto.LogoutResponse;
import com.bookMyShow.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class UserDetailsController {

    private UserDetailsService userDetailsService;

    @Autowired
    private UserDetailsController (UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public UserDetails registerUser(@RequestBody UserDetailsRequest userDetailsRequest) {
        return userDetailsService.registerUser(userDetailsRequest);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse loginUser(@RequestBody UserDetailsRequest userDetailsRequest, HttpServletResponse httpServletResponse) {

        LoginResponse loginResponse = userDetailsService.loginUser(userDetailsRequest);
        if(loginResponse.getValidLogin()){
            Cookie cookie = new Cookie("session-id", userDetailsService.getUserSessionDetails(userDetailsRequest.getEmail()).getSessionId());
            cookie.setMaxAge(999999);
            //cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setDomain("http://127.0.0.1:3000");

            cookie.setPath("/");

            //httpServletResponse.addHeader("Access-Control-Expose-Headers", "set-cookie");
            httpServletResponse.addCookie(cookie);
        }

        return loginResponse;
    }

    @RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
    public LogoutResponse logoutUser(HttpServletRequest httpServletRequest) {
        String rawCookie = httpServletRequest.getHeader("Cookie");
        String[] rawCookieParams = rawCookie.split(";");
        String[] rawCookieNameAndValuePair = new String[100];
        for(String rawCookieNameAndValue :rawCookieParams)
        {
            rawCookieNameAndValuePair = rawCookieNameAndValue.split("=");
        }
        return userDetailsService.logoutUser(rawCookieNameAndValuePair[1]);
    }

}
