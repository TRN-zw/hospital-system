package com.hospitalSystem.hospital_system.api.dto;

import com.hospitalSystem.hospital_system.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstname;
    private String lastname;

    public static UserDto constructUser (User user) {
        if (Objects.isNull(user)){
            return null;
        }
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail());
    }

}
