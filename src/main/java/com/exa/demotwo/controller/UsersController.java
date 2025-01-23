package com.exa.demotwo.controller;

import com.exa.demotwo.models.Users;
import com.exa.demotwo.servic.UsersCRUD;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersCRUD usersCRUD;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersCRUD.getAll());
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Users user = usersCRUD.getById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        try {
            usersCRUD.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Success: Item deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }

}
