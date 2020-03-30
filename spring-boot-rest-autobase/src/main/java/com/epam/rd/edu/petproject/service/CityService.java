package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.CityDto;
import java.util.List;
import java.util.UUID;

public interface CityService {

  CityDto create(CityDto cityDto);

  void delete(UUID key);

  void update(CityDto cityDto);

  CityDto read(UUID key);

  List<CityDto> getAll();

  List<CityDto> createAll(List<CityDto> cityDtoList);

  void updateAll(List<CityDto> cityDtoList);
}
