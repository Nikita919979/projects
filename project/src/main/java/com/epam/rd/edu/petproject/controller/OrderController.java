package com.epam.rd.edu.petproject.controller;

import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.service.OrderService;
import com.epam.rd.edu.petproject.utils.aspect.Timed;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

  private OrderService orderDtoService;

  @Timed
  @GetMapping("/{id}")
  public OrderDto getOrderDto(@PathVariable String id) {
    return orderDtoService.read(Integer.parseInt(id));
  }

  @Timed
  @GetMapping
  public List<OrderDto> getOrdersList() {
    return orderDtoService.getAll();
  }

  @Timed
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto createOrderDto(@RequestBody OrderDto orderDto) {
    return orderDtoService.create(orderDto);
  }

  @Timed
  @PutMapping
  public void updateOrderDto(@RequestBody OrderDto orderDto) {
    orderDtoService.update(orderDto);
  }

  @Timed
  @DeleteMapping(value = "/{id}")
  public void deleteOrderDto(@PathVariable String id) {
    orderDtoService.delete(Integer.parseInt(id));
  }
}
