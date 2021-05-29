package com.bookMyShow.repos;

import com.bookMyShow.dao.MovieDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Integer> {

    @Query(value = "Select md.name as movieName, ptmsd.showType , ptmsd.showDateAndTime, td.name as theatreName from movieDetails md \n" +
            "      inner join placeTheatreMovieDetails ptmd  on md.id = ptmd.movieId\n" +
            "      inner join placeTheatreMovieShowDetails ptmsd on ptmsd.placeTheatreMovieId = ptmd.id\n" +
            "      inner join placeTheatreDetails ptd on ptd.id = ptmd.placeTheatreId\n" +
            "      inner join placeDetails pd on pd.id = ptd.placeId\n" +
            "      inner join theatreDetails td on td.id = ptd.theatreId \n" +
            "      where md.name = ?1 and DATE(ptmsd.showDateAndTime) = ?2", nativeQuery=true)
    public List<Object[]> findAllMoviesDetailsByNameAndDate(String movieName, String date);

    @Modifying
    @Transactional
    @Query(value ="insert into movieDetails (name, language, duration) values(?1, ?2, ?3)", nativeQuery = true)
    public void postMovieDetails(String movieName, String movieLanguage, int movieDuration);

    //@Query(value ="select * from movieDetails order by rand() \n#pageable\n", nativeQuery = true)
    //@Query("select * from MovieDetails")
    //public List<MovieDetails> findAll(PageRequest pageable);
}
