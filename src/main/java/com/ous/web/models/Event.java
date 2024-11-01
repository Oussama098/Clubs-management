package com.ous.web.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "the name field should not be empty")
    private String name;
    @NotEmpty(message = "the email field should not be empty")
    private String Email;
    @NotEmpty(message = "the description field should not be empty")
    private String Description;
    @NotEmpty(message = "the location field should not be empty")
    private String Location;
    @NotNull
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "club_id"  , nullable = false)
    private Club club;
}
