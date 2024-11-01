package com.ous.web.security;

import com.ous.web.Repositories.UserRepository;
import com.ous.web.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (userEntity != null) {
            User authUser = new User(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getRole().stream().map((role) -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList())
            );
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }

    }

}
