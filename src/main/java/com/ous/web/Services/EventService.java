package com.ous.web.Services;

import com.ous.web.Repositories.EventRepository;
import com.ous.web.models.Club;
import com.ous.web.models.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;
    private ClubService clubService;
    public EventService(EventRepository eventRepository , ClubService clubService) {
        this.eventRepository = eventRepository;
        this.clubService = clubService;
    }

    public void createEvent(long club_id , Event event) {
        Club club =this.clubService.getClubById(club_id);
        event.setClub(club);
        this.eventRepository.save(event);
    }
    public List<Event> getAllEventsByClub(long club_id) {
        return this.eventRepository.findAllEventByClub(club_id);
    }

    public void deleteEvent(long id) {
        this.eventRepository.deleteById(id);
    }

    public Event getEventById(long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public void updateEvent(long id ,long club_id ,Event event) {
        Event eventP = getEventById(id);
        Club ClubP = this.clubService.getClubById(club_id);
        eventP.setName(event.getName());
        eventP.setDescription(event.getDescription());
        eventP.setEmail(event.getEmail());
        eventP.setLocation(event.getLocation());
        eventP.setCreatedAt(event.getCreatedAt());
        eventP.setUpdatedAt(event.getUpdatedAt());
        eventP.setClub(ClubP);
        this.eventRepository.save(eventP);
    }

    public void create(Event event){
        this.eventRepository.save(event);
    }
}
