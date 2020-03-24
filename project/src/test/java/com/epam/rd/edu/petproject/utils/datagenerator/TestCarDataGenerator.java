package com.epam.rd.edu.petproject.utils.datagenerator;

import com.epam.rd.edu.petproject.converter.CarConverter;
import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.model.Car;
import com.epam.rd.edu.petproject.model.Car.CarModel;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestCarDataGenerator {

  private final Random random = new Random();
  private final CarConverter carConverter = new CarConverter();

  public List<CarDto> generateRequestCarDtoList(int count) {
    return IntStream.range(0, count)
        .mapToObj(TestCarDataGenerator::generateCarDtoWithRandomModel)
        .collect(Collectors.toList());
  }

  public CarDto generateCarDto(int counter, Car.CarModel model) {
    return CarDto.builder()
        .id(counter)
        .model(model)
        .carNumber("testCarNumber" + counter)
        .carTechnicalPassport("testCarTechnicalPassport" + counter)
        .fully_Functional(random.nextBoolean())
        .releaseDate(LocalDate.now())
        .build();
  }

  public CarDto generateCarDtoWithRandomModel(int counter) {
    return CarDto.builder()
        .id(counter)
        .model(Arrays.stream(CarModel.values()).findAny().get())
        .carNumber("testCarNumber" + counter)
        .carTechnicalPassport("testCarTechnicalPassport" + counter)
        .fully_Functional(random.nextBoolean())
        .releaseDate(LocalDate.now())
        .build();
  }

  public Car getCar(CarDto carDto) {
    return carConverter.toEntity(carDto);
  }

  public List<Car> getCarList(List<CarDto> carDtoList) {
    return carConverter.toEntityList(carDtoList);
  }
}
