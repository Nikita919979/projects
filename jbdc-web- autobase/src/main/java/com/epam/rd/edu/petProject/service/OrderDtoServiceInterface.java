package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.dto.OrderDto;

import java.util.List;

public interface OrderDtoServiceInterface {

    OrderDto create(OrderDto orderDto);

    boolean delete(Integer key);

    boolean update(OrderDto orderDto);

    OrderDto read(Integer key);

    List<OrderDto> getAll();

    List<OrderDto> create(List<OrderDto> orderDtoList);

    boolean update(List<OrderDto> orderDtoList);
}
