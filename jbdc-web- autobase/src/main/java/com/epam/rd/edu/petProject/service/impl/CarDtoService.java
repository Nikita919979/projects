package com.epam.rd.edu.petProject.service.impl;

import com.epam.rd.edu.petProject.converter.ModelConverter;
import com.epam.rd.edu.petProject.converter.SimpleConverterFactory;
import com.epam.rd.edu.petProject.dao.CarDao;
import com.epam.rd.edu.petProject.dao.Query;
import com.epam.rd.edu.petProject.dao.SimpleDaoFactory;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.Service;
import com.epam.rd.edu.petProject.dto.CarDto;

import java.util.List;

public class CarDtoService implements CarDtoServiceInterface {

    private CarDao carDao = SimpleDaoFactory.getDaoFactory().getCarDao();
    private ModelConverter<Car, CarDto> carConverter = SimpleConverterFactory.getConverter().getCarConverter();

    @Override
    public CarDto create(CarDto carDto) {
        Car car = carDao.create(carConverter.toEntity(carDto), Query.CAR_CREATE.getQuery());
        return carConverter.toDto(car);
    }

    @Override
    public boolean delete(Integer key) {
        return carDao.delete(key, Query.CAR_DELETE.getQuery());
    }

    @Override
    public boolean update(CarDto carDto) {
        return carDao.update(carConverter.toEntity(carDto), Query.CAR_UPDATE.getQuery());
    }

    @Override
    public CarDto read(Integer key) {
        return carConverter.toDto(carDao.read(key, Query.CAR_READ.getQuery()));
    }

    @Override
    public List<CarDto> getAll() {
        return carConverter.toDtoList(carDao.getAll(Query.CAR_GET_ALL.getQuery()));
    }

    @Override
    public List<CarDto> create(List<CarDto> carDtoList) {
        List<Car> carList = carConverter.toEntityList(carDtoList);
        return carConverter.toDtoList(carDao.create(carList, Query.CAR_CREATE_LIST.getQuery()));
    }

    @Override
    public boolean update(List<CarDto> carDtoList) {
        return carDao.update(carConverter.toEntityList(carDtoList), Query.CAR_UPDATE_LIST.getQuery());
    }

}
