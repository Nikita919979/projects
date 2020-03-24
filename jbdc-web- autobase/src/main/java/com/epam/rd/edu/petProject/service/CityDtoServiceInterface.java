package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.dto.CityDto;

import java.util.List;

public interface CityDtoServiceInterface {

    CityDto create(CityDto cityDto);

    boolean delete(Integer key);

    boolean update(CityDto cityDto);

    CityDto read(Integer key);

    List<CityDto> getAll();

    List<CityDto> create(List<CityDto> cityDtoList);

    boolean update(List<CityDto> cityDtoList);
}
