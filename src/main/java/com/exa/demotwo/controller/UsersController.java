package com.exa.demotwo.controller;

import java.util.List;
import java.util.Optional;

import com.exa.demotwo.models.Users;
import com.exa.demotwo.servic.UsersCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersCRUD usersCRUD;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersCRUD.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = usersCRUD.getById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody Users user) {

        usersCRUD.saveInfo(user.getName(), user.getEmail(), user.getPassword(), user.getMobilePhone());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody Users user) {
        usersCRUD.updateInfo(user.getName(), user.getEmail(), user.getPassword(), user.getMobilePhone(), id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}