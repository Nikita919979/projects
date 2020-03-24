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
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCarDataGenerator;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCityDataGenerator;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

public class CityControllerIT extends SpringAutobaseProjectTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);

  @Test
  @Transactional
  public void shouldCreateCityThroughAllLayers() throws Exception {
    //Given
    CityDto cityDto = TestCityDataGenerator.generateCityDto(4);

    //Then
    mockMvc.perform(post("/cities")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(cityDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(cityDto.getId())))
        .andExpect(jsonPath("$.name", is(cityDto.getName())));

    mockMvc.perform(get("/cities/4")
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(cityDto.getId())))
        .andExpect(jsonPath("$.name", is(cityDto.getName())));
  }

  @Test
  @Transactional
  public void shouldUpdateCityThroughAllLayers() throws Exception {
    //Given
    CityDto cityDto = TestCityDataGenerator.generateCityDto(1);

    //Then
    mockMvc.perform(put("/cities")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(cityDto)))
        .andExpect(status().isOk());

    mockMvc.perform(get("/cities/1")
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(cityDto.getId())))
        .andExpect(jsonPath("$.name", is(cityDto.getName())));
  }

  @Test
  @Transactional
  public void shouldDeleteCityThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(delete("/cities/3"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/cities/3"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldGetCityThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/cities/3"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.name", is("Moscow")));
  }

  @Test
  public void shouldGetCityListThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/cities"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[2].id", is(3)));
  }
}
