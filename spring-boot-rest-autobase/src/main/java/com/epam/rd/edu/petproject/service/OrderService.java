package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.OrderDto;
import java.util.List;
import java.util.UUID;

public interface OrderService {

  OrderDto create(OrderDto orderDto);

  void delete(UUID key);

  void update(OrderDto orderDto);

  OrderDto read(UUID key);

  List<OrderDto> getAll();

  List<OrderDto> createAll(List<OrderDto> orderDtoList);

  void updateAll(List<OrderDto> orderDtoList);
}
