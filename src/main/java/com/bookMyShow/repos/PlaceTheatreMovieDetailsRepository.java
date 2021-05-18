package com.bookMyShow.repos;

import com.bookMyShow.dao.PlaceTheatreMovieDetails;
import com.bookMyShow.dao.PlaceTheatreMovieShowDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceTheatreMovieDetailsRepository extends JpaRepository<PlaceTheatreMovieDetails, Integer> {

    @Query(value = "Select distinct(md.name) from \n" +
            "      movieDetails md inner join placeTheatreMovieDetails ptmd  on md.id = ptmd.movieId\n" +
            "      inner join placeTheatreMovieShowDetails ptmsd on ptmsd.placeTheatreMovieId = ptmd.id\n" +
            "      inner join placeTheatreDetails ptd  on  ptd.id = ptmd.placeTheatreId\n" +
            "      inner join placeDetails pd on pd.id = ptd.placeId \n" +
            "where pd.name = ?1 and DATE(ptmsd.showDateAndTime) = ?2", nativeQuery=true)
    public List<String> findMovieByDateAndPlace(String place, String date);
}
