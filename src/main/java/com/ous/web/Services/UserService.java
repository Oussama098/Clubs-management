package com.ous.web.Services;

import com.ous.web.DTO.RegistrationDTO;
import com.ous.web.Repositories.RoleRepository;
import com.ous.web.Repositories.UserRepository;
import com.ous.web.models.Role;
import com.ous.web.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void saveUser(RegistrationDTO registrationDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRole(Collections.singletonList(role));
        this.userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
