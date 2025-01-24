package com.exa.demotwo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Users class represents a user entity in the application.
 * It contains user-related information such as name, email, mobile phone, and password.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {


    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     * Must not be blank and should have at least 2 characters.
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "First Name should have at least 2 characters")
    private String name;

    /**
     * The mobile phone number of the user.
     * Must not be blank and should have at least 10 characters.
     */
    @NotBlank(message = "MobilePhone cannot be blank ")
    @Size(min = 10, message = "Mobile Phone should have at least 10 characters")
    private String mobilePhone;

    /**
     * The password for the user account.
     * Must not be blank and should have at least 6 characters.
     */
    @NotBlank(message = "Password cannot be blank ")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    /**
     * The email address of the user.
     * Must not be blank, should have at least 5 characters, and must be a valid email format.
     */
    @NotBlank(message = "Email cannot be blank ")
    @Size(min = 5, message = "Email should have at least 5 characters")
    @Email
    private String email;

    /**
     * Constructs a new Users object with the specified name, password, mobile phone, and email.
     *
     * @param name        the name of the user
     * @param password    the password of the user
     * @param mobilePhone the mobile phone number of the user
     * @param email       the email address of the user
     */
    public Users(String name, String password, String mobilePhone, String email) {
        this.email = email;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.name = name;
    }
}
// name, email, phone number, date of birth, id