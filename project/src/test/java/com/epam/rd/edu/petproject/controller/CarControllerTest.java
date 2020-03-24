package com.epam.rd.edu.petproject.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.service.CarService;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCarDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CarControllerTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  private final CarService carService = Mockito.mock(CarService.class);
  private final CarController sut = new CarController(carService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();

  @Test
  public void shouldReturnListOfCarDtoDetails() throws Exception {
    //Given
    List<CarDto> inputCarDtoList = TestCarDataGenerator.generateRequestCarDtoList(2);
    given(carService.getAll()).willReturn(inputCarDtoList);
    //Then
    mockMvc.perform(get("/cars")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(inputCarDtoList.get(0).getId())))
        .andExpect(jsonPath("$[0].model", is(inputCarDtoList.get(0).getModel().name())))
        .andExpect(jsonPath("$[0].carNumber", is(inputCarDtoList.get(0).getCarNumber())))
        .andExpect(jsonPath("$[0].carTechnicalPassport",
            is(inputCarDtoList.get(0).getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$[0].releaseDate", is(inputCarDtoList.get(0).getReleaseDate().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(
            jsonPath("$[0].fully_Functional", is(inputCarDtoList.get(0).isFully_Functional())))
        .andExpect(jsonPath("$[1].id", is(inputCarDtoList.get(1).getId())))
        .andExpect(jsonPath("$[1].model", is(inputCarDtoList.get(1).getModel().name())))
        .andExpect(jsonPath("$[1].carNumber", is(inputCarDtoList.get(1).getCarNumber())))
        .andExpect(jsonPath("$[1].carTechnicalPassport",
            is(inputCarDtoList.get(1).getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$[1].releaseDate", is(inputCarDtoList.get(1).getReleaseDate().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(
            jsonPath("$[1].fully_Functional", is(inputCarDtoList.get(1).isFully_Functional())));
  }

  @Test
  public void shouldReturnCarDtoDetails() throws Exception {
    //Given
    CarDto inputCarDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    given(carService.read(1)).willReturn(inputCarDto);

    //Then
    mockMvc.perform(get("/cars/1")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(inputCarDto.getId())))
        .andExpect(jsonPath("$.model", is(inputCarDto.getModel().name())))
        .andExpect(jsonPath("$.carNumber", is(inputCarDto.getCarNumber())))
        .andExpect(jsonPath("$.carTechnicalPassport", is(inputCarDto.getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.releaseDate", is(inputCarDto.getReleaseDate().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$.fully_Functional", is(inputCarDto.isFully_Functional())));
  }

//  @Test
//  public void createCarDtoShouldReturnCreatedCarAndStatusCreated() throws Exception {
//    //Given
//    CarDto inputCarDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
//    given(carService.create(inputCarDto)).willReturn(inputCarDto.clone());
//    ObjectMapper mapper = new ObjectMapper();
//    //Then
//    mockMvc.perform(post("/cars")
//        .accept(APPLICATION_JSON_UTF8)
//        .content(mapper.writeValueAsString(inputCarDto))
//        .contentType(APPLICATION_JSON_UTF8))
//        .andDo(MockMvcResultHandlers.print())
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("$.id", is(inputCarDto.getId())))
//        .andExpect(jsonPath("$.model", is(inputCarDto.getModel().name())))
//        .andExpect(jsonPath("$.carNumber", is(inputCarDto.getCarNumber())))
//        .andExpect(jsonPath("$.carTechnicalPassport", is(inputCarDto.getCarTechnicalPassport())))
//        .andExpect(
//            jsonPath("$.releaseDate", is(inputCarDto.getReleaseDate().format(
//                DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
//        .andExpect(jsonPath("$.fully_Functional", is(inputCarDto.isFully_Functional())));
//  }

  @Test
  public void shouldUpdateCarWithStatusOk() throws Exception {
    //Given
    CarDto inputCarDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(put("/cars")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputCarDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldDeleteCarWithStatusOk() throws Exception {
    //Given
    CarDto inputCarDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(delete("/cars/1")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputCarDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }
}

