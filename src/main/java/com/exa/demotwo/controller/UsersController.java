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

/**
 * The UsersController class is a REST controller that handles user-related operations.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersCRUD usersCRUD;

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing a list of Users and an HTTP status of 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersCRUD.getAll());
    }

    /**
     * Creates a new user.
     *
     * @param user   the user to be created
     * @param result the binding result containing validation errors, if any
     * @return a ResponseEntity containing a map of validation errors if there are any,
     * or an HTTP status of 201 Created if the user is successfully created
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody Users user, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        usersCRUD.saveInfo(user.getName(), user.getEmail(), user.getPassword(), user.getMobilePhone());
        return ResponseEntity.status(201).build();

    }

    /**
     * Updates an existing user.
     *
     * @param id     the ID of the user to be updated
     * @param user   the updated user information
     * @param result the binding result containing validation errors, if any
     * @return a ResponseEntity containing a map of validation errors if there are any,
     * or an HTTP status of 200 OK if the user is successfully updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Long id, @Valid @RequestBody Users user, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);

        }

        usersCRUD.updateInfo(user.getName(), user.getEmail(), user.getPassword(), user.getMobilePhone(), id);
        return ResponseEntity.ok().build();

    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to be retrieved
     * @return a ResponseEntity containing the user if found, or an appropriate HTTP status
     */
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity containing a success message or an appropriate HTTP status
     */
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
