package com.ous.web.Services;

import com.ous.web.DTO.ClubDTO;
import com.ous.web.Repositories.ClubRepository;
import com.ous.web.Repositories.UserRepository;
import com.ous.web.models.Club;
import com.ous.web.models.UserEntity;
import com.ous.web.security.ServiceUtil;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClubService {
    @Autowired
    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    public ClubService(ClubRepository clubRepository , UserRepository userRepository , UserService userService) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Club> getAllClubs() {
        System.out.println(ServiceUtil.getServiceUsername());
        return clubRepository.findAll();
    }

    public List<Club> getClubsByCreatedBy() {
        UserEntity user = this.userService.getUserByUsername(ServiceUtil.getServiceUsername());
        if (user != null) {
            return this.clubRepository.findByCreatedBy(user.getUsername());
        }
        return null;
    }

    public void addClub(Club club) {
        String username = ServiceUtil.getServiceUsername();
        UserEntity user = this.userRepository.findByUsername(username);
        club.setCreated_by(user);
        this.clubRepository.save(club);
    }

    public Club getClubById(long id) {
        return this.clubRepository.findById(id).get();
    }

    public void updateClub(long id , Club club) {
        Club clubNeeded = this.getClubById(id);
        String username  = ServiceUtil.getServiceUsername();
        UserEntity user = this.userRepository.findByUsername(username);
        if (clubNeeded.getId() == club.getId()) {
            clubNeeded.setTitle(club.getTitle());
            clubNeeded.setPhotoURL(club.getPhotoURL());
            clubNeeded.setContact(club.getContact());
            clubNeeded.setCreated_by(user);
            clubRepository.save(clubNeeded);
        }
    }

    public void deleteClub(long id) {
        this.clubRepository.deleteById(id);
    }

    public ClubDTO mapToClubDTO(Club club){
        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoURL(club.getPhotoURL())
                .contact(club.getContact())

                .build();
        return clubDTO;

    }

    public List<Club> findByQuery(String query) {
        return this.clubRepository.findByQuery(query);
    }

}
