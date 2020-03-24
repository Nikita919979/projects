package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends GenericAbstractDao<User> {

    @Override
    public User getClone(User user) {
        return user.clone();
    }

    @Override
    public void setEntityParametersForCreate(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getFamilyName());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.setString(6, user.getEmail());
    }

    @Override
    public void setEntityParametersForUpdate(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getFamilyName());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setInt(7, user.getId());
    }

    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        return User.builder().id(resultSet.getInt("user_id"))
                .name(resultSet.getString("name"))
                .familyName(resultSet.getString("family_name"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .role(User.Role.valueOf(resultSet.getString("role")))
                .email(resultSet.getString("email"))
                .build();
    }
}
