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

import com.epam.rd.edu.petproject.converter.TransitConverter;
import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.model.Transit;
import com.epam.rd.edu.petproject.repository.TransitRepository;
import com.epam.rd.edu.petproject.service.impl.TransitServiceImpl;
import com.epam.rd.edu.petproject.utils.datagenerator.TestTransitDataGenerator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;

public class TransitServiceImplTest {

  private final TransitRepository transitRepository = mock(TransitRepository.class);
  private final TransitConverter transitConverter = mock(TransitConverter.class);
  private final TransitServiceImpl sut = new TransitServiceImpl(transitRepository,
      transitConverter);

  @Test
  public void shouldReturnCreatedEntity() {
    //Given
    TransitDto transitDto = TestTransitDataGenerator.generateTransitDto(1);
    Transit transit = TestTransitDataGenerator.getTransit(transitDto);
    doReturn(transit).when(transitRepository).save(transit);
    doReturn(transit).when(transitConverter).toEntity(transitDto);
    doReturn(transitDto).when(transitConverter).toDto(transit);

    //When
    TransitDto cityDtoVerify = sut.create(transitDto);

    //Then
    verify(transitRepository).save(transit);
    assertThat(cityDtoVerify, hasProperty("uuid", is(transitDto.getUuid())));
    assertThat(cityDtoVerify, hasProperty("status", is(transitDto.getStatus())));
    assertThat(cityDtoVerify, hasProperty("city_from", is(transitDto.getCity_from())));
    assertThat(cityDtoVerify, hasProperty("city_to", is(transitDto.getCity_to())));
    assertThat(cityDtoVerify, hasProperty("car", is(transitDto.getCar())));
    assertThat(cityDtoVerify, hasProperty("user", is(transitDto.getUser())));
    assertThat(cityDtoVerify, hasProperty("driver", is(transitDto.getDriver())));
  }

  @Test
  public void shouldDeleteTransit() {
    //Given
    TransitDto transitDto = TestTransitDataGenerator.generateTransitDto(1);
    Transit transit = TestTransitDataGenerator.getTransit(transitDto);

    //When
    sut.delete(transitDto.getUuid());

    //Then
    verify(transitRepository).deleteById(transit.getUuid());
  }

  @Test
  public void shouldUpdateTransit() {
    //Given
    TransitDto transitDto = TestTransitDataGenerator.generateTransitDto(1);
    Transit transit = TestTransitDataGenerator.getTransit(transitDto);
    doReturn(transit).when(transitRepository).save(transit);
    doReturn(transit).when(transitConverter).toEntity(transitDto);

    //When
    sut.update(transitDto);

    //Then
    verify(transitRepository).save(transit);
  }

  @Test
  public void shouldReturnFoundedEntity() {
    //Given
    TransitDto transitDto = TestTransitDataGenerator.generateTransitDto(1);
    Transit transit = TestTransitDataGenerator.getTransit(transitDto);
    Optional<Transit> carOptional = Optional.of(transit);
    doReturn(carOptional).when(transitRepository)
        .findById(UUID.fromString("f1344876-71db-11ea-bc55-0242ac130003"));
    doReturn(transitDto).when(transitConverter).toDto(transit);

    //When
    TransitDto cityDtoVerify = sut.read(UUID.fromString("f1344876-71db-11ea-bc55-0242ac130003"));

    //Then
    verify(transitRepository).findById(UUID.fromString("f1344876-71db-11ea-bc55-0242ac130003"));
    assertThat(cityDtoVerify, hasProperty("uuid", is(transitDto.getUuid())));
    assertThat(cityDtoVerify, hasProperty("status", is(transitDto.getStatus())));
    assertThat(cityDtoVerify, hasProperty("city_from", is(transitDto.getCity_from())));
    assertThat(cityDtoVerify, hasProperty("city_to", is(transitDto.getCity_to())));
    assertThat(cityDtoVerify, hasProperty("car", is(transitDto.getCar())));
    assertThat(cityDtoVerify, hasProperty("user", is(transitDto.getUser())));
    assertThat(cityDtoVerify, hasProperty("driver", is(transitDto.getDriver())));
  }

  @Test
  public void shouldReturnAllFoundedEntities() {
    //Given
    List<TransitDto> transitDtoList = TestTransitDataGenerator.generateRequestTransitDtoList(5);
    List<Transit> transitList = TestTransitDataGenerator.getTransitList(transitDtoList);
    doReturn(transitList).when(transitRepository).findAll();
    doReturn(transitDtoList).when(transitConverter).toDtoList(transitList);

    //When
    List<TransitDto> cityDtoReturnList = sut.getAll();

    //Then
    verify(transitRepository).findAll();
    assertThat(cityDtoReturnList, not(is(empty())));
    transitDtoList.forEach(
        entity -> {
          assertThat(cityDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("status", is(entity.getStatus()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_from", is(entity.getCity_from()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_to", is(entity.getCity_to()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("car", is(entity.getCar()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("user", is(entity.getUser()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("driver", is(entity.getDriver()))));
        }
    );
  }

  @Test
  public void shouldUpdateAllTransits() {
    //Given
    List<TransitDto> transitDtoList = TestTransitDataGenerator.generateRequestTransitDtoList(5);
    List<Transit> transitList = TestTransitDataGenerator.getTransitList(transitDtoList);
    doReturn(transitList).when(transitRepository).saveAll(transitList);
    doReturn(transitList).when(transitConverter).toEntityList(transitDtoList);

    //When
    sut.updateAll(transitDtoList);

    //Then
    verify(transitRepository).saveAll(transitList);
  }

  @Test
  public void shouldReturnAllCreatedEntities() {
    //Given
    List<TransitDto> transitDtoList = TestTransitDataGenerator.generateRequestTransitDtoList(5);
    List<Transit> transitList = TestTransitDataGenerator.getTransitList(transitDtoList);
    doReturn(transitList).when(transitRepository).saveAll(transitList);
    doReturn(transitList).when(transitConverter).toEntityList(transitDtoList);
    doReturn(transitDtoList).when(transitConverter).toDtoList(transitList);

    //When
    List<TransitDto> cityDtoReturnList = sut.createAll(transitDtoList);

    //Then
    verify(transitRepository).saveAll(transitList);
    assertThat(cityDtoReturnList, not(is(empty())));
    transitDtoList.forEach(
        entity -> {
          assertThat(cityDtoReturnList, hasItem((hasProperty("uuid", is(entity.getUuid())))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("status", is(entity.getStatus()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_from", is(entity.getCity_from()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("city_to", is(entity.getCity_to()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("car", is(entity.getCar()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("user", is(entity.getUser()))));
          assertThat(cityDtoReturnList,
              hasItem(hasProperty("driver", is(entity.getDriver()))));
        }
    );
  }
}
