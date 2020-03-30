package com.epam.rd.edu.petproject.converter;

import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.model.Car;
import com.epam.rd.edu.petproject.model.City;
import com.epam.rd.edu.petproject.model.Transit;
import com.epam.rd.edu.petproject.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransitConverter implements ModelConverter<Transit, TransitDto> {

  private ModelConverter<Car, CarDto> carConverter;
  private ModelConverter<User, UserDto> userConverter;
  private ModelConverter<City, CityDto> cityConverter;

  @Override
  public TransitDto toDto(Transit transit) {
    return TransitDto.builder().uuid(transit.getUuid())
        .car(carConverter.toDto(transit.getCar()))
        .city_from(cityConverter.toDto(transit.getCity_from()))
        .city_to(cityConverter.toDto(transit.getCity_to()))
        .status(transit.getStatus())
        .user(userConverter.toDto(transit.getUser()))
        .driver(userConverter.toDto(transit.getDriver()))
        .build();
  }

  @Override
  public Transit toEntity(TransitDto transitDto) {
    return Transit.builder()
        .uuid(transitDto.getUuid())
        .car(carConverter.toEntity(transitDto.getCar()))
        .city_from(cityConverter.toEntity(transitDto.getCity_from()))
        .city_to(cityConverter.toEntity(transitDto.getCity_to()))
        .status(transitDto.getStatus())
        .user(userConverter.toEntity(transitDto.getUser()))
        .driver(userConverter.toEntity(transitDto.getDriver()))
        .build();
  }

  @Override
  public List<TransitDto> toDtoList(Iterable<Transit> transitList) {
    List<TransitDto> result = new ArrayList<>();
    for (Transit transit : transitList) {
      result.add(toDto(transit));
    }
    return result;
  }

  @Override
  public List<Transit> toEntityList(Iterable<TransitDto> transitDtoList) {
    List<Transit> result = new ArrayList<>();
    for (TransitDto transitDto : transitDtoList) {
      result.add(toEntity(transitDto));
    }
    return result;
  }
}
