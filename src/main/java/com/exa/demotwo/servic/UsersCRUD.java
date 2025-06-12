package com.exa.demotwo.servic;

import com.exa.demotwo.daos.UsersDAO;
import com.exa.demotwo.dtos.CreateUserRequestDTO;
import com.exa.demotwo.dtos.UpdateUserRequest;
import com.exa.demotwo.dtos.UserResponseDTO;
import com.exa.demotwo.mappers.UserMapper;
import com.exa.demotwo.models.Role;
import com.exa.demotwo.models.Users;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersCRUD {
    private final UsersDAO usersDAO;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        Users user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // Default role
        Users savedUser = usersDAO.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponseDTO updateUser(UpdateUserRequest request, Long id) {
        Users user = usersDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userMapper.updateUserFromDto(request, user);
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Users updatedUser = usersDAO.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!usersDAO.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        usersDAO.deleteById(id);
    }

    public UserResponseDTO getUserById(Long id) {
        Users user = usersDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return usersDAO.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }
}