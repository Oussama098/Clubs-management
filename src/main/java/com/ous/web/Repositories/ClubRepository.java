package com.ous.web.Repositories;

import com.ous.web.DTO.ClubDTO;
import com.ous.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    @Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Club> findByQuery(@Param("query") String query);

    @Query("select c from Club c  where c.created_by.Username = :username")
    List<Club> findByCreatedBy(@Param("username") String username);


}
