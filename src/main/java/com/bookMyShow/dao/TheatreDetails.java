package com.bookMyShow.dao;

import javax.persistence.*;

@Entity
@Table(name = "theatreDetails")
public class TheatreDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "theatreDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
