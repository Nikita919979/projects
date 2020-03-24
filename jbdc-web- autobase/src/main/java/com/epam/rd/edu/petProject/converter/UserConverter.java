package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserConverter implements ModelConverter<User, UserDto> {

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .familyName(user.getFamilyName())
                .login(user.getLogin())
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .familyName(userDto.getFamilyName())
                .login(userDto.getLogin())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    @Override
    public List<UserDto> toDtoList(List<User> userList) {
        List<UserDto> result = new ArrayList<>();
        for (User user : userList) {
            result.add(toDto(user));
        }
        return result;
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtoList) {
        List<User> result = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            result.add(toEntity(userDto));
        }
        return result;
    }
}
