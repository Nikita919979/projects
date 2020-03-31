package com.epam.rd.edu.petproject.service.impl;

import com.epam.rd.edu.petproject.converter.ModelConverter;
import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.exception.EntityNotFoundException;
import com.epam.rd.edu.petproject.model.Car;
import com.epam.rd.edu.petproject.repository.CarRepository;
import com.epam.rd.edu.petproject.service.CarService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

  private CarRepository carRepo;
  private ModelConverter<Car, CarDto> carConverter;

  @Override
  public CarDto create(CarDto carDto) {
    Car car = carRepo.save(carConverter.toEntity(carDto));
    return carConverter.toDto(car);
  }

  @Override
  public void delete(UUID key) {
    carRepo.deleteById(key);
  }

  @Override
  public void update(CarDto carDto) {
    carRepo.save(carConverter.toEntity(carDto));
  }

  @Override
  public CarDto read(UUID key) {
    try {
      return carConverter.toDto(carRepo.findById(key).get());
    } catch (NoSuchElementException ex) {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public List<CarDto> getAll() {
    return carConverter.toDtoList(carRepo.findAll());
  }

  @Override
  public List<CarDto> createAll(List<CarDto> carDtoList) {
    List<Car> carList = carConverter.toEntityList(carDtoList);
    return carConverter.toDtoList(carRepo.saveAll(carList));
  }

  @Override
  public void updateAll(List<CarDto> carDtoList) {
    carRepo.saveAll(carConverter.toEntityList(carDtoList));
  }
}
