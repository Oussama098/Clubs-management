package com.ous.web.Repositories;

import com.ous.web.models.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.Username = :username")
    public UserEntity findByUsername(String username);
    @Query("select u from UserEntity u where u.Email= :email")
    public UserEntity findByEmail(String email);

}
