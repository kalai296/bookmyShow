package com.bookMyShow.dao;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="userSessionDetails")
public class UserSessionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String sessionId;
    private String username;
    private String email;
    private String status;
    private int timeoutDurationInMins;
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimeoutDurationInMins() {
        return timeoutDurationInMins;
    }

    public void setTimeoutDurationInMins(int timeoutDurationInMins) {
        this.timeoutDurationInMins = timeoutDurationInMins;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserSessionDetails{" +
                "id=" + id +
                ", userId=" + userId +
                ", sessionId='" + sessionId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", timeoutDurationInMins=" + timeoutDurationInMins +
                ", createdAt=" + createdAt +
                '}';
    }
}
