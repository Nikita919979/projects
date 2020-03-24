package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.City;
import com.epam.rd.edu.petProject.model.Order;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.OrderDto;
import com.epam.rd.edu.petProject.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter implements ModelConverter<Order, OrderDto> {
    private final ModelConverter<User, UserDto> userConverter = SimpleConverterFactory.getConverter().getUserConverter();
    private final ModelConverter<City, CityDto> cityConverter = SimpleConverterFactory.getConverter().getCityConverter();

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
    public List<OrderDto> toDtoList(List<Order> orderList) {
        List<OrderDto> result = new ArrayList<>();
        for (Order order : orderList) {
            result.add(toDto(order));
        }
        return result;
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> orderDtoList) {
        List<Order> result = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            result.add(toEntity(orderDto));
        }
        return result;
    }
}
