package com.epam.rd.edu.petproject.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.epam.rd.edu.petproject.converter.UserConverter;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.exception.UserWithEmailExistException;
import com.epam.rd.edu.petproject.exception.UserWithLoginExistException;
import com.epam.rd.edu.petproject.model.User;
import com.epam.rd.edu.petproject.repository.UserRepository;
import com.epam.rd.edu.petproject.service.impl.UserServiceImpl;
import com.epam.rd.edu.petproject.utils.UserValidator;
import com.epam.rd.edu.petproject.utils.datagenerator.TestUserDataGenerator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;

public class UserServiceImplTest {

  private final UserRepository userRepository = mock(UserRepository.class);
  private final UserConverter userConverter = mock(UserConverter.class);
  private final UserValidator userValidator = mock(UserValidator.class);
  private final UserServiceImpl sut = new UserServiceImpl(userRepository, userConverter,
      userValidator);

  @Test
  public void shouldReturnCreatedEntity() {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    User user = TestUserDataGenerator.getUser(userDto);
    doReturn(user).when(userRepository).save(user);
    doReturn(user).when(userConverter).toEntity(userDto);
    doReturn(userDto).when(userConverter).toDto(user);

    //When
    UserDto userDtoVerify = sut.create(userDto);

    //Then
    verify(userRepository).save(user);
    assertThat(userDtoVerify, hasProperty("uuid", is(userDto.getUuid())));
    assertThat(userDtoVerify, hasProperty("name", is(userDto.getName())));
    assertThat(userDtoVerify, hasProperty("familyName", is(userDto.getFamilyName())));
    assertThat(userDtoVerify, hasProperty("login", is(userDto.getLogin())));
    assertThat(userDtoVerify, hasProperty("password", is(userDto.getPassword())));
    assertThat(userDtoVerify, hasProperty("role", is(userDto.getRole())));
    assertThat(userDtoVerify, hasProperty("email", is(userDto.getEmail())));
  }

  @Test(expected = UserWithEmailExistException.class)
  public void shouldReturnUserWithEmailExistException() {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    User user = TestUserDataGenerator.getUser(userDto);
    Optional<User> userOptional = Optional.of(user);
    doReturn(user).when(userRepository).save(user);
    doReturn(user).when(userConverter).toEntity(userDto);
    doReturn(userDto).when(userConverter).toDto(user);
    doReturn(userOptional).when(userRepository).findByEmail(user.getEmail());

    //When
    sut.create(userDto);

    //Then
    verify(userRepository).save(user);
    verify(userRepository).findByEmail(user.getEmail());
  }

  @Test(expected = UserWithLoginExistException.class)
  public void shouldReturnUserWithLoginExistException() {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    User user = TestUserDataGenerator.getUser(userDto);
    Optional<User> userOptional = Optional.of(user);
    doReturn(user).when(userRepository).save(user);
    doReturn(user).when(userConverter).toEntity(userDto);
    doReturn(userDto).when(userConverter).toDto(user);
    doReturn(userOptional).when(userRepository).findByLogin(user.getLogin());

    //When
    sut.create(userDto);

    //Then
    verify(userRepository).save(user);
    verify(userRepository).findByLogin(user.getLogin());
  }

  @Test
  public void shouldDeleteUser() {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    User user = TestUserDataGenerator.getUser(userDto);
    Optional<User> userOptional = Optional.of(user);
    doReturn(userOptional).when(userRepository).findById(user.getUuid());

    //When
    sut.delete(UUID.fromString("df9e5624-71db-11ea-bc55-0242ac130003"));

    //Then
    verify(userRepository).delete(user);
  }

  @Test
  public void shouldUpdateUser() {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    User user = TestUserDataGenerator.getUser(userDto);
    doReturn(user.clone()).when(userRepository).save(user);
    doReturn(user).when(userConverter).toEntity(userDto);

    //When
    sut.update(userDto);

    //Then
    verify(userRepository).save(user);
  }

  @Test
  public void shouldReturnFoundedEntity() {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    User user = TestUserDataGenerator.getUser(userDto);
    Optional<User> userOptional = Optional.of(user);
    doReturn(userOptional).when(userRepository)
        .findById(UUID.fromString("df9e5624-71db-11ea-bc55-0242ac130003"));
    doReturn(userDto).when(userConverter).toDto(user);

    //When
    UserDto userDtoVerify = sut.read(UUID.fromString("df9e5624-71db-11ea-bc55-0242ac130003"));

    //Then
    verify(userConverter).toDto(user);
    assertThat(userDtoVerify, hasProperty("uuid", is(userDto.getUuid())));
    assertThat(userDtoVerify, hasProperty("name", is(userDto.getName())));
    assertThat(userDtoVerify, hasProperty("familyName", is(userDto.getFamilyName())));
    assertThat(userDtoVerify, hasProperty("login", is(userDto.getLogin())));
    assertThat(userDtoVerify, hasProperty("password", is(userDto.getPassword())));
    assertThat(userDtoVerify, hasProperty("role", is(userDto.getRole())));
    assertThat(userDtoVerify, hasProperty("email", is(userDto.getEmail())));
  }

  @Test
  public void shouldReturnAllFoundedEntities() {
    //Given
    List<UserDto> userDtoList = TestUserDataGenerator.generateRequestUserDtoList(5);
    List<User> userList = TestUserDataGenerator.getUserList(userDtoList);
    doReturn(userList).when(userRepository).findAll();
    doReturn(userDtoList).when(userConverter).toDtoList(userList);

    //When
    List<UserDto> carDtoReturnList = sut.getAll();

    //Then
    verify(userRepository).findAll();
    userDtoList.forEach(
        entity -> {
          assertThat(carDtoReturnList, hasItem(hasProperty("uuid", is(entity.getUuid()))));
          assertThat(carDtoReturnList, hasItem(hasProperty("name", is(entity.getName()))));
          assertThat(carDtoReturnList,
              hasItem(hasProperty("familyName", is(entity.getFamilyName()))));
          assertThat(carDtoReturnList, hasItem(hasProperty("login", is(entity.getLogin()))));
          assertThat(carDtoReturnList, hasItem(hasProperty("password", is(entity.getPassword()))));
          assertThat(carDtoReturnList, hasItem(hasProperty("role", is(entity.getRole()))));
          assertThat(carDtoReturnList, hasItem(hasProperty("email", is(entity.getEmail()))));
        }
    );
  }

  @Test
  public void shouldUpdateAllUsers() {
    //Given
    List<UserDto> userDtoList = TestUserDataGenerator.generateRequestUserDtoList(5);
    List<User> userList = TestUserDataGenerator.getUserList(userDtoList);
    doReturn(userList).when(userRepository).saveAll(userList);
    doReturn(userList).when(userConverter).toEntityList(userDtoList);

    //When
    sut.updateAll(userDtoList);

    //Then
    verify(userRepository).saveAll(userList);
  }

  @Test
  public void shouldReturnAllCreatedEntities() {
    //Given
    List<UserDto> userDtoList = TestUserDataGenerator.generateRequestUserDtoList(5);
    List<User> userList = TestUserDataGenerator.getUserList(userDtoList);
    doReturn(userList).when(userRepository).saveAll(userList);
    doReturn(userDtoList).when(userConverter).toDtoList(userList);
    doReturn(userList).when(userConverter).toEntityList(userDtoList);

    //When
    List<UserDto> userDtoReturnList = sut.createAll(userDtoList);

    //Then
    verify(userRepository).saveAll(userList);
    userDtoList.forEach(
        entity -> {
          assertThat(userDtoReturnList, hasItem(hasProperty("uuid", is(entity.getUuid()))));
          assertThat(userDtoReturnList, hasItem(hasProperty("name", is(entity.getName()))));
          assertThat(userDtoReturnList,
              hasItem(hasProperty("familyName", is(entity.getFamilyName()))));
          assertThat(userDtoReturnList, hasItem(hasProperty("login", is(entity.getLogin()))));
          assertThat(userDtoReturnList, hasItem(hasProperty("password", is(entity.getPassword()))));
          assertThat(userDtoReturnList, hasItem(hasProperty("role", is(entity.getRole()))));
          assertThat(userDtoReturnList, hasItem(hasProperty("email", is(entity.getEmail()))));
        }
    );
  }
}
