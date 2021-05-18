package com.bookMyShow.repos;

import com.bookMyShow.dao.TheatreDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreDetailsRepository extends JpaRepository<TheatreDetails, Integer> {
}
