package com.epam.rd.edu.petproject.converter;

import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.model.City;
import com.epam.rd.edu.petproject.model.Order;
import com.epam.rd.edu.petproject.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderConverter implements ModelConverter<Order, OrderDto> {

  private ModelConverter<User, UserDto> userConverter;
  private ModelConverter<City, CityDto> cityConverter;

  @Override
  public OrderDto toDto(Order order) {
    return OrderDto.builder().id(order.getId())
        .carModel(order.getCarModel())
        .city_from(cityConverter.toDto(order.getCity_from()))
        .city_to(cityConverter.toDto(order.getCity_to()))
        .user(userConverter.toDto(order.getUser()))
        .build();
  }

  @Override
  public Order toEntity(OrderDto orderDto) {
    return Order.builder().id(orderDto.getId())
        .carModel(orderDto.getCarModel())
        .city_from(cityConverter.toEntity(orderDto.getCity_from()))
        .city_to(cityConverter.toEntity(orderDto.getCity_to()))
        .user(userConverter.toEntity(orderDto.getUser()))
        .build();
  }

  @Override
  public List<OrderDto> toDtoList(Iterable<Order> orderList) {
    List<OrderDto> result = new ArrayList<>();
    for (Order order : orderList) {
      result.add(toDto(order));
    }
    return result;
  }

  @Override
  public List<Order> toEntityList(Iterable<OrderDto> orderDtoList) {
    List<Order> result = new ArrayList<>();
    for (OrderDto orderDto : orderDtoList) {
      result.add(toEntity(orderDto));
    }
    return result;
  }
}
