package com.epam.rd.edu.petProject.service.impl;

import com.epam.rd.edu.petProject.converter.ModelConverter;
import com.epam.rd.edu.petProject.converter.SimpleConverterFactory;
import com.epam.rd.edu.petProject.dao.Query;
import com.epam.rd.edu.petProject.dao.SimpleDaoFactory;
import com.epam.rd.edu.petProject.dao.UserDao;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.exception.DaoException;
import com.epam.rd.edu.petProject.exception.UserWithEmailExistException;
import com.epam.rd.edu.petProject.exception.UserWithLoginExistException;
import com.epam.rd.edu.petProject.service.Service;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;

import java.util.List;

public class UserDtoService implements UserDtoServiceInterface {

    private UserDao userDao = SimpleDaoFactory.getDaoFactory().getUserDao();
    private ModelConverter<User, UserDto> userConverter = SimpleConverterFactory.getConverter().getUserConverter();

    @Override
    public UserDto create(UserDto userDto) {
        if (userWithEmailExist(userDto)) {
            throw new UserWithEmailExistException();
        }
        if (userWithLoginExist(userDto)) {
            throw new UserWithLoginExistException();
        }
        User user = userConverter.toEntity(userDto);
        return userConverter.toDto(userDao.create(user, Query.USER_CREATE.getQuery()));
    }

    @Override
    public boolean delete(Integer key) {
        return userDao.delete(key, Query.USER_DELETE.getQuery());
    }

    @Override
    public boolean update(UserDto userDto) {
        return userDao.update(userConverter.toEntity(userDto), Query.USER_UPDATE.getQuery());
    }

    @Override
    public UserDto read(Integer key) {
        return userConverter.toDto(userDao.read(key, Query.USER_READ.getQuery()));
    }

    @Override
    public List<UserDto> getAll() {
        return userConverter.toDtoList(userDao.getAll(Query.USER_GET_ALL.getQuery()));
    }

    @Override
    public List<UserDto> create(List<UserDto> userDtoList) {
        List<User> userList = userConverter.toEntityList(userDtoList);
        return userConverter.toDtoList(userDao.create(userList, Query.USER_CREATE_LIST.getQuery()));
    }

    @Override
    public boolean update(List<UserDto> userDtoList) {
        return userDao.update(userConverter.toEntityList(userDtoList), Query.USER_UPDATE_LIST.getQuery());
    }

    @Override
    public UserDto getByLogin(String login) {
        return userConverter.toDto(userDao.readByOneStringField(login, Query.USER_GET_BY_LOGIN.getQuery()));
    }

    @Override
    public boolean userWithEmailExist(UserDto userDto) {
        try {
            userConverter.toDto(userDao.readByOneStringField(userDto.getEmail(), Query.USER_GET_BY_EMAIL.getQuery()));
            return true;
        } catch (DaoException ex) {
            return false;
        }
    }

    @Override
    public boolean userWithLoginExist(UserDto userDto) {
        try {
            userConverter.toDto(userDao.readByOneStringField(userDto.getLogin(), Query.USER_GET_BY_LOGIN.getQuery()));
            return true;
        } catch (DaoException ex) {
            return false;
        }
    }
}
