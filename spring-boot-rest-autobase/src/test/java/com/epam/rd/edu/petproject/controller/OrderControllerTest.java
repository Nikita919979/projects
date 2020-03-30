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

import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.service.OrderService;
import com.epam.rd.edu.petproject.utils.datagenerator.TestOrderDataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class OrderControllerTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);
  private final OrderService orderService = Mockito.mock(OrderService.class);
  private final OrderController sut = new OrderController(orderService);
  private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sut).build();

  @Test
  public void shouldReturnListOfCityDtoDetails() throws Exception {
    //Given
    List<OrderDto> inputOrderDtoList = TestOrderDataGenerator.generateRequestOrderDtoList(2);
    given(orderService.getAll()).willReturn(inputOrderDtoList);

    //Then
    mockMvc.perform(get("/orders")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].uuid", is(inputOrderDtoList.get(0).getUuid())))
        .andExpect(
            jsonPath("$[0].city_from.uuid", is(inputOrderDtoList.get(0).getCity_from().getUuid())))
        .andExpect(
            jsonPath("$[0].city_from.name", is(inputOrderDtoList.get(0).getCity_from().getName())))
        .andExpect(
            jsonPath("$[0].city_to.uuid", is(inputOrderDtoList.get(0).getCity_to().getUuid())))
        .andExpect(
            jsonPath("$[0].city_to.name", is(inputOrderDtoList.get(0).getCity_to().getName())))
        .andExpect(jsonPath("$[0].carModel", is(inputOrderDtoList.get(0).getCarModel().name())))
        .andExpect(jsonPath("$[0].user.uuid", is(inputOrderDtoList.get(0).getUser().getUuid())))
        .andExpect(jsonPath("$[0].user.name", is(inputOrderDtoList.get(0).getUser().getName())))
        .andExpect(jsonPath("$[0].user.familyName",
            is(inputOrderDtoList.get(0).getUser().getFamilyName())))
        .andExpect(jsonPath("$[0].user.login", is(inputOrderDtoList.get(0).getUser().getLogin())))
        .andExpect(
            jsonPath("$[0].user.password", is(inputOrderDtoList.get(0).getUser().getPassword())))
        .andExpect(
            jsonPath("$[0].user.role", is(inputOrderDtoList.get(0).getUser().getRole().name())))
        .andExpect(jsonPath("$[0].user.email", is(inputOrderDtoList.get(0).getUser().getEmail())))
        .andExpect(jsonPath("$[1].uuid", is(inputOrderDtoList.get(1).getUuid())))
        .andExpect(
            jsonPath("$[1].city_from.uuid", is(inputOrderDtoList.get(1).getCity_from().getUuid())))
        .andExpect(
            jsonPath("$[1].city_from.name", is(inputOrderDtoList.get(1).getCity_from().getName())))
        .andExpect(
            jsonPath("$[1].city_to.uuid", is(inputOrderDtoList.get(1).getCity_to().getUuid())))
        .andExpect(
            jsonPath("$[1].city_to.name", is(inputOrderDtoList.get(1).getCity_to().getName())))
        .andExpect(jsonPath("$[1].carModel", is(inputOrderDtoList.get(1).getCarModel().name())))
        .andExpect(jsonPath("$[1].user.uuid", is(inputOrderDtoList.get(1).getUser().getUuid())))
        .andExpect(jsonPath("$[1].user.name", is(inputOrderDtoList.get(1).getUser().getName())))
        .andExpect(jsonPath("$[1].user.familyName",
            is(inputOrderDtoList.get(1).getUser().getFamilyName())))
        .andExpect(jsonPath("$[1].user.login", is(inputOrderDtoList.get(1).getUser().getLogin())))
        .andExpect(
            jsonPath("$[1].user.password", is(inputOrderDtoList.get(1).getUser().getPassword())))
        .andExpect(
            jsonPath("$[1].user.role", is(inputOrderDtoList.get(1).getUser().getRole().name())))
        .andExpect(jsonPath("$[1].user.email", is(inputOrderDtoList.get(1).getUser().getEmail())));
  }

  @Test
  public void shouldReturnCityDtoDetails() throws Exception {
    //Given
    OrderDto inputOrderDto = TestOrderDataGenerator.generateOrderDto(1);
    given(orderService.read(UUID.fromString("e731d0fa-71db-11ea-bc55-0242ac130003")))
        .willReturn(inputOrderDto);

    //Then
    mockMvc.perform(get("/orders/e731d0fa-71db-11ea-bc55-0242ac130003")
        .accept(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.uuid", is(inputOrderDto.getUuid())))
        .andExpect(
            jsonPath("$.city_from.uuid", is(inputOrderDto.getCity_from().getUuid())))
        .andExpect(
            jsonPath("$.city_from.name", is(inputOrderDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.uuid", is(inputOrderDto.getCity_to().getUuid())))
        .andExpect(jsonPath("$.city_to.name", is(inputOrderDto.getCity_to().getName())))
        .andExpect(jsonPath("$.carModel", is(inputOrderDto.getCarModel().name())))
        .andExpect(jsonPath("$.user.uuid", is(inputOrderDto.getUser().getUuid())))
        .andExpect(jsonPath("$.user.name", is(inputOrderDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(inputOrderDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(inputOrderDto.getUser().getLogin())))
        .andExpect(jsonPath("$.user.password", is(inputOrderDto.getUser().getPassword())))
        .andExpect(
            jsonPath("$.user.role", is(inputOrderDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(inputOrderDto.getUser().getEmail())));
  }

  @Test
  public void shouldReturnCreatedCityWithStatusCreated() throws Exception {
    //Given
    OrderDto inputOrderDto = TestOrderDataGenerator.generateOrderDto(1);
    given(orderService.create(inputOrderDto)).willReturn(inputOrderDto.clone());
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(post("/orders")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputOrderDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.uuid", is(inputOrderDto.getUuid())))
        .andExpect(
            jsonPath("$.city_from.uuid", is(inputOrderDto.getCity_from().getUuid())))
        .andExpect(
            jsonPath("$.city_from.name", is(inputOrderDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.uuid", is(inputOrderDto.getCity_to().getUuid())))
        .andExpect(jsonPath("$.city_to.name", is(inputOrderDto.getCity_to().getName())))
        .andExpect(jsonPath("$.carModel", is(inputOrderDto.getCarModel().name())))
        .andExpect(jsonPath("$.user.uuid", is(inputOrderDto.getUser().getUuid())))
        .andExpect(jsonPath("$.user.name", is(inputOrderDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(inputOrderDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(inputOrderDto.getUser().getLogin())))
        .andExpect(jsonPath("$.user.password", is(inputOrderDto.getUser().getPassword())))
        .andExpect(
            jsonPath("$.user.role", is(inputOrderDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(inputOrderDto.getUser().getEmail())));
  }

  @Test
  public void shouldUpdateOrderWithStatusOk() throws Exception {
    //Given
    OrderDto inputOrderDto = TestOrderDataGenerator.generateOrderDto(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(put("/orders")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputOrderDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldDeleteOrderWithStatusOk() throws Exception {
    //Given
    OrderDto inputOrderDto = TestOrderDataGenerator.generateOrderDto(1);
    ObjectMapper mapper = new ObjectMapper();

    //Then
    mockMvc.perform(delete("/orders/e731d0fa-71db-11ea-bc55-0242ac130003")
        .accept(APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(inputOrderDto))
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());
  }
}

