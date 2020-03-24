package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.model.City;
import com.epam.rd.edu.petProject.model.Transit;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.TransitDto;
import com.epam.rd.edu.petProject.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class TransitConverter implements ModelConverter<Transit, TransitDto> {
    private final ModelConverter<Car, CarDto> carConverter = SimpleConverterFactory.getConverter().getCarConverter();
    private final ModelConverter<User, UserDto> userConverter = SimpleConverterFactory.getConverter().getUserConverter();
    private final ModelConverter<City, CityDto> cityConverter = SimpleConverterFactory.getConverter().getCityConverter();

    @Override
    public TransitDto toDto(Transit transit) {
        return TransitDto.builder().id(transit.getId())
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
                .id(transitDto.getId())
                .car(carConverter.toEntity(transitDto.getCar()))
                .city_from(cityConverter.toEntity(transitDto.getCity_from()))
                .city_to(cityConverter.toEntity(transitDto.getCity_to()))
                .status(transitDto.getStatus())
                .user(userConverter.toEntity(transitDto.getUser()))
                .driver(userConverter.toEntity(transitDto.getDriver()))
                .build();
    }

    @Override
    public List<TransitDto> toDtoList(List<Transit> transitList) {
        List<TransitDto> result = new ArrayList<>();
        for (Transit transit : transitList) {
            result.add(toDto(transit));
        }
        return result;
    }

    @Override
    public List<Transit> toEntityList(List<TransitDto> transitDtoList) {
        List<Transit> result = new ArrayList<>();
        for (TransitDto transitDto : transitDtoList) {
            result.add(toEntity(transitDto));
        }
        return result;
    }
}
