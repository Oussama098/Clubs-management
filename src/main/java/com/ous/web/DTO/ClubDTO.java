package com.ous.web.DTO;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
@Builder
@Data
public class ClubDTO {
    private int id;
    private String title;
    private String photoURL;
    private String contact;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
