package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.model.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class CarDao extends GenericAbstractDao<Car> {

    @Override
    public Car getClone(Car car) {
        return car.clone();
    }

    @Override
    public void setEntityParametersForCreate(Car car, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, car.getModel().toString());
        preparedStatement.setString(2, car.getCarNumber());
        preparedStatement.setString(3, car.getCarTechnicalPassport());
        preparedStatement.setDate(4, java.sql.Date.valueOf(car.getReleaseDate()));
        preparedStatement.setBoolean(5, car.isFully_Functional());
    }

    @Override
    public void setEntityParametersForUpdate(Car car, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, car.getModel().toString());
        preparedStatement.setString(2, car.getCarNumber());
        preparedStatement.setString(3, car.getCarTechnicalPassport());
        preparedStatement.setDate(4, java.sql.Date.valueOf(car.getReleaseDate()));
        preparedStatement.setBoolean(5, car.isFully_Functional());
        preparedStatement.setInt(6, car.getId());
    }

    @Override
    public Car getEntity(ResultSet resultSet) throws SQLException {

        return Car.builder().id(resultSet.getInt("car_id"))
                .model(Car.CarModel.valueOf(resultSet.getString("model")))
                .carNumber(resultSet.getString("number"))
                .carTechnicalPassport(resultSet.getString("technical_passport"))
                .releaseDate(resultSet.getDate("release_date").toLocalDate())
                .fully_Functional(resultSet.getBoolean("fully_Functional"))
                .build();

    }
}
