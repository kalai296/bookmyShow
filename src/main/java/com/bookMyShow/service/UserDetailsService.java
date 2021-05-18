package com.bookMyShow.service;

import com.bookMyShow.dao.UserDetails;
import com.bookMyShow.dao.UserSessionDetails;
import com.bookMyShow.repos.UserSessionDetailsRepository;
import com.bookMyShow.requestDto.UserDetailsRequest;
import com.bookMyShow.repos.UserDetailsRepository;
import com.bookMyShow.responseDto.LoginResponse;
import com.bookMyShow.responseDto.LogoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsService {


    private UserDetailsRepository userDetailsRepository;
    private UserSessionDetailsRepository userSessionDetailsRepository;

    @Autowired
    private UserDetailsService (UserDetailsRepository userDetailsRepository, UserSessionDetailsRepository userSessionDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userSessionDetailsRepository = userSessionDetailsRepository;
    }

    public UserDetails registerUser(UserDetailsRequest userDetailsRequest) {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(userDetailsRequest.getEmail());
        userDetails.setMobileNo(userDetailsRequest.getMobileNo());
        userDetails.setUsername(userDetailsRequest.getUsername());
        userDetails.setStatus(userDetailsRequest.getStatus());
        userDetails.setPassword(hashPassword(userDetailsRequest.getPassword()));
        System.out.println("userDetails " + userDetails.toString());
        return userDetailsRepository.save(userDetails);
    }

    public LoginResponse loginUser(UserDetailsRequest userDetailsRequest) {
        LoginResponse loginResponse = new LoginResponse();
        UserDetails userDetails = new UserDetails();
        userDetails = userDetailsRepository.findByEmail(userDetailsRequest.getEmail());
        String pwdHash = userDetails.getPassword();
        Boolean validLogin = checkPassword(userDetailsRequest.getPassword(), pwdHash);
        System.out.println("validLogin " + validLogin);

        UserSessionDetails userSessionDetails = new UserSessionDetails();
        if (validLogin) {

            if(getUserSessionDetails(userDetailsRequest.getEmail()) != null){
                updateSessionDetails(UUID.randomUUID().toString(), "Active", 2, userDetailsRequest.getEmail());
            } else {
                userSessionDetails.setSessionId(UUID.randomUUID().toString());
                userSessionDetails.setUserId(userDetails.getId());
                userSessionDetails.setEmail(userDetails.getEmail());
                userSessionDetails.setUsername(userDetails.getUsername());
                userSessionDetails.setStatus("Active");
                userSessionDetails.setTimeoutDurationInMins(2);
                userSessionDetailsRepository.save(userSessionDetails);
            }

        }
        loginResponse.setId(userDetails.getId());
        loginResponse.setStatus(userDetails.getStatus());
        loginResponse.setValidLogin(validLogin);
        return loginResponse;
    }

    public LogoutResponse logoutUser(String sessionId) {
        LogoutResponse logoutResponse = new LogoutResponse();
        updateSessionStatus( "Inactive", sessionId);
        logoutResponse.setStatus("session deactivated");
        logoutResponse.setMessage("Logged out successfully");
        return logoutResponse;
    }

    public UserSessionDetails getUserSessionDetails(String email) {
        System.out.println("email :" + email);
        return userSessionDetailsRepository.findByEmail(email);
    }

    public UserSessionDetails getUserSessionDetailsBySessionId(String sessionId) {
        return userSessionDetailsRepository.findBySessionId(sessionId);
    }

    public void updateSessionDetails(String sessionId, String status, int timeout, String email) {
        userSessionDetailsRepository.updateSessionDetails(sessionId, status, timeout, email);
    }

    public void updateSessionStatus( String status, String sessionId) {
        userSessionDetailsRepository.updateSessionStatus(status, sessionId);
    }

    /**
     * This method can be used to generate a string representing an account password
     * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
     * hash string of length=60
     * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
     * A workload of 12 is a very reasonable safe default as of 2013.
     * This automatically handles secure 128-bit salt generation and storage within the hash.
     * @param password_plaintext The account's plaintext password as provided during account creation,
     *			     or when changing an account's password.
     * @return String - a string of length 60 that is the bcrypt hashed password in crypt(3) format.
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g. during a login
     * request) with that of a stored hash from a database. The password hash from the database
     * must be passed as the second variable.
     * @param password_plaintext The account's plaintext password, as provided during a login request
     * @param stored_hash The account's stored password hash, retrieved from the authorization database
     * @return boolean - true if the password matches the password of the stored hash, false otherwise
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
