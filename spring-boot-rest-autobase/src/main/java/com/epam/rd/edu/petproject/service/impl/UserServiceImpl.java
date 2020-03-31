package com.epam.rd.edu.petproject.service.impl;

import com.epam.rd.edu.petproject.converter.ModelConverter;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.exception.EntityNotFoundException;
import com.epam.rd.edu.petproject.exception.UserWithEmailExistException;
import com.epam.rd.edu.petproject.exception.UserWithLoginExistException;
import com.epam.rd.edu.petproject.model.User;
import com.epam.rd.edu.petproject.repository.UserRepository;
import com.epam.rd.edu.petproject.service.UserService;
import com.epam.rd.edu.petproject.utils.HashUtil;
import com.epam.rd.edu.petproject.utils.UserValidator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepo;
  private ModelConverter<User, UserDto> userConverter;
  private UserValidator userValidator;

  @Override
  public UserDto create(UserDto userDto) {
    userValidator.ValidateUserDto(userDto);
    if (userWithEmailExist(userDto.getEmail())) {
      throw new UserWithEmailExistException();
    }
    if (userWithLoginExist(userDto.getLogin())) {
      throw new UserWithLoginExistException();
    }
    userDto.setPassword(HashUtil.messageToHash(userDto.getPassword()));
    return userConverter.toDto(userRepo.save(userConverter.toEntity(userDto)));
  }

  @Override
  public void delete(UUID key) {
    userRepo.deleteById(key);
  }

  @Override
  public void update(UserDto userDto) {
    if (userDto.getPassword() == null) {

    }
    userRepo.save(userConverter.toEntity(userDto));
  }

  @Override
  public UserDto read(UUID key) {
    try {
      return userConverter.toDto(userRepo.findById(key).get());
    } catch (NoSuchElementException ex) {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public List<UserDto> getAll() {
    return userConverter.toDtoList(userRepo.findAll());
  }

  @Override
  public List<UserDto> createAll(List<UserDto> userDtoList) {
    List<User> userList = userConverter.toEntityList(userDtoList);
    return userConverter.toDtoList(userRepo.saveAll(userList));
  }

  @Override
  public void updateAll(List<UserDto> userDtoList) {
    userRepo.saveAll(userConverter.toEntityList(userDtoList));
  }

  @Override
  public UserDto getByLogin(String login) {
    return userConverter.toDto(userRepo.findByLogin(login).get());
  }

  @Override
  public boolean userWithEmailExist(String email) {
    return userRepo.findByEmail(email).isPresent();
  }

  @Override
  public boolean userWithLoginExist(String login) {
    return userRepo.findByLogin(login).isPresent();
  }
}
