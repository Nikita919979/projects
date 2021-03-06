package com.epam.rd.edu.petproject.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.rd.edu.petproject.SpringAutobaseProjectTest;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.model.User.Role;
import com.epam.rd.edu.petproject.utils.datagenerator.TestUserDataGenerator;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class UserControllerIT extends SpringAutobaseProjectTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldCreateUserThroughAllLayers() throws Exception {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(3);

    //Then
    mockMvc.perform(post("/users")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.uuid", is(userDto.getUuid().toString())))
        .andExpect(jsonPath("$.name", is(userDto.getName())))
        .andExpect(jsonPath("$.familyName", is(userDto.getFamilyName())))
        .andExpect(jsonPath("$.login", is(userDto.getLogin())))
        .andExpect(
            jsonPath("$.role", is(userDto.getRole().name())))
        .andExpect(jsonPath("$.email", is(userDto.getEmail())));

    mockMvc.perform(get("/users/" + userDto.getUuid().toString())
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.uuid", is(userDto.getUuid().toString())))
        .andExpect(jsonPath("$.name", is(userDto.getName())))
        .andExpect(jsonPath("$.familyName", is(userDto.getFamilyName())))
        .andExpect(jsonPath("$.login", is(userDto.getLogin())))
        .andExpect(
            jsonPath("$.role", is(userDto.getRole().name())))
        .andExpect(jsonPath("$.email", is(userDto.getEmail())));
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldUpdateUserThroughAllLayers() throws Exception {
    //Given
    UserDto userDto = TestUserDataGenerator.generateUserDtoWithRandomRole(1);

    //Then
    mockMvc.perform(put("/users")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isOk());

    mockMvc.perform(get("/users/" + userDto.getUuid())
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.uuid", is(userDto.getUuid().toString())))
        .andExpect(jsonPath("$.name", is(userDto.getName())))
        .andExpect(jsonPath("$.familyName", is(userDto.getFamilyName())))
        .andExpect(jsonPath("$.login", is(userDto.getLogin())))
        .andExpect(
            jsonPath("$.role", is(userDto.getRole().name())))
        .andExpect(jsonPath("$.email", is(userDto.getEmail())));
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldDeleteUserThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(delete("/users/317e82f2-7295-11ea-bc55-0242ac130003"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/users/317e82f2-7295-11ea-bc55-0242ac130003"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetUserThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/users/e3f1bb8a-71db-11ea-bc55-0242ac130003"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.uuid", is("e3f1bb8a-71db-11ea-bc55-0242ac130003")))
        .andExpect(jsonPath("$.name", is("Mike")))
        .andExpect(jsonPath("$.familyName", is("Petrov")))
        .andExpect(jsonPath("$.login", is("dispatcher")))
        .andExpect(
            jsonPath("$.role", is(Role.DISPATCHER.name())))
        .andExpect(jsonPath("$.email", is("qwerty919979@gmail.com")));
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetUserListThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].uuid", is("df9e5624-71db-11ea-bc55-0242ac130003")))
        .andExpect(jsonPath("$[1].uuid", is("e3f1bb8a-71db-11ea-bc55-0242ac130003")))
        .andExpect(jsonPath("$[2].uuid", is("317e82f2-7295-11ea-bc55-0242ac130003")));
    ;
  }
}
