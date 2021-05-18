package com.bookMyShow.repos;

import com.bookMyShow.dao.PlaceTheatreDetails;
import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceTheatreDetailsRepository extends JpaRepository<PlaceTheatreDetails, Integer> {
}
