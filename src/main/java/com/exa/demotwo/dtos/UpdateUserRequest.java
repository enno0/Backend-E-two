package com.exa.demotwo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotBlank
    @Size(min = 2)
    private String name;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    @Pattern(regexp = "\\d{10}")
    private String mobilePhone;
}
