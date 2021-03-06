package com.epam.rd.edu.petproject.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.epam.rd.edu.petproject.converter.CityConverter;
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.model.City;
import com.epam.rd.edu.petproject.repository.CityRepository;
import com.epam.rd.edu.petproject.service.impl.CityServiceImpl;
import com.epam.rd.edu.petproject.utils.datagenerator.TestCityDataGenerator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;

public class CityServiceImplTest {

  private final CityRepository cityRepository = mock(CityRepository.class);
  private final CityConverter cityConverter = mock(CityConverter.class);
  private final CityServiceImpl sut = new CityServiceImpl(cityRepository, cityConverter);

  @Test
  public void shouldReturnCreatedEntity() {
    //Given
    CityDto cityDto = TestCityDataGenerator.generateCityDto(1);
    City city = TestCityDataGenerator.getCity(cityDto);
    doReturn(city).when(cityRepository).save(city);
    doReturn(city).when(cityConverter).toEntity(cityDto);
    doReturn(cityDto).when(cityConverter).toDto(city);

    //When
    CityDto cityDtoVerify = sut.create(cityDto);

    //Then
    verify(cityRepository).save(city);
    assertThat(cityDtoVerify, hasProperty("uuid", is(cityDto.getUuid())));
    assertThat(cityDtoVerify, hasProperty("name", is(cityDto.getName())));
  }

  @Test
  public void shouldDeleteCity() {
    //Given
    CityDto cityDto = TestCityDataGenerator.generateCityDto(1);
    City city = TestCityDataGenerator.getCity(cityDto);

    //When
    sut.delete(cityDto.getUuid());

    //Then
    verify(cityRepository).deleteById(city.getUuid());
  }

  @Test
  public void shouldUpdateCar() {
    //Given
    CityDto cityDto = TestCityDataGenerator.generateCityDto(1);
    City city = TestCityDataGenerator.getCity(cityDto);
    doReturn(city).when(cityRepository).save(city);
    doReturn(city).when(cityConverter).toEntity(cityDto);

    //When
    sut.update(cityDto);

    //Then
    verify(cityRepository).save(city);
  }

  @Test
  public void shouldReturnFoundedEntity() {
    //Given
    CityDto cityDto = TestCityDataGenerator.generateCityDto(1);
    City city = TestCityDataGenerator.getCity(cityDto);
    Optional<City> cityOptional = Optional.of(city);
    doReturn(cityOptional).when(cityRepository)
        .findById(UUID.fromString("caad8f82-71db-11ea-bc55-0242ac130003"));
    doReturn(cityDto).when(cityConverter).toDto(city);

    //When
    CityDto cityDtoVerify = sut.read(UUID.fromString("caad8f82-71db-11ea-bc55-0242ac130003"));

    //Then
    verify(cityRepository).findById(UUID.fromString("caad8f82-71db-11ea-bc55-0242ac130003"));
    assertThat(cityDtoVerify, hasProperty("uuid", is(cityDto.getUuid())));
    assertThat(cityDtoVerify, hasProperty("name", is(cityDto.getName())));
  }

  @Test
  public void shouldReturnAllFoundedEntities() {
    //Given
    List<CityDto> cityDtoList = TestCityDataGenerator.generateRequestCityDtoList(5);
    List<City> cityList = TestCityDataGenerator.getCityList(cityDtoList);
    doReturn(cityList).when(cityRepository).findAll();
    doReturn(cityDtoList).when(cityConverter).toDtoList(cityList);

    //When
    List<CityDto> cityDtoReturnList = sut.getAll();

    //Then
    verify(cityRepository).findAll();
    assertThat(cityDtoReturnList, not(is(empty())));
    cityDtoList.forEach(
        entity -> {
          assertThat(cityDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("name", is(entity.getName()))));
        }
    );
  }

  @Test
  public void shouldUpdateCityList() {
    //Given
    List<CityDto> cityDtoList = TestCityDataGenerator.generateRequestCityDtoList(5);
    List<City> cityList = TestCityDataGenerator.getCityList(cityDtoList);
    doReturn(cityList).when(cityRepository).saveAll(cityList);
    doReturn(cityList).when(cityConverter).toEntityList(cityDtoList);

    //When
    sut.updateAll(cityDtoList);

    //Then
    verify(cityRepository).saveAll(cityList);
    verify(cityConverter).toEntityList(cityDtoList);
  }

  @Test
  public void shouldReturnAllCreatedEntities() {
    //Given
    List<CityDto> cityDtoList = TestCityDataGenerator.generateRequestCityDtoList(5);
    List<City> cityList = TestCityDataGenerator.getCityList(cityDtoList);
    doReturn(cityList).when(cityRepository).saveAll(cityList);
    doReturn(cityList).when(cityConverter).toEntityList(cityDtoList);
    doReturn(cityDtoList).when(cityConverter).toDtoList(cityList);

    //When
    List<CityDto> cityDtoReturnList = sut.createAll(cityDtoList);

    //Then
    verify(cityRepository).saveAll(cityList);
    assertThat(cityDtoReturnList, not(is(empty())));
    cityDtoList.forEach(
        entity -> {
          assertThat(cityDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("name", is(entity.getName()))));
        }
    );
  }
}
