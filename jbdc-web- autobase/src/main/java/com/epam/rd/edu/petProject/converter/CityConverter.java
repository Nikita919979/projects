package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.City;
import com.epam.rd.edu.petProject.dto.CityDto;

import java.util.ArrayList;
import java.util.List;

public class CityConverter implements ModelConverter<City, CityDto> {

    @Override
    public CityDto toDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

    @Override
    public City toEntity(CityDto cityDto) {
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .build();
    }

    @Override
    public List<CityDto> toDtoList(List<City> cityList) {
        List<CityDto> result = new ArrayList<>();
        for (City city : cityList) {
            result.add(toDto(city));
        }
        return result;
    }

    @Override
    public List<City> toEntityList(List<CityDto> cityDtoList) {
        List<City> result = new ArrayList<>();
        for (CityDto cityDto : cityDtoList) {
            result.add(toEntity(cityDto));
        }
        return result;
    }
}
