package com.ous.web.Repositories;

import com.ous.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.club.id = :id")
    List<Event> findAllEventByClub(@Param("id") Long id);
}
