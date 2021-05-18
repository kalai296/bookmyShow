package com.bookMyShow.requestDto;


public class TheatreShowDetails {

    private String theatreName;
    private String showType;
    private String dateAndTime;

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "TheatreShowDetails{" +
                "theatreName='" + theatreName + '\'' +
                ", showType='" + showType + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}
