package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.dto.CarDto;

import java.util.List;

public interface CarDtoServiceInterface {

    CarDto create(CarDto carDto);

    boolean delete(Integer key);

    boolean update(CarDto carDto);

    CarDto read(Integer key);

    List<CarDto> getAll();

    List<CarDto> create(List<CarDto> carDtoList);

    boolean update(List<CarDto> carDtoList);

}
