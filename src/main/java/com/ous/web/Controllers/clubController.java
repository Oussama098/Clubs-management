package com.ous.web.Controllers;


import com.ous.web.Services.ClubService;
import com.ous.web.Services.EventService;
import com.ous.web.Services.UserService;
import com.ous.web.models.Club;
import com.ous.web.models.Event;
import com.ous.web.models.UserEntity;
import com.ous.web.security.ServiceUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(path = "club")
public class clubController {
    private final ClubService clubservice;
    private final EventService eventservice;
    private final UserService userservice;
    @Autowired
    public clubController(ClubService clubservice , EventService eventservice , UserService userservice) {
        this.clubservice = clubservice;
        this.eventservice = eventservice;
        this.userservice = userservice;
    }

    @GetMapping(path = "/club")
    public String getAllClubs(Model model) {
        List<Club> club= this.clubservice.getAllClubs();
        UserEntity user = new UserEntity();
        String username = ServiceUtil.getServiceUsername();
        if(username != null) {
            user = this.userservice.getUserByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", club);
        return "club/club-list";
    }

    @GetMapping(path = "/club/new")
    public String NewClub(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "Club/NewClub";
    }

    @PostMapping(path = "/club/new")
    public String NewClubSubmit(@Valid @ModelAttribute("club") Club club , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "Club/NewClub";
        }
        this.clubservice.addClub(club);
        return "redirect:/club";
    }

    @GetMapping(path = "/club/{id}/update")
    public String UpdateClub(@PathVariable("id") long id, Model model) {
        Club club = this.clubservice.getClubById(id);
        model.addAttribute("club", club);
        return "Club/UpdateClub";
    }

    @PostMapping(path = "/club/{id}/update")
    public String UpdateClubSubmit(@PathVariable("id") long id , @Valid @ModelAttribute("club") Club club, BindingResult bindingResult ) {
        if(bindingResult.hasErrors()) {
            return "Club/UpdateClub";
        }
        this.clubservice.updateClub(id , club);
        return "redirect:/club";
    }

    @GetMapping(path = "/club/{id}/delete")
    public String DeleteClub(@PathVariable("id") long id) {
        this.clubservice.deleteClub(id);
        return "redirect:/club";
    }

    @GetMapping(path = "/club/{id}/detail")
    public String detailClub(@PathVariable("id") long id, Model model) {
        Club club = this.clubservice.getClubById(id);
        List<Event> events = this.eventservice.getAllEventsByClub(id);
        UserEntity user = new UserEntity();
        String username = ServiceUtil.getServiceUsername();
        if(username != null) {
            user = this.userservice.getUserByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", club);
        model.addAttribute("events", events);
        return "Club/detailClub";
    }

    @GetMapping(path = "/club/search")
    public String searchClub(@RequestParam String query, Model model) {
        List<Club> club = new ArrayList<Club>();
        if(query == null || query.isEmpty()) {
            club = this.clubservice.getAllClubs();
        }else {
            club = this.clubservice.findByQuery(query);
        }

        model.addAttribute("clubs", club);
        return "Club/club-list";
    }

//    @GetMapping(path = "/club/events")
//    public String getAllEventsClubs(Model model) {
//        List<Club> club= this.clubservice.getAllClubs();
//        List<Event> events = new ArrayList<>();
//        for(Club c : club) {
//           events.addAll(this.eventservice.getAllEventsByClub(c.getId()));
//        }
//        model.addAttribute("events", events);
//        model.addAttribute("clubs", club);
//        return "event/events-list";
//    }
    @GetMapping(path = "/club/events")
    public String getAllEventsClubs(Model model) {
        List<Club> clubs = this.clubservice.getAllClubs();
        Map<Club, List<Event>> clubEventsMap = new HashMap<>();

        for (Club club : clubs) {
            List<Event> events = this.eventservice.getAllEventsByClub(club.getId());
            clubEventsMap.put(club, events);
        }

        model.addAttribute("clubEventsMap", clubEventsMap);
        return "event/events-list";
    }




}
