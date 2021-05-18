package com.bookMyShow.repos;

import com.bookMyShow.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    @Query(value = "select * from userDetails where email = ?1", nativeQuery = true)
    public UserDetails findByEmail(String email);
}
