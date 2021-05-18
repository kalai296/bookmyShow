package com.bookMyShow.responseDto;

public class LoginResponse {

    int id;
    String status;

    public Boolean getValidLogin() {
        return validLogin;
    }

    public void setValidLogin(Boolean validLogin) {
        this.validLogin = validLogin;
    }

    Boolean validLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
