package com.epam.rd.edu.petproject.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.epam.rd.edu.petproject.converter.CarConverter;
import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.model.Car;
import com.epam.rd.edu.petproject.repository.CarRepository;
import com.epam.rd.edu.petproject.service.impl.CarServiceImpl;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCarDataGenerator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;

public class CarServiceImplTest {

  private final CarRepository carRepository = mock(CarRepository.class);
  private final CarConverter carConverter = mock(CarConverter.class);
  private final CarServiceImpl sut = new CarServiceImpl(carRepository, carConverter);

  @Test
  public void shouldReturnCreatedEntity() {
    //Given
    CarDto carDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    Car car = TestCarDataGenerator.getCar(carDto);
    doReturn(car).when(carRepository).save(car);
    doReturn(car).when(carConverter).toEntity(carDto);
    doReturn(carDto).when(carConverter).toDto(car);

    //When
    CarDto carDtoVerify = sut.create(carDto);

    //Then
    verify(carRepository).save(car);
    assertThat(carDtoVerify, hasProperty("uuid", is(carDto.getUuid())));
    assertThat(carDtoVerify, hasProperty("releaseDate", is(carDto.getReleaseDate())));
    assertThat(carDtoVerify, hasProperty("fully_Functional", is(carDto.isFully_Functional())));
    assertThat(carDtoVerify,
        hasProperty("carTechnicalPassport", is(carDto.getCarTechnicalPassport())));
    assertThat(carDtoVerify, hasProperty("carNumber", is(carDto.getCarNumber())));
    assertThat(carDtoVerify, hasProperty("model", is(carDto.getModel())));
  }

  @Test
  public void shouldDeleteCar() {
    //Given
    CarDto carDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    Car car = TestCarDataGenerator.getCar(carDto);
    Optional<Car> carOptional = Optional.of(car);
    doReturn(carOptional).when(carRepository).findById(car.getUuid());

    //When
    sut.delete(UUID.fromString("8e45a958-71db-11ea-bc55-0242ac130003"));

    //Then
    verify(carRepository).delete(car);
  }

  @Test
  public void shouldUpdateCar() {
    //Given
    CarDto carDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    Car car = TestCarDataGenerator.getCar(carDto);
    doReturn(car).when(carRepository).save(car);
    doReturn(car).when(carConverter).toEntity(carDto);

    //When
    sut.update(carDto);

    //Then
    verify(carRepository).save(car);
  }

  @Test
  public void shouldReturnFoundedEntity() {
    //Given
    CarDto carDto = TestCarDataGenerator.generateCarDtoWithRandomModel(1);
    Car car = TestCarDataGenerator.getCar(carDto);
    Optional<Car> carOptional = Optional.of(car);
    doReturn(carOptional).when(carRepository)
        .findById(UUID.fromString("8e45a958-71db-11ea-bc55-0242ac130003"));
    doReturn(carDto).when(carConverter).toDto(car);

    //When
    CarDto carDtoVerify = sut.read(UUID.fromString("8e45a958-71db-11ea-bc55-0242ac130003"));

    //Then
    assertThat(carDtoVerify, hasProperty("uuid", is(carDto.getUuid())));
    assertThat(carDtoVerify, hasProperty("releaseDate", is(carDto.getReleaseDate())));
    assertThat(carDtoVerify, hasProperty("fully_Functional", is(carDto.isFully_Functional())));
    assertThat(carDtoVerify,
        hasProperty("carTechnicalPassport", is(carDto.getCarTechnicalPassport())));
    assertThat(carDtoVerify, hasProperty("carNumber", is(carDto.getCarNumber())));
    assertThat(carDtoVerify, hasProperty("model", is(carDto.getModel())));
  }

  @Test
  public void shouldReturnAllFoundedEntities() {
    //Given
    List<CarDto> carDtoList = TestCarDataGenerator.generateRequestCarDtoList(5);
    List<Car> carList = TestCarDataGenerator.getCarList(carDtoList);
    doReturn(carList).when(carRepository).findAll();
    doReturn(carDtoList).when(carConverter).toDtoList(carList);

    //When
    List<CarDto> carDtoReturnList = sut.getAll();

    //Then
    verify(carRepository).findAll();
    assertThat(carDtoReturnList, not(is(empty())));
    carDtoList.forEach(
        entity -> {
          assertThat(carDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(carDtoReturnList,
              hasItem(hasProperty("releaseDate", is(entity.getReleaseDate()))));
          assertThat(carDtoReturnList,
              hasItem(hasProperty("fully_Functional", is(entity.isFully_Functional()))));
          assertThat(carDtoReturnList,
              hasItem(hasProperty("carTechnicalPassport", is(entity.getCarTechnicalPassport()))));
          assertThat(carDtoReturnList,
              hasItem(hasProperty("carNumber", is(entity.getCarNumber()))));
          assertThat(carDtoReturnList, hasItem(hasProperty("model", is(entity.getModel()))));
        }
    );
  }

  @Test
  public void shouldUpdateCarList() {
    //Given
    List<CarDto> carDtoList = TestCarDataGenerator.generateRequestCarDtoList(5);
    List<Car> carList = TestCarDataGenerator.getCarList(carDtoList);
    doReturn(carList).when(carRepository).saveAll(carList);
    doReturn(carList).when(carConverter).toEntityList(carDtoList);

    //When
    sut.updateAll(carDtoList);

    //Then
    verify(carRepository).saveAll(carList);
  }

  @Test
  public void shouldReturnAllCreatedEntities() {
    //Given
    List<CarDto> carDtoList = TestCarDataGenerator.generateRequestCarDtoList(5);
    List<Car> carList = TestCarDataGenerator.getCarList(carDtoList);
    doReturn(carList).when(carRepository).saveAll(carList);
    doReturn(carList).when(carConverter).toEntityList(carDtoList);
    doReturn(carDtoList).when(carConverter).toDtoList(carList);

    //When
    List<CarDto> carDtoReturnList = sut.createAll(carDtoList);

    //Then
    verify(carRepository).saveAll(carList);
    assertThat(carDtoReturnList, not(is(empty())));
    carDtoList.forEach(
        entity -> assertThat(carDtoReturnList, hasItem(allOf(
            hasProperty("uuid", is(entity.getUuid())),
            hasProperty("releaseDate", is(entity.getReleaseDate())),
            hasProperty("fully_Functional", is(entity.isFully_Functional())),
            hasProperty("carTechnicalPassport", is(entity.getCarTechnicalPassport())),
            hasProperty("carNumber", is(entity.getCarNumber())),
            hasProperty("model", is(entity.getModel())))))
    );
  }
}
