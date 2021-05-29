package com.bookMyShow.responseDto;


public class MovieDetailsResponse {

    private String name;
    private String language;
    private int duration;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "movieDetails{" +
                " name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", duration=" + duration +
                '}';
    }
}
