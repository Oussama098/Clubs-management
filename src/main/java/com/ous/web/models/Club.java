package com.ous.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "the title should not be empty")
    private String title;
    @NotEmpty(message = "the photoURL should not be empty")
    private String photoURL;
    @Column(unique = true)
    @NotEmpty(message = "the contact should not be empty")
    private String contact;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by" , nullable = false)
    private UserEntity created_by;
    @OneToMany(mappedBy = "club" , cascade = CascadeType.REMOVE)
    private Set<Event> events = new HashSet<>();
}
