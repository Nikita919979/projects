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

import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.service.CityService;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCityDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CityControllerTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  private final CityService cityService = Mockito.mock(CityService.class);
  private final CityController sut = new CityController(cityService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();

  @Test
  public void shouldReturnListOfCityDtoDetails() throws Exception {
    //Given
    List<CityDto> inputCityDtoList = TestCityDataGenerator.generateRequestCityDtoList(2);
    given(cityService.getAll()).willReturn(inputCityDtoList);

    //Then
    mockMvc.perform(get("/cities")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(inputCityDtoList.get(0).getId())))
        .andExpect(jsonPath("$[0].name", is(inputCityDtoList.get(0).getName())))
        .andExpect(jsonPath("$[1].id", is(inputCityDtoList.get(1).getId())))
        .andExpect(jsonPath("$[1].name", is(inputCityDtoList.get(1).getName())));
  }

  @Test
  public void shouldReturnCityDtoDetails() throws Exception {
    //Given
    CityDto inputCityDto = TestCityDataGenerator.generateCityDto(1);
    given(cityService.read(1)).willReturn(inputCityDto);

    //Then
    mockMvc.perform(get("/cities/1")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(inputCityDto.getId())))
        .andExpect(jsonPath("$.name", is(inputCityDto.getName())));
  }

  @Test
  public void shouldReturnCreatedCityWithStatusCreated() throws Exception {
    //Given
    CityDto inputCityDto = TestCityDataGenerator.generateCityDto(1);
    given(cityService.create(inputCityDto)).willReturn(inputCityDto);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(post("/cities")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputCityDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(inputCityDto.getId())))
        .andExpect(jsonPath("$.name", is(inputCityDto.getName())));
  }

  @Test
  public void shouldUpdateCityStatusOk() throws Exception {
    //Given
    CityDto inputCityDto = TestCityDataGenerator.generateCityDto(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(put("/cities")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputCityDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldDeleteCityStatusOk() throws Exception {
    //Given
    CityDto inputCityDto = TestCityDataGenerator.generateCityDto(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(delete("/cities/1")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputCityDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }
}

