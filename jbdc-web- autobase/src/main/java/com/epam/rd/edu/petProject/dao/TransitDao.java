package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.model.Transit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransitDao extends GenericAbstractDao<Transit> {

    @Override
    public Transit getClone(Transit transit) {
        return transit.clone();
    }

    @Override
    public void setEntityParametersForCreate(Transit transit, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, transit.getStatus().toString());
        preparedStatement.setInt(2, transit.getCity_from().getId());
        preparedStatement.setInt(3, transit.getCity_to().getId());
        preparedStatement.setInt(4, transit.getCar().getId());
        preparedStatement.setInt(5, transit.getUser().getId());
        preparedStatement.setInt(6, transit.getDriver().getId());
    }

    @Override
    public void setEntityParametersForUpdate(Transit transit, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, transit.getStatus().toString());
        preparedStatement.setInt(2, transit.getCity_from().getId());
        preparedStatement.setInt(3, transit.getCity_to().getId());
        preparedStatement.setInt(4, transit.getCar().getId());
        preparedStatement.setInt(5, transit.getUser().getId());
        preparedStatement.setInt(6, transit.getDriver().getId());
        preparedStatement.setInt(7, transit.getId());
    }

    @Override
    public Transit getEntity(ResultSet resultSet) throws SQLException {
        return Transit.builder().id(resultSet.getInt(1))
                .status(Transit.Status.valueOf(resultSet.getString(2)))
                .city_from(SimpleDaoFactory.getDaoFactory().getCityDao().read(resultSet.getInt(3)
                        , Query.CITY_READ.getQuery()))
                .city_to(SimpleDaoFactory.getDaoFactory().getCityDao().read(resultSet.getInt(4)
                        , Query.CITY_READ.getQuery()))
                .car(SimpleDaoFactory.getDaoFactory().getCarDao().read(resultSet.getInt(5)
                        , Query.CAR_READ.getQuery()))
                .user(SimpleDaoFactory.getDaoFactory().getUserDao().read(resultSet.getInt(6)
                        , Query.USER_READ.getQuery()))
                .driver(SimpleDaoFactory.getDaoFactory().getUserDao().read(resultSet.getInt(7)
                        , Query.USER_READ.getQuery()))
                .build();
    }
}
