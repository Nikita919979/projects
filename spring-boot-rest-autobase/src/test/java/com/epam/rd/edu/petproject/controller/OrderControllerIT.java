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
import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.model.User.Role;
import com.epam.rd.edu.petproject.utils.datagenerator.TestOrderDataGenerator;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class OrderControllerIT extends SpringAutobaseProjectTest {

  private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldCreateOrderThroughAllLayers() throws Exception {
    //Given
    OrderDto orderDto = TestOrderDataGenerator.generateRealEntity(4);

    //Then
    mockMvc.perform(post("/orders")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(orderDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.uuid", is(orderDto.getUuid().toString())))
        .andExpect(
            jsonPath("$.city_from.uuid", is(orderDto.getCity_from().getUuid().toString())))
        .andExpect(
            jsonPath("$.city_from.name", is(orderDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.uuid", is(orderDto.getCity_to().getUuid().toString())))
        .andExpect(jsonPath("$.city_to.name", is(orderDto.getCity_to().getName())))
        .andExpect(jsonPath("$.carModel", is(orderDto.getCarModel().name())))
        .andExpect(jsonPath("$.user.uuid", is(orderDto.getUser().getUuid().toString())))
        .andExpect(jsonPath("$.user.name", is(orderDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(orderDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(orderDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.role", is(orderDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(orderDto.getUser().getEmail())));

    mockMvc.perform(get("/orders/" + orderDto.getUuid().toString())
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.uuid", is(orderDto.getUuid().toString())))
        .andExpect(
            jsonPath("$.city_from.uuid", is(orderDto.getCity_from().getUuid().toString())))
        .andExpect(
            jsonPath("$.city_from.name", is(orderDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.uuid", is(orderDto.getCity_to().getUuid().toString())))
        .andExpect(jsonPath("$.city_to.name", is(orderDto.getCity_to().getName())))
        .andExpect(jsonPath("$.carModel", is(orderDto.getCarModel().name())))
        .andExpect(jsonPath("$.user.uuid", is(orderDto.getUser().getUuid().toString())))
        .andExpect(jsonPath("$.user.name", is(orderDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(orderDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(orderDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.role", is(orderDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(orderDto.getUser().getEmail())));
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldUpdateOrderThroughAllLayers() throws Exception {
    //Given
    OrderDto orderDto = TestOrderDataGenerator.generateRealEntity(1);
    orderDto.setUuid(UUID.fromString("e731d0fa-71db-11ea-bc55-0242ac130003"));
    //Then
    mockMvc.perform(put("/orders")
        .contentType(APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(orderDto)))
        .andExpect(status().isOk());

    mockMvc.perform(get("/orders/" + orderDto.getUuid())
        .contentType(APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.uuid", is(orderDto.getUuid().toString())))
        .andExpect(
            jsonPath("$.city_from.uuid", is(orderDto.getCity_from().getUuid().toString())))
        .andExpect(
            jsonPath("$.city_from.name", is(orderDto.getCity_from().getName())))
        .andExpect(jsonPath("$.city_to.uuid", is(orderDto.getCity_to().getUuid().toString())))
        .andExpect(jsonPath("$.city_to.name", is(orderDto.getCity_to().getName())))
        .andExpect(jsonPath("$.carModel", is(orderDto.getCarModel().name())))
        .andExpect(jsonPath("$.user.uuid", is(orderDto.getUser().getUuid().toString())))
        .andExpect(jsonPath("$.user.name", is(orderDto.getUser().getName())))
        .andExpect(jsonPath("$.user.familyName", is(orderDto.getUser().getFamilyName())))
        .andExpect(jsonPath("$.user.login", is(orderDto.getUser().getLogin())))
        .andExpect(
            jsonPath("$.user.role", is(orderDto.getUser().getRole().name())))
        .andExpect(jsonPath("$.user.email", is(orderDto.getUser().getEmail())));
  }

  @Test
  @Transactional
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldDeleteOrderThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(delete("/orders/ee117524-71db-11ea-bc55-0242ac130003"))
        .andExpect(status().isOk());

    mockMvc.perform(get("/orders/ee117524-71db-11ea-bc55-0242ac130003"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetOrderThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/orders/ee117524-71db-11ea-bc55-0242ac130003"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.uuid", is("ee117524-71db-11ea-bc55-0242ac130003")))
        .andExpect(
            jsonPath("$.city_from.uuid", is("caad8f82-71db-11ea-bc55-0242ac130003")))
        .andExpect(
            jsonPath("$.city_from.name", is("Dnepr")))
        .andExpect(jsonPath("$.city_to.uuid", is("cfd3fb4a-71db-11ea-bc55-0242ac130003")))
        .andExpect(jsonPath("$.city_to.name", is("Rome")))
        .andExpect(jsonPath("$.carModel", is("MAN")))
        .andExpect(jsonPath("$.user.uuid", is("e3f1bb8a-71db-11ea-bc55-0242ac130003")))
        .andExpect(jsonPath("$.user.name", is("Mike")))
        .andExpect(jsonPath("$.user.familyName", is("Petrov")))
        .andExpect(jsonPath("$.user.login", is("dispatcher")))
        .andExpect(
            jsonPath("$.user.role", is(Role.DISPATCHER.name())))
        .andExpect(jsonPath("$.user.email", is("qwerty919979@gmail.com")));
  }

  @Test
  @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
  public void shouldGetOrderListThroughAllLayers() throws Exception {
    //Then
    mockMvc.perform(get("/orders"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(
            jsonPath("$[0].uuid", is("e731d0fa-71db-11ea-bc55-0242ac130003")))
        .andExpect(
            jsonPath("$[1].uuid", is("eb3d9184-71db-11ea-bc55-0242ac130003")))
        .andExpect(
            jsonPath("$[2].uuid", is("ee117524-71db-11ea-bc55-0242ac130003")));
  }
}