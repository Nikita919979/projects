package com.epam.rd.edu.petproject.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.rd.edu.petproject.converter.OrderConverter;
import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.model.Order;
import com.epam.rd.edu.petproject.repository.OrderRepository;
import com.epam.rd.edu.petproject.service.impl.OrderServiceImpl;
import com.epam.rd.edu.petproject.utils.datagenerator.TestOrderDataGenerator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;

public class OrderServiceImplTest {

  private final OrderRepository orderRepository = mock(OrderRepository.class);
  private final OrderConverter orderConverter = mock(OrderConverter.class);
  private final OrderServiceImpl sut = new OrderServiceImpl(orderRepository, orderConverter);

  @Test
  public void shouldReturnCreatedEntity() {
    //Given
    OrderDto OrderDto = TestOrderDataGenerator.generateOrderDto(1);
    Order order = TestOrderDataGenerator.getOrder(OrderDto);
    doReturn(order).when(orderRepository).save(order);
    doReturn(order).when(orderConverter).toEntity(OrderDto);
    doReturn(OrderDto).when(orderConverter).toDto(order);

    //When
    OrderDto cityDtoVerify = sut.create(OrderDto);

    //Then
    verify(orderRepository).save(order);
    assertThat(cityDtoVerify, hasProperty("uuid", is(OrderDto.getUuid())));
    assertThat(cityDtoVerify, hasProperty("city_from", is(OrderDto.getCity_from())));
    assertThat(cityDtoVerify, hasProperty("city_to", is(OrderDto.getCity_to())));
    assertThat(cityDtoVerify, hasProperty("carModel", is(OrderDto.getCarModel())));
    assertThat(cityDtoVerify, hasProperty("user", is(OrderDto.getUser())));
  }

  @Test
  public void shouldDeleteOrder() {
    //Given
    OrderDto OrderDto = TestOrderDataGenerator.generateOrderDto(1);
    Order order = TestOrderDataGenerator.getOrder(OrderDto);

    //When
    sut.delete(OrderDto.getUuid());

    //Then
    verify(orderRepository).deleteById(order.getUuid());
  }

  @Test
  public void shouldUpdateOrder() {
    //Given
    OrderDto OrderDto = TestOrderDataGenerator.generateOrderDto(1);
    Order order = TestOrderDataGenerator.getOrder(OrderDto);
    doReturn(order).when(orderRepository).save(order);
    doReturn(order).when(orderConverter).toEntity(OrderDto);

    //When
    sut.update(OrderDto);

    //Then
    verify(orderRepository).save(order);
  }

  @Test
  public void shouldReturnFoundedEntity() {
    //Given
    OrderDto OrderDto = TestOrderDataGenerator.generateOrderDto(1);
    Order order = TestOrderDataGenerator.getOrder(OrderDto);
    Optional<Order> carOptional = Optional.of(order);
    doReturn(carOptional).when(orderRepository)
        .findById(UUID.fromString("e731d0fa-71db-11ea-bc55-0242ac130003"));
    doReturn(OrderDto).when(orderConverter).toDto(order);

    //When
    OrderDto cityDtoVerify = sut.read(UUID.fromString("e731d0fa-71db-11ea-bc55-0242ac130003"));

    //Then
    verify(orderRepository)
        .findById(UUID.fromString("e731d0fa-71db-11ea-bc55-0242ac130003"));
    assertThat(cityDtoVerify, hasProperty("uuid", is(OrderDto.getUuid())));
    assertThat(cityDtoVerify, hasProperty("city_from", is(OrderDto.getCity_from())));
    assertThat(cityDtoVerify, hasProperty("city_to", is(OrderDto.getCity_to())));
    assertThat(cityDtoVerify, hasProperty("carModel", is(OrderDto.getCarModel())));
    assertThat(cityDtoVerify, hasProperty("user", is(OrderDto.getUser())));
  }

  @Test
  public void shouldReturnAllFoundedEntities() {
    //Given
    List<OrderDto> orderDtoList = TestOrderDataGenerator.generateRequestOrderDtoList(5);
    List<Order> cityList = TestOrderDataGenerator.getOrderList(orderDtoList);
    doReturn(cityList).when(orderRepository).findAll();
    doReturn(orderDtoList).when(orderConverter).toDtoList(cityList);

    //When
    List<OrderDto> cityDtoReturnList = sut.getAll();

    //Then
    verify(orderRepository).findAll();
    assertThat(cityDtoReturnList, not(is(empty())));
    orderDtoList.forEach(
        entity -> {
          assertThat(cityDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_from", is(entity.getCity_from()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_to", is(entity.getCity_to()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("carModel", is(entity.getCarModel()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("user", is(entity.getUser()))));
        }
    );
  }

  @Test
  public void shouldUpdateAllOrders() {
    //Given
    List<OrderDto> orderDtoList = TestOrderDataGenerator.generateRequestOrderDtoList(5);
    List<Order> cityList = TestOrderDataGenerator.getOrderList(orderDtoList);
    doReturn(cityList).when(orderRepository).saveAll(cityList);
    doReturn(cityList).when(orderConverter).toEntityList(orderDtoList);

    //When
    sut.updateAll(orderDtoList);

    //Then
    verify(orderRepository).saveAll(cityList);
  }

  @Test
  public void shouldReturnAllCreatedEntities() {
    //Given
    List<OrderDto> orderDtoList = TestOrderDataGenerator.generateRequestOrderDtoList(5);
    List<Order> cityList = TestOrderDataGenerator.getOrderList(orderDtoList);
    doReturn(cityList).when(orderRepository).saveAll(cityList);
    doReturn(cityList).when(orderConverter).toEntityList(orderDtoList);
    doReturn(orderDtoList).when(orderConverter).toDtoList(cityList);

    //When
    List<OrderDto> cityDtoReturnList = sut.createAll(orderDtoList);

    //Then
    verify(orderRepository).saveAll(cityList);
    assertThat(cityDtoReturnList, not(is(empty())));
    orderDtoList.forEach(
        entity -> {
          assertThat(cityDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_from", is(entity.getCity_from()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_to", is(entity.getCity_to()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("carModel", is(entity.getCarModel()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("user", is(entity.getUser()))));
        }
    );
  }
}
