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
import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.model.Car;
import com.epam.rd.edu.petproject.model.Car.CarModel;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCarDataGenerator;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

public class CarControllerIT extends SpringAutobaseProjectTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldCreateCarThroughAllLayers() throws Exception {
    //Given
    CarDto carDto = TestCarDataGenerator.generateCarDtoWithRandomModel(4);

    //Then
    mockMvc.perform(post("/cars")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(carDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(carDto.getId())))
        .andExpect(jsonPath("$.model", is(carDto.getModel().name())))
        .andExpect(jsonPath("$.carNumber", is(carDto.getCarNumber())))
        .andExpect(jsonPath("$.carTechnicalPassport",
            is(carDto.getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.releaseDate", is(carDto.getReleaseDate().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(
            jsonPath("$.fully_Functional", is(carDto.isFully_Functional())));

    mockMvc.perform(get("/cars/4")
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(carDto.getId())))
        .andExpect(jsonPath("$.model", is(carDto.getModel().name())))
        .andExpect(jsonPath("$.carNumber", is(carDto.getCarNumber())))
        .andExpect(jsonPath("$.carTechnicalPassport",
            is(carDto.getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.releaseDate", is(carDto.getReleaseDate().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(
            jsonPath("$.fully_Functional", is(carDto.isFully_Functional())));
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldUpdateCarThroughAllLayers() throws Exception {
    //Given
    CarDto carDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);

    //Then
    mockMvc.perform(put("/cars")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(carDto)))
        .andExpect(status().isOk());

    mockMvc.perform(get("/cars/1")
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(carDto.getId())))
        .andExpect(jsonPath("$.model", is(carDto.getModel().name())))
        .andExpect(jsonPath("$.carNumber", is(carDto.getCarNumber())))
        .andExpect(jsonPath("$.carTechnicalPassport",
            is(carDto.getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.releaseDate", is(carDto.getReleaseDate().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(
            jsonPath("$.fully_Functional", is(carDto.isFully_Functional())));
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldDeleteCarThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(delete("/cars/3"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/cars/3"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetCarThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/cars/3"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.model", is(CarModel.MAN)))
        .andExpect(jsonPath("$.carNumber", is("testCarNumber1")))
        .andExpect(jsonPath("$.carTechnicalPassport", is("testCarTechnicalPassport1")))
        .andExpect(jsonPath("$.releaseDate", is("23-03-2020")))
        .andExpect(jsonPath("$.fully_Functional", is(true)));
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetCarListThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/cars"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[2].id", is(3)));
  }
}
