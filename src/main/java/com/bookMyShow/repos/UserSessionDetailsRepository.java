package com.bookMyShow.repos;

import com.bookMyShow.dao.UserDetails;
import com.bookMyShow.dao.UserSessionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserSessionDetailsRepository extends JpaRepository<UserSessionDetails, Integer> {

    @Query(value = "select * from userSessionDetails where email = ?1", nativeQuery = true)
    public UserSessionDetails findByEmail(String email);

    @Query(value = "select * from userSessionDetails where sessionId = ?1", nativeQuery = true)
    public UserSessionDetails findBySessionId(String sessionId);

    @Query(value = "select TIMESTAMPDIFF(MINUTE, createdAt, NOW()) FROM userSessionDetails where sessionId = ?1", nativeQuery = true)
    public int getCreatedAtTimeStampDiff(String sessionId);



    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update userSessionDetails set sessionId = ?1, status = ?2, timeoutDurationInmins = ?3 where email = ?4", nativeQuery = true)
    public void updateSessionDetails(String sessionId, String status, int timeout, String email);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update userSessionDetails set status = ?1 where sessionId = ?2", nativeQuery = true)
    public void updateSessionStatus(String status, String sessionId);

}
