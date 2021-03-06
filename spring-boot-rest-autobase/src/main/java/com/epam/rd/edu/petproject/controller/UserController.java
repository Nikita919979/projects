package com.epam.rd.edu.petproject.controller;

import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.service.UserService;
import com.epam.rd.edu.petproject.utils.HashUtil;
import com.epam.rd.edu.petproject.utils.aspect.Timed;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userDtoService;

  @Timed
  @GetMapping("/{uuid}")
  public UserDto getUserDto(@PathVariable String uuid) {
    return userDtoService.read(UUID.fromString(uuid));
  }

  @Timed
  @GetMapping
  public List<UserDto> getAllUsersList() {
    return userDtoService.getAll();
  }

  @Timed
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto createUserDto(@RequestBody UserDto userDto) {
    return userDtoService.create(userDto);
  }

  @Timed
  @PutMapping
  public void updateUserDto(@RequestBody UserDto userDto) {
    userDto.setPassword(HashUtil.messageToHash(userDto.getPassword()));
    userDtoService.update(userDto);
  }

  @Timed
  @DeleteMapping("/{uuid}")
  public void deleteUserDto(@PathVariable String uuid) {
    userDtoService.delete(UUID.fromString(uuid));
  }
}
