package com.exa.demotwo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String name;
    private String mobilePhone;
    private String password;
    private String email;

    public Users(String email, String password, String mobilePhone, String name) {
        this.email = email;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.name = name;
    }
}
// name, email, phone number, date of birth, id