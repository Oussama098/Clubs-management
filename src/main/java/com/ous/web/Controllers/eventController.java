package com.ous.web.Controllers;

import com.ous.web.Services.ClubService;
import com.ous.web.Services.EventService;
import com.ous.web.models.Club;
import com.ous.web.models.Event;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class eventController {
    private EventService eventService;
    private ClubService clubService;
    public eventController(EventService eventService , ClubService clubService) {
        this.eventService = eventService;
        this.clubService = clubService;
    }

    @GetMapping(path = "/event/{club_id}/add")
    public String addEvent(@PathVariable("club_id") long club_id , Model model) {
        Event event = new Event();
        model.addAttribute("clubId" , club_id);
        model.addAttribute("event", event);
        return "Event/eventAdd";
    }

    @PostMapping(path = "/event/{club_id}/add")
    public String addEvent(@Valid @PathVariable("club_id") long club_id , @ModelAttribute Event  event, Model model , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Event/"+club_id +"/eventAdd";
        }
        this.eventService.createEvent(club_id,event);
        return "redirect:/club";
    }

    @GetMapping(path = "/event/{club_id}/{event_id}/delete")
    public String deleteEvent(@PathVariable("event_id") long event_id , @PathVariable("club_id") long club_id) {
        this.eventService.deleteEvent(event_id);
        return "redirect:/club/"+club_id+"/detail";
    }

    @GetMapping(path = "/event/{club_id}/{event_id}/update")
    public String update(@PathVariable("event_id") long event_id, @PathVariable("club_id") long club_id, Model model) {
        Event event = this.eventService.getEventById(event_id);
        Club club = this.clubService.getClubById(club_id);
        model.addAttribute("club" , club);
        model.addAttribute("event", event);
        return "Event/eventUpdate";
    }

    @PostMapping(path = "/event/{club_id}/{event_id}/update")
    public String updateEvent(@PathVariable("event_id") long event_id ,@PathVariable("club_id") long club_id, @Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Event/eventUpdate";
        }
        this.eventService.updateEvent(event_id,club_id,event);
        return "redirect:/club/"+club_id+"/detail";
    }

    @GetMapping(path = "/event/add")
    public String addEvent(Model model) {
        List<Club> clubs = this.clubService.getClubsByCreatedBy();
        model.addAttribute("clubs", clubs);
        model.addAttribute("event", new Event());
        return "Event/eventAddC";
    }

    @PostMapping(path = "event/add")
    public String addEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Event/eventAddc";
        }
        this.eventService.create(event);
        return "redirect:/club";
    }
}
