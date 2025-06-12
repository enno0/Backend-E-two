package com.exa.demotwo.mappers;

import com.exa.demotwo.dtos.CreateUserRequestDTO;
import com.exa.demotwo.dtos.UpdateUserRequest;
import com.exa.demotwo.dtos.UserResponseDTO;
import com.exa.demotwo.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toUserResponse(Users user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .mobilePhone(user.getMobilePhone())
                .role(user.getRole())
                .build();
    }

    public Users toUser(CreateUserRequestDTO dto) {
        return Users.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // Will be encoded in service
                .mobilePhone(dto.getMobilePhone())
                .build();
    }

    public void updateUserFromDto(UpdateUserRequest dto, Users user) {
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getMobilePhone() != null) user.setMobilePhone(dto.getMobilePhone());
    }
}