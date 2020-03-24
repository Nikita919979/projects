package com.epam.rd.edu.petproject.utils.datagenerator;

import com.epam.rd.edu.petproject.converter.UserConverter;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.model.User;
import com.epam.rd.edu.petproject.model.User.Role;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUserDataGenerator {

  private final UserConverter userConverter = new UserConverter();

  public List<UserDto> generateRequestUserDtoList(int count) {
    return IntStream.range(0, count)
        .mapToObj(TestUserDataGenerator::generateUserDtoWithRandomRole)
        .collect(Collectors.toList());
  }

  public UserDto generateUserDto(int counter, User.Role role) {
    return UserDto.builder()
        .id(counter)
        .email("testEmail" + counter + "@gmail.com")
        .familyName("testFamilyName" + counter)
        .login("testLogin" + counter)
        .name("testName" + counter)
        .password("testPass" + counter)
        .role(role)
        .build();
  }

  public UserDto generateUserDtoWithRandomRole(int counter) {
    return UserDto.builder()
        .id(counter)
        .email("testEmail" + counter + "@gmail.com")
        .familyName("testFamilyName" + counter)
        .login("testLogin" + counter)
        .name("testName" + counter)
        .password("testPass" + counter)
        .role(Arrays.stream(Role.values()).findAny().get())
        .build();
  }

  public User getUser(UserDto userDto) {
    return userConverter.toEntity(userDto);
  }

  public List<User> getUserList(List<UserDto> userDtoList) {
    return userConverter.toEntityList(userDtoList);
  }
}
