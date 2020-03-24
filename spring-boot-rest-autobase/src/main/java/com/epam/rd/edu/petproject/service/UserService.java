package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.UserDto;
import java.util.List;

public interface UserService {

  UserDto create(UserDto userDto);

  void delete(Integer key);

  void update(UserDto userDto);

  UserDto read(Integer key);

  List<UserDto> getAll();

  List<UserDto> createAll(List<UserDto> userDtoList);

  void updateAll(List<UserDto> userDtoList);

  UserDto getByLogin(String login);

  boolean userWithEmailExist(String email);

  boolean userWithLoginExist(String login);
}
