package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.model.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao extends GenericAbstractDao<City> {

    @Override
    public City getClone(City city) {
        return city.clone();
    }

    @Override
    public void setEntityParametersForCreate(City city, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, city.getName());
    }

    @Override
    public void setEntityParametersForUpdate(City city, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, city.getName());
        preparedStatement.setInt(2, city.getId());
    }

    @Override
    public City getEntity(ResultSet resultSet) throws SQLException {
        return City.builder().id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .build();
    }
}
