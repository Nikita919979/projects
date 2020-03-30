package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.CarDto;
import java.util.List;
import java.util.UUID;

public interface CarService {

  CarDto create(CarDto carDto);

  void delete(UUID key);

  void update(CarDto carDto);

  CarDto read(UUID key);

  List<CarDto> getAll();

  List<CarDto> createAll(List<CarDto> carDtoList);

  void updateAll(List<CarDto> carDtoList);

}
