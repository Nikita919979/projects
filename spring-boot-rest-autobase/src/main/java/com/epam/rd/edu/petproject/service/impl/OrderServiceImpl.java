package com.epam.rd.edu.petproject.service.impl;

import com.epam.rd.edu.petproject.converter.ModelConverter;
import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.exception.EntityNotFoundException;
import com.epam.rd.edu.petproject.model.Order;
import com.epam.rd.edu.petproject.repository.OrderRepository;
import com.epam.rd.edu.petproject.service.OrderService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepo;
  private ModelConverter<Order, OrderDto> orderConverter;

  @Override
  public OrderDto create(OrderDto orderDto) {
    Order order = orderConverter.toEntity(orderDto);
    return orderConverter.toDto(orderRepo.save(order));
  }

  @Override
  public void delete(UUID key) {
    orderRepo.delete(orderRepo.findById(key).get());
  }

  @Override
  public void update(OrderDto orderDto) {
    orderRepo.save(orderConverter.toEntity(orderDto));
  }

  @Override
  public OrderDto read(UUID key) {
    try {
      return orderConverter.toDto(orderRepo.findById(key).get());
    } catch (
        NoSuchElementException ex) {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public List<OrderDto> getAll() {
    return orderConverter.toDtoList(orderRepo.findAll());
  }

  @Override
  public List<OrderDto> createAll(List<OrderDto> orderDtoList) {
    List<Order> orderList = orderConverter.toEntityList(orderDtoList);
    return orderConverter.toDtoList(orderRepo.saveAll(orderList));
  }

  @Override
  public void updateAll(List<OrderDto> orderDtoList) {
    orderRepo.saveAll(orderConverter.toEntityList(orderDtoList));
  }
}
