package com.epam.rd.edu.petproject.service.impl;

import com.epam.rd.edu.petproject.converter.ModelConverter;
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.exception.EntityNotFoundException;
import com.epam.rd.edu.petproject.model.City;
import com.epam.rd.edu.petproject.repository.CityRepository;
import com.epam.rd.edu.petproject.service.CityService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

  private CityRepository cityRepo;
  private ModelConverter<City, CityDto> cityConverter;

  @Override
  public CityDto create(CityDto cityDto) {
    City city = cityConverter.toEntity(cityDto);
    return cityConverter.toDto(cityRepo.save(city));
  }

  @Override
  public void delete(UUID key) {
    cityRepo.delete(cityRepo.findById(key).get());
  }

  @Override
  public void update(CityDto cityDto) {
    cityRepo.save(cityConverter.toEntity(cityDto));
  }

  @Override
  public CityDto read(UUID key) {
    try {
      return cityConverter.toDto(cityRepo.findById(key).get());
    } catch (NoSuchElementException ex) {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public List<CityDto> getAll() {
    return cityConverter.toDtoList(cityRepo.findAll());
  }

  @Override
  public List<CityDto> createAll(List<CityDto> cityDtoList) {
    List<City> cityList = cityConverter.toEntityList(cityDtoList);
    return cityConverter.toDtoList(cityRepo.saveAll(cityList));
  }

  @Override
  public void updateAll(List<CityDto> cityDtoList) {
    cityRepo.saveAll(cityConverter.toEntityList(cityDtoList));
  }
}
