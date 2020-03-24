package com.epam.rd.edu.petproject.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.service.TransitService;
import com.epam.rd.edu.petproject.utils.datagenerator.TestTransitDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TransitControllerTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  private final TransitService transitService = Mockito.mock(TransitService.class);
  private final TransitController sut = new TransitController(transitService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();

  @Test
  public void shouldReturnListOfTransitDtoDetails() throws Exception {
    //Given
    List<TransitDto> inputTransitDtoList = TestTransitDataGenerator
        .generateRequestTransitDtoList(2);
    given(transitService.getAll()).willReturn(inputTransitDtoList);

    //Then
    mockMvc.perform(get("/transits")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id", is(inputTransitDtoList.get(0).getId())))
        .andExpect(
            jsonPath("$[0].city_from.id", is(inputTransitDtoList.get(0).getCity_from().getId())))
        .andExpect(
            jsonPath("$[0].city_from.name",
                is(inputTransitDtoList.get(0).getCity_from().getName())))
        .andExpect(jsonPath("$[0].city_to.id", is(inputTransitDtoList.get(0).getCity_to().getId())))
        .andExpect(
            jsonPath("$[0].city_to.name", is(inputTransitDtoList.get(0).getCity_to().getName())))
        .andExpect(jsonPath("$[0].status", is(inputTransitDtoList.get(0).getStatus().name())))
        .andExpect(jsonPath("$[0].user.id", is(inputTransitDtoList.get(0).getUser().getId())))
        .andExpect(jsonPath("$[0].user.name", is(inputTransitDtoList.get(0).getUser().getName())))
        .andExpect(jsonPath("$[0].user.familyName",
            is(inputTransitDtoList.get(0).getUser().getFamilyName())))
        .andExpect(jsonPath("$[0].user.login", is(inputTransitDtoList.get(0).getUser().getLogin())))
        .andExpect(
            jsonPath("$[0].user.password", is(inputTransitDtoList.get(0).getUser().getPassword())))
        .andExpect(
            jsonPath("$[0].user.role", is(inputTransitDtoList.get(0).getUser().getRole().name())))
        .andExpect(jsonPath("$[0].user.email", is(inputTransitDtoList.get(0).getUser().getEmail())))
        .andExpect(jsonPath("$[0].driver.id", is(inputTransitDtoList.get(0).getDriver().getId())))
        .andExpect(
            jsonPath("$[0].driver.name", is(inputTransitDtoList.get(0).getDriver().getName())))
        .andExpect(jsonPath("$[0].driver.familyName",
            is(inputTransitDtoList.get(0).getDriver().getFamilyName())))
        .andExpect(
            jsonPath("$[0].driver.login", is(inputTransitDtoList.get(0).getDriver().getLogin())))
        .andExpect(
            jsonPath("$[0].driver.password",
                is(inputTransitDtoList.get(0).getDriver().getPassword())))
        .andExpect(
            jsonPath("$[0].driver.role",
                is(inputTransitDtoList.get(0).getDriver().getRole().name())))
        .andExpect(
            jsonPath("$[0].driver.email", is(inputTransitDtoList.get(0).getDriver().getEmail())))
        .andExpect(jsonPath("$[0].car.id", is(inputTransitDtoList.get(0).getCar().getId())))
        .andExpect(
            jsonPath("$[0].car.model", is(inputTransitDtoList.get(0).getCar().getModel().name())))
        .andExpect(
            jsonPath("$[0].car.carNumber", is(inputTransitDtoList.get(0).getCar().getCarNumber())))
        .andExpect(jsonPath("$[0].car.carTechnicalPassport",
            is(inputTransitDtoList.get(0).getCar().getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$[0].car.releaseDate",
                is(inputTransitDtoList.get(0).getCar().getReleaseDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$[0].car.fully_Functional",
            is(inputTransitDtoList.get(0).getCar().isFully_Functional())))
        .andExpect(jsonPath("$[1].id", is(inputTransitDtoList.get(1).getId())))
        .andExpect(
            jsonPath("$[1].city_from.id", is(inputTransitDtoList.get(1).getCity_from().getId())))
        .andExpect(
            jsonPath("$[1].city_from.name",
                is(inputTransitDtoList.get(1).getCity_from().getName())))
        .andExpect(jsonPath("$[1].city_to.id", is(inputTransitDtoList.get(1).getCity_to().getId())))
        .andExpect(
            jsonPath("$[1].city_to.name", is(inputTransitDtoList.get(1).getCity_to().getName())))
        .andExpect(jsonPath("$[1].status", is(inputTransitDtoList.get(1).getStatus().name())))
        .andExpect(jsonPath("$[1].user.id", is(inputTransitDtoList.get(1).getUser().getId())))
        .andExpect(jsonPath("$[1].user.name", is(inputTransitDtoList.get(1).getUser().getName())))
        .andExpect(jsonPath("$[1].user.familyName",
            is(inputTransitDtoList.get(1).getUser().getFamilyName())))
        .andExpect(jsonPath("$[1].user.login", is(inputTransitDtoList.get(1).getUser().getLogin())))
        .andExpect(
            jsonPath("$[1].user.password", is(inputTransitDtoList.get(1).getUser().getPassword())))
        .andExpect(
            jsonPath("$[1].user.role", is(inputTransitDtoList.get(1).getUser().getRole().name())))
        .andExpect(jsonPath("$[1].user.email", is(inputTransitDtoList.get(1).getUser().getEmail())))
        .andExpect(jsonPath("$[1].driver.id", is(inputTransitDtoList.get(1).getDriver().getId())))
        .andExpect(
            jsonPath("$[1].driver.name", is(inputTransitDtoList.get(1).getDriver().getName())))
        .andExpect(jsonPath("$[1].driver.familyName",
            is(inputTransitDtoList.get(1).getDriver().getFamilyName())))
        .andExpect(
            jsonPath("$[1].driver.login", is(inputTransitDtoList.get(1).getDriver().getLogin())))
        .andExpect(
            jsonPath("$[1].driver.password",
                is(inputTransitDtoList.get(1).getDriver().getPassword())))
        .andExpect(
            jsonPath("$[1].driver.role",
                is(inputTransitDtoList.get(1).getDriver().getRole().name())))
        .andExpect(
            jsonPath("$[1].driver.email", is(inputTransitDtoList.get(1).getDriver().getEmail())))
        .andExpect(jsonPath("$[1].car.id", is(inputTransitDtoList.get(1).getCar().getId())))
        .andExpect(
            jsonPath("$[1].car.model", is(inputTransitDtoList.get(1).getCar().getModel().name())))
        .andExpect(
            jsonPath("$[1].car.carNumber", is(inputTransitDtoList.get(1).getCar().getCarNumber())))
        .andExpect(jsonPath("$[1].car.carTechnicalPassport",
            is(inputTransitDtoList.get(1).getCar().getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$[1].car.releaseDate",
                is(inputTransitDtoList.get(1).getCar().getReleaseDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$[1].car.fully_Functional",
            is(inputTransitDtoList.get(1).getCar().isFully_Functional())));
  }

  @Test
  public void shouldReturnTransitDtoDetails() throws Exception {
    //Given
    TransitDto inputTransitDto = TestTransitDataGenerator.generateTransitDto(1);
    given(transitService.read(1)).willReturn(inputTransitDto);

    //Then
    mockMvc.perform(get("/transits/1")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(inputTransitDto.getId())))
        .andExpect(
            jsonPath("$.city_from.id", is(inputTransitDto.getCity_from().getId())))
        .andExpect(
            jsonPath("$.city_from.name",
                is(inputTransitDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.id", is(inputTransitDto.getCity_to().getId())))
        .andExpect(
            jsonPath("$.city_to.name", is(inputTransitDto.getCity_to().getName())))
        .andExpect(jsonPath("$.status", is(inputTransitDto.getStatus().name())))
        .andExpect(jsonPath("$.user.id", is(inputTransitDto.getUser().getId())))
        .andExpect(jsonPath("$.user.name", is(inputTransitDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName",
            is(inputTransitDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(inputTransitDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.password", is(inputTransitDto.getUser().getPassword())))
        .andExpect(
            jsonPath("$.user.role", is(inputTransitDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(inputTransitDto.getUser().getEmail())))
        .andExpect(jsonPath("$.driver.id", is(inputTransitDto.getDriver().getId())))
        .andExpect(
            jsonPath("$.driver.name", is(inputTransitDto.getDriver().getName())))
        .andExpect(jsonPath("$.driver.familyName",
            is(inputTransitDto.getDriver().getFamilyName())))
        .andExpect(
            jsonPath("$.driver.login", is(inputTransitDto.getDriver().getLogin())))
        .andExpect(
            jsonPath("$.driver.password",
                is(inputTransitDto.getDriver().getPassword())))
        .andExpect(
            jsonPath("$.driver.role",
                is(inputTransitDto.getDriver().getRole().name())))
        .andExpect(
            jsonPath("$.driver.email", is(inputTransitDto.getDriver().getEmail())))
        .andExpect(jsonPath("$.car.id", is(inputTransitDto.getCar().getId())))
        .andExpect(
            jsonPath("$.car.model", is(inputTransitDto.getCar().getModel().name())))
        .andExpect(
            jsonPath("$.car.carNumber", is(inputTransitDto.getCar().getCarNumber())))
        .andExpect(jsonPath("$.car.carTechnicalPassport",
            is(inputTransitDto.getCar().getCarTechnicalPassport())))
        .andExpect(
            jsonPath("$.car.releaseDate",
                is(inputTransitDto.getCar().getReleaseDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
        .andExpect(jsonPath("$.car.fully_Functional",
            is(inputTransitDto.getCar().isFully_Functional())));
  }

//  @Test
//  public void createTransitDtoShouldReturnCreatedTransitAndStatusCreated() throws Exception {
//    //Given
//    TransitDto inputTransitDto = TestTransitDataGenerator.generateTransitDto(1);
//    given(transitService.create(inputTransitDto)).willReturn(inputTransitDto.clone());
//    ObjectMapper mapper = new ObjectMapper();
//
//    //Then
//    mockMvc.perform(post("/transits")
//        .accept(APPLICATION_JSON_UTF8)
//        .content(mapper.writeValueAsString(inputTransitDto))
//        .contentType(APPLICATION_JSON_UTF8))
//        .andDo(MockMvcResultHandlers.print())
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("$.id", is(inputTransitDto.getId())))
//        .andExpect(
//            jsonPath("$.city_from.id", is(inputTransitDto.getCity_from().getId())))
//        .andExpect(
//            jsonPath("$.city_from.name",
//                is(inputTransitDto.getCity_from().getName())))
//        .andExpect(jsonPath("$.city_to.id", is(inputTransitDto.getCity_to().getId())))
//        .andExpect(
//            jsonPath("$.city_to.name", is(inputTransitDto.getCity_to().getName())))
//        .andExpect(jsonPath("$.status", is(inputTransitDto.getStatus().name())))
//        .andExpect(jsonPath("$.user.id", is(inputTransitDto.getUser().getId())))
//        .andExpect(jsonPath("$.user.name", is(inputTransitDto.getUser().getName())))
//        .andExpect(jsonPath("$.user.familyName",
//            is(inputTransitDto.getUser().getFamilyName())))
//        .andExpect(jsonPath("$.user.login", is(inputTransitDto.getUser().getLogin())))
//        .andExpect(
//            jsonPath("$.user.password", is(inputTransitDto.getUser().getPassword())))
//        .andExpect(
//            jsonPath("$.user.role", is(inputTransitDto.getUser().getRole().name())))
//        .andExpect(jsonPath("$.user.email", is(inputTransitDto.getUser().getEmail())))
//        .andExpect(jsonPath("$.driver.id", is(inputTransitDto.getDriver().getId())))
//        .andExpect(
//            jsonPath("$.driver.name", is(inputTransitDto.getDriver().getName())))
//        .andExpect(jsonPath("$.driver.familyName",
//            is(inputTransitDto.getDriver().getFamilyName())))
//        .andExpect(
//            jsonPath("$.driver.login", is(inputTransitDto.getDriver().getLogin())))
//        .andExpect(
//            jsonPath("$.driver.password",
//                is(inputTransitDto.getDriver().getPassword())))
//        .andExpect(
//            jsonPath("$.driver.role",
//                is(inputTransitDto.getDriver().getRole().name())))
//        .andExpect(
//            jsonPath("$.driver.email", is(inputTransitDto.getDriver().getEmail())))
//        .andExpect(jsonPath("$.car.id", is(inputTransitDto.getCar().getId())))
//        .andExpect(
//            jsonPath("$.car.model", is(inputTransitDto.getCar().getModel().name())))
//        .andExpect(
//            jsonPath("$.car.carNumber", is(inputTransitDto.getCar().getCarNumber())))
//        .andExpect(jsonPath("$.car.carTechnicalPassport",
//            is(inputTransitDto.getCar().getCarTechnicalPassport())))
//        .andExpect(
//            jsonPath("$.car.releaseDate",
//                is(inputTransitDto.getCar().getReleaseDate().format(
//                    DateTimeFormatter.ofPattern("dd-MM-yyyy")))))
//        .andExpect(jsonPath("$.car.fully_Functional",
//            is(inputTransitDto.getCar().isFully_Functional())));
//  }

  @Test
  public void shouldUpdateWithStatusOk() throws Exception {
    //Given
    TransitDto inputTransitDto = TestTransitDataGenerator.generateTransitDto(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(put("/transits")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputTransitDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldDeleteWithStatusOk() throws Exception {
    //Given
    TransitDto inputTransitDto = TestTransitDataGenerator.generateTransitDto(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(delete("/transits/1")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputTransitDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }
}

