package com.epam.rd.edu.petProject.service.impl;

import com.epam.rd.edu.petProject.converter.ModelConverter;
import com.epam.rd.edu.petProject.converter.SimpleConverterFactory;
import com.epam.rd.edu.petProject.dao.Query;
import com.epam.rd.edu.petProject.dao.SimpleDaoFactory;
import com.epam.rd.edu.petProject.dao.OrderDao;
import com.epam.rd.edu.petProject.model.Order;
import com.epam.rd.edu.petProject.service.OrderDtoServiceInterface;
import com.epam.rd.edu.petProject.service.Service;
import com.epam.rd.edu.petProject.dto.OrderDto;

import java.util.List;

public class OrderDtoService implements OrderDtoServiceInterface {

    private OrderDao orderDao = SimpleDaoFactory.getDaoFactory().getOrderDao();
    private ModelConverter<Order, OrderDto> orderConverter = SimpleConverterFactory.getConverter().getOrderConverter();

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = orderConverter.toEntity(orderDto);
        return orderConverter.toDto(orderDao.create(order, Query.ORDER_CREATE.getQuery()));
    }

    @Override
    public boolean delete(Integer key) {
        return orderDao.delete(key, Query.ORDER_DELETE.getQuery());
    }

    @Override
    public boolean update(OrderDto orderDto) {
        return orderDao.update(orderConverter.toEntity(orderDto), Query.ORDER_UPDATE.getQuery());
    }

    @Override
    public OrderDto read(Integer key) {
        return orderConverter.toDto(orderDao.read(key, Query.ORDER_READ.getQuery()));
    }

    @Override
    public List<OrderDto> getAll() {
        return orderConverter.toDtoList(orderDao.getAll(Query.ORDER_GET_ALL.getQuery()));
    }

    @Override
    public List<OrderDto> create(List<OrderDto> orderDtoList) {
        List<Order> orderList = orderConverter.toEntityList(orderDtoList);
        return orderConverter.toDtoList(orderDao.create(orderList, Query.ORDER_CREATE_LIST.getQuery()));
    }

    @Override
    public boolean update(List<OrderDto> orderDtoList) {
        return orderDao.update(orderConverter.toEntityList(orderDtoList), Query.ORDER_UPDATE_LIST.getQuery());
    }
}
