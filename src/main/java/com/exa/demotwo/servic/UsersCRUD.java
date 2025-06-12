package com.exa.demotwo.servic;

import com.exa.demotwo.daos.UsersDAO;
import com.exa.demotwo.models.Users;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The UsersCRUD class provides CRUD operations for the Users entity.
 * It interacts with the UsersDAO to perform database operations.
 */
@Service
@Transactional
public class UsersCRUD {
    @Autowired
    private UsersDAO us;

    /**
     * Saves a new user with the provided information.
     *
     * @param name        the name of the user
     * @param email       the email address of the user
     * @param password    the password of the user
     * @param mobilePhone the mobile phone number of the user
     */
    public void saveInfo(String name, String email, String password, String mobilePhone) {
        Users users = new Users(name, password, mobilePhone, email);
        us.save(users);
    }

    /**
     * Updates an existing user's information.
     *
     * @param name        the new name of the user
     * @param email       the new email address of the user
     * @param password    the new password of the user
     * @param mobilePhone the new mobile phone number of the user
     * @param id          the ID of the user to be updated
     * @throws IllegalArgumentException if the user with the given ID does not exist
     */
    public void updateInfo(String name, String email, String password, String mobilePhone, Long id) {
        Optional<Users> existingRecord = us.findById(id);
        if (existingRecord.isPresent()) {
            Users users = existingRecord.get();
            users.setName(name);
            users.setEmail(email);
            users.setPassword(password);
            users.setMobilePhone(mobilePhone);
            us.save(users);
        } else {
            throw new IllegalArgumentException("Invalid input data or record not found.");
        }

    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @throws EntityNotFoundException if the user with the given ID does not exist
     */
    public void delete(Long id) {
        validateId(id);
        if (!us.existsById(id)) {
            throw new EntityNotFoundException("User  with ID " + id + " not found.");

        }
        us.deleteById(id);

    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the user with the specified ID
     * @throws EntityNotFoundException if the user with the given ID does not exist
     */
    public Users getById(Long id) {
        validateId(id);
        return us.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User  with ID " + id + " not found."));

    }

    /**
     * Validates the provided ID.
     *
     * @param id the ID to be validated
     * @throws IllegalArgumentException if the ID is null or less than or equal to zero
     */
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }

    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users
     */
    public List<Users> getAll() {
        return us.findAll();
    }

}
