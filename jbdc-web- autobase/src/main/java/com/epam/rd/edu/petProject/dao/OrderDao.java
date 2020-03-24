package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao extends GenericAbstractDao<Order> {

    @Override
    public Order getClone(Order order) {
        return order.clone();
    }

    @Override
    public void setEntityParametersForCreate(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, order.getCarModel().toString());
        preparedStatement.setInt(2, order.getCity_from().getId());
        preparedStatement.setInt(3, order.getCity_to().getId());
        preparedStatement.setInt(4, order.getUser().getId());
    }

    @Override
    public void setEntityParametersForUpdate(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, order.getCarModel().toString());
        preparedStatement.setInt(2, order.getCity_from().getId());
        preparedStatement.setInt(3, order.getCity_to().getId());
        preparedStatement.setInt(4, order.getUser().getId());
        preparedStatement.setInt(5, order.getId());
    }

    @Override
    public Order getEntity(ResultSet resultSet) throws SQLException {
        return Order.builder().id(resultSet.getInt(1))
                .carModel(Car.CarModel.valueOf(resultSet.getString(2)))
                .city_from(SimpleDaoFactory.getDaoFactory().getCityDao().read(resultSet.getInt(3)
                        , Query.CITY_READ.getQuery()))
                .city_to(SimpleDaoFactory.getDaoFactory().getCityDao().read(resultSet.getInt(4)
                        , Query.CITY_READ.getQuery()))
                .user(SimpleDaoFactory.getDaoFactory().getUserDao().read(resultSet.getInt(5)
                        , Query.USER_READ.getQuery()))
                .build();
    }

}
