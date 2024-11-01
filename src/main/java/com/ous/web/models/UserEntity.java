package com.ous.web.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Username;
    private String Email;
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles" ,
            joinColumns = {@JoinColumn(name = "user_id" )},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> role = new ArrayList<>();

    @OneToMany(mappedBy = "created_by")
    private List<Club> createdEntities;
}
