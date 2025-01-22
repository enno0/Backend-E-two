package com.exa.demotwo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "First Name should have at least 2 characters")
    private String name;
    @NotBlank(message = "MobilePhone cannot be blank ")
    @Size(min = 10, message = "Mobile Phone should have at least 10 characters")
    private String mobilePhone;
    @NotBlank(message = "Password cannot be blank ")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
    @NotBlank(message = "Email cannot be blank ")
    @Size(min = 5, message = "Email should have at least 5 characters")
    @Email
    private String email;

    public Users(String name, String password, String mobilePhone, String email) {
        this.email = email;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.name = name;
    }
}
// name, email, phone number, date of birth, id