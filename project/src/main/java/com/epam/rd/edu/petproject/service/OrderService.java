package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.OrderDto;
import java.util.List;

public interface OrderService {

  OrderDto create(OrderDto orderDto);

  void delete(Integer key);

  void update(OrderDto orderDto);

  OrderDto read(Integer key);

  List<OrderDto> getAll();

  List<OrderDto> createAll(List<OrderDto> orderDtoList);

  void updateAll(List<OrderDto> orderDtoList);
}
