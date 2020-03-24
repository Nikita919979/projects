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
import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.model.Transit;
import com.epam.rd.edu.petproject.model.Transit.Status;
import com.epam.rd.edu.petproject.model.User;
import com.epam.rd.edu.petproject.model.User.Role;
import com.epam.rd.edu.petproject.utils.datagenerator.TestTransitDataGenerator;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class TransitControllerIT extends SpringAutobaseProjectTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldCreateTransitThroughAllLayers() throws Exception {
    //Given
    TransitDto transitDto = TestTransitDataGenerator.generateRealEntity(3);

    //Then
    mockMvc.perform(post("/transits")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(transitDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(transitDto.getId())))
        .andExpect(jsonPath("$.status", is(transitDto.getStatus().name())))
        .andExpect(
            jsonPath("$.city_from.id", is(transitDto.getCity_from().getId())))
        .andExpect(
            jsonPath("$.city_from.name", is(transitDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.id", is(transitDto.getCity_to().getId())))
        .andExpect(jsonPath("$.city_to.name", is(transitDto.getCity_to().getName())))
        .andExpect(jsonPath("$.user.id", is(transitDto.getUser().getId())))
        .andExpect(jsonPath("$.user.name", is(transitDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(transitDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(transitDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.role", is(transitDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(transitDto.getUser().getEmail())))
        .andExpect(jsonPath("$.driver.id", is(transitDto.getDriver().getId())))
        .andExpect(
            jsonPath("$.driver.name", is(transitDto.getDriver().getName())))
        .andExpect(jsonPath("$.driver.familyName",
            is(transitDto.getDriver().getFamilyName())))
        .andExpect(
            jsonPath("$.driver.login", is(transitDto.getDriver().getLogin())))
        .andExpect(
            jsonPath("$.driver.role",
                is(transitDto.getDriver().getRole().name())))
        .andExpect(
            jsonPath("$.driver.email", is(transitDto.getDriver().getEmail())))
        .andExpect(jsonPath("$.car.id", is(transitDto.getCar().getId())))
        .andExpect(
            jsonPath("$.car.model", is(transitDto.getCar().getModel().name())))
        .andExpect(
            jsonPath("$.car.carNumber", is(transitDto.getCar().getCarNumber())))
        .andExpect(jsonPath("$.car.carTechnicalPassport",
            is(transitDto.getCar().getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.car.releaseDate",
                is(transitDto.getCar().getReleaseDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$.car.fully_Functional",
            is(transitDto.getCar().isFully_Functional())));

    mockMvc.perform(get("/transits/3")
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(transitDto.getId())))
        .andExpect(jsonPath("$.status", is(transitDto.getStatus().name())))
        .andExpect(
            jsonPath("$.city_from.id", is(transitDto.getCity_from().getId())))
        .andExpect(
            jsonPath("$.city_from.name", is(transitDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.id", is(transitDto.getCity_to().getId())))
        .andExpect(jsonPath("$.city_to.name", is(transitDto.getCity_to().getName())))
        .andExpect(jsonPath("$.user.id", is(transitDto.getUser().getId())))
        .andExpect(jsonPath("$.user.name", is(transitDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(transitDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(transitDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.role", is(transitDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(transitDto.getUser().getEmail())))
        .andExpect(jsonPath("$.driver.id", is(transitDto.getDriver().getId())))
        .andExpect(
            jsonPath("$.driver.name", is(transitDto.getDriver().getName())))
        .andExpect(jsonPath("$.driver.familyName",
            is(transitDto.getDriver().getFamilyName())))
        .andExpect(
            jsonPath("$.driver.login", is(transitDto.getDriver().getLogin())))
        .andExpect(
            jsonPath("$.driver.role",
                is(transitDto.getDriver().getRole().name())))
        .andExpect(
            jsonPath("$.driver.email", is(transitDto.getDriver().getEmail())))
        .andExpect(jsonPath("$.car.id", is(transitDto.getCar().getId())))
        .andExpect(
            jsonPath("$.car.model", is(transitDto.getCar().getModel().name())))
        .andExpect(
            jsonPath("$.car.carNumber", is(transitDto.getCar().getCarNumber())))
        .andExpect(jsonPath("$.car.carTechnicalPassport",
            is(transitDto.getCar().getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.car.releaseDate",
                is(transitDto.getCar().getReleaseDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$.car.fully_Functional",
            is(transitDto.getCar().isFully_Functional())));
    ;
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldUpdateTransitThroughAllLayers() throws Exception {
    //Given
    TransitDto transitDto = TestTransitDataGenerator.generateRealEntity(1);

    //Then
    mockMvc.perform(put("/transits")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(transitDto)))
        .andExpect(status().isOk());

    mockMvc.perform(get("/transits/1")
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(transitDto.getId())))
        .andExpect(jsonPath("$.status", is(transitDto.getStatus().name())))
        .andExpect(
            jsonPath("$.city_from.id", is(transitDto.getCity_from().getId())))
        .andExpect(
            jsonPath("$.city_from.name", is(transitDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.id", is(transitDto.getCity_to().getId())))
        .andExpect(jsonPath("$.city_to.name", is(transitDto.getCity_to().getName())))
        .andExpect(jsonPath("$.user.id", is(transitDto.getUser().getId())))
        .andExpect(jsonPath("$.user.name", is(transitDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(transitDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(transitDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.role", is(transitDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(transitDto.getUser().getEmail())))
        .andExpect(jsonPath("$.driver.id", is(transitDto.getDriver().getId())))
        .andExpect(
            jsonPath("$.driver.name", is(transitDto.getDriver().getName())))
        .andExpect(jsonPath("$.driver.familyName",
            is(transitDto.getDriver().getFamilyName())))
        .andExpect(
            jsonPath("$.driver.login", is(transitDto.getDriver().getLogin())))
        .andExpect(
            jsonPath("$.driver.role",
                is(transitDto.getDriver().getRole().name())))
        .andExpect(
            jsonPath("$.driver.email", is(transitDto.getDriver().getEmail())))
        .andExpect(jsonPath("$.car.id", is(transitDto.getCar().getId())))
        .andExpect(
            jsonPath("$.car.model", is(transitDto.getCar().getModel().name())))
        .andExpect(
            jsonPath("$.car.carNumber", is(transitDto.getCar().getCarNumber())))
        .andExpect(jsonPath("$.car.carTechnicalPassport",
            is(transitDto.getCar().getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.car.releaseDate",
                is(transitDto.getCar().getReleaseDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$.car.fully_Functional",
            is(transitDto.getCar().isFully_Functional())));
    ;
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldDeleteTransitThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(delete("/transits/2"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/transits/2"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetTransitThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/transits/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.status", is(Status.OPENED.name())))
        .andExpect(
            jsonPath("$.city_from.id", is(2)))
        .andExpect(
            jsonPath("$.city_from.name", is("Rome")))
        .andExpect(jsonPath("$.city_to.id", is(2)))
        .andExpect(jsonPath("$.city_to.name", is("Rome")))
        .andExpect(jsonPath("$.driver.id", is(2)))
        .andExpect(jsonPath("$.driver.name", is("Mike")))
        .andExpect(jsonPath("$.driver.familyName", is("Petrov")))
        .andExpect(jsonPath("$.driver.login", is("dispatcher")))
        .andExpect(
            jsonPath("$.driver.role", is(Role.DISPATCHER.name())))
        .andExpect(jsonPath("$.driver.email", is("qwerty919979@gmail.com")))
        .andExpect(jsonPath("$.user.id", is(1)))
        .andExpect(jsonPath("$.user.name", is("Nikita")))
        .andExpect(jsonPath("$.user.familyName", is("Poddubskiy")))
        .andExpect(jsonPath("$.user.login", is("admin")))
        .andExpect(
            jsonPath("$.user.role", is(Role.ADMIN.name())))
        .andExpect(jsonPath("$.user.email", is("nikita919979@gmail.com")));
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetTransitListThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/transits"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[1].id", is(2)));
  }
}
