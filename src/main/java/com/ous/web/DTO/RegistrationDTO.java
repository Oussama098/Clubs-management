package com.ous.web.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDTO {
    private int id;
    @NotEmpty
    private String Username;
    @NotEmpty
    private String Email;
    @NotEmpty
    private String Password;
}
