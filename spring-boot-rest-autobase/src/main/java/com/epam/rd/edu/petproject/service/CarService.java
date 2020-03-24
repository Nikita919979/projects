package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.CarDto;
import java.util.List;

public interface CarService {

  CarDto create(CarDto carDto);

  void delete(Integer key);

  void update(CarDto carDto);

  CarDto read(Integer key);

  List<CarDto> getAll();

  List<CarDto> createAll(List<CarDto> carDtoList);

  void updateAll(List<CarDto> carDtoList);

}
