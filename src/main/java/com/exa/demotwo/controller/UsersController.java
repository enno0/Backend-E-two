package com.exa.demotwo.controller;

import com.exa.demotwo.models.Users;
import com.exa.demotwo.servic.UsersCRUD;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody Users user, BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(error ->

            errors.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);

        }

        usersCRUD.saveInfo(user.getName(), user.getEmail(), user.getPassword(), user.getMobilePhone());

        return ResponseEntity.status(201).build();

    }

    @PutMapping("/{id}")

    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Long id, @Valid @RequestBody Users user,
            BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(error ->

            errors.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);

        }

        usersCRUD.updateInfo(user.getName(), user.getEmail(), user.getPassword(), user.getMobilePhone(), id);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersCRUD.delete(id);
        return ResponseEntity.noContent().build();
    }
}