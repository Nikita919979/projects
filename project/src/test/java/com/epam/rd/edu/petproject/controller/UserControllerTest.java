package com.epam.rd.edu.petproject.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.service.UserService;
import com.epam.rd.edu.petproject.utils.datagenerator.TestUserDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  private final UserService userService = Mockito.mock(UserService.class);
  private final UserController sut = new UserController(userService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();

  @Test
  public void shouldReturnListOfUserDtoDetails() throws Exception {
    //Given
    List<UserDto> inputUserDtoList = TestUserDataGenerator.generateRequestUserDtoList(2);
    given(userService.getAll()).willReturn(inputUserDtoList);

    //Then
    mockMvc.perform(get("/users")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(inputUserDtoList.get(0).getId())))
        .andExpect(jsonPath("$[0].name", is(inputUserDtoList.get(0).getName())))
        .andExpect(jsonPath("$[0].familyName", is(inputUserDtoList.get(0).getFamilyName())))
        .andExpect(jsonPath("$[0].login", is(inputUserDtoList.get(0).getLogin())))
        .andExpect(jsonPath("$[0].role", is(inputUserDtoList.get(0).getRole().name())))
        .andExpect(jsonPath("$[0].email", is(inputUserDtoList.get(0).getEmail())))
        .andExpect(jsonPath("$[1].id", is(inputUserDtoList.get(1).getId())))
        .andExpect(jsonPath("$[1].name", is(inputUserDtoList.get(1).getName())))
        .andExpect(jsonPath("$[1].familyName", is(inputUserDtoList.get(1).getFamilyName())))
        .andExpect(jsonPath("$[1].login", is(inputUserDtoList.get(1).getLogin())))
        .andExpect(jsonPath("$[1].role", is(inputUserDtoList.get(1).getRole().name())))
        .andExpect(jsonPath("$[1].email", is(inputUserDtoList.get(1).getEmail())));
  }

  @Test
  public void shouldReturnUserDtoDetails() throws Exception {
    //Given
    UserDto inputUserDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    given(userService.read(1)).willReturn(inputUserDto);

    //Then
    mockMvc.perform(get("/users/1")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(inputUserDto.getId())))
        .andExpect(jsonPath("$.name", is(inputUserDto.getName())))
        .andExpect(jsonPath("$.familyName", is(inputUserDto.getFamilyName())))
        .andExpect(jsonPath("$.login", is(inputUserDto.getLogin())))
        .andExpect(jsonPath("$.role", is(inputUserDto.getRole().name())))
        .andExpect(jsonPath("$.email", is(inputUserDto.getEmail())));
  }

  @Test
  public void shouldReturnCreatedUserWithStatusCreated() throws Exception {
    //Given
    UserDto inputUserDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    given(userService.create(inputUserDto)).willReturn(inputUserDto.clone());
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(post("/users")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputUserDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(inputUserDto.getId())))
        .andExpect(jsonPath("$.name", is(inputUserDto.getName())))
        .andExpect(jsonPath("$.familyName", is(inputUserDto.getFamilyName())))
        .andExpect(jsonPath("$.login", is(inputUserDto.getLogin())))
        .andExpect(jsonPath("$.role", is(inputUserDto.getRole().name())))
        .andExpect(jsonPath("$.email", is(inputUserDto.getEmail())));
  }

  @Test
  public void shouldUpdateWithStatusOk() throws Exception {
    //Given
    UserDto inputUserDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(put("/users")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputUserDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldDeleteWithStatusOk() throws Exception {
    //Given
    UserDto inputUserDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(delete("/users/1")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputUserDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }
}

