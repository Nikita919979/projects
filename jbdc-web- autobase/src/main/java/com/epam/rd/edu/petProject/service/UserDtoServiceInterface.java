package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.exception.DaoException;

import java.util.List;

public interface UserDtoServiceInterface {

    UserDto create(UserDto userDto);

    boolean delete(Integer key);

    boolean update(UserDto userDto);

    UserDto read(Integer key);

    List<UserDto> getAll();

    List<UserDto> create(List<UserDto> userDtoList);

    boolean update(List<UserDto> userDtoList);

    UserDto getByLogin(String login);

    boolean userWithEmailExist(UserDto userDto);

    boolean userWithLoginExist(UserDto userDto);
}
