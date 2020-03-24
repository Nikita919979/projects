package com.epam.rd.edu.petproject.utils.datagenerator;

import com.epam.rd.edu.petproject.converter.CityConverter;
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.model.City;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestCityDataGenerator {

  private final CityConverter cityConverter = new CityConverter();

  public List<CityDto> generateRequestCityDtoList(int count) {
    return IntStream.range(0, count)
        .mapToObj(TestCityDataGenerator::generateCityDto)
        .collect(Collectors.toList());
  }

  public CityDto generateCityDto(int counter) {
    return CityDto.builder()
        .id(counter)
        .name("testName" + counter)
        .build();
  }

  public City getCity(CityDto cityDto) {
    return cityConverter.toEntity(cityDto);
  }

  public List<City> getCityList(List<CityDto> cityDtoList) {
    return cityConverter.toEntityList(cityDtoList);
  }
}
