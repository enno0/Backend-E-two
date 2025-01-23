package com.exa.demotwo.servic;

import com.exa.demotwo.dataaccessopject.UsersDAO;
import com.exa.demotwo.models.Users;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersCRUD {
    @Autowired
    private UsersDAO us;

    public void saveInfo(String name, String email, String password, String mobilePhone) {
        Users users = new Users(name, password, mobilePhone, email);
        us.save(users);
    }

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

    public void delete(Long id) {
        validateId(id);
        if (!us.existsById(id)) {
            throw new EntityNotFoundException("User  with ID " + id + " not found.");

        }
        us.deleteById(id);

    }

    public Users getById(Long id) {
        validateId(id);
        return us.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User  with ID " + id + " not found."));

    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID.");
        }

    }

    public List<Users> getAll() {

        return us.findAll();
    }

}
