package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.CityDto;
import java.util.List;

public interface CityService {

  CityDto create(CityDto cityDto);

  void delete(Integer key);

  void update(CityDto cityDto);

  CityDto read(Integer key);

  List<CityDto> getAll();

  List<CityDto> createAll(List<CityDto> cityDtoList);

  void updateAll(List<CityDto> cityDtoList);
}
