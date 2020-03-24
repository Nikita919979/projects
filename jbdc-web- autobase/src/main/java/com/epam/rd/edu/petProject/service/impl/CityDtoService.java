package com.epam.rd.edu.petProject.service.impl;

import com.epam.rd.edu.petProject.converter.ModelConverter;
import com.epam.rd.edu.petProject.converter.SimpleConverterFactory;
import com.epam.rd.edu.petProject.dao.CityDao;
import com.epam.rd.edu.petProject.dao.Query;
import com.epam.rd.edu.petProject.dao.SimpleDaoFactory;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.model.City;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;

import java.util.List;

public class CityDtoService implements CityDtoServiceInterface {

    private CityDao cityDao = SimpleDaoFactory.getDaoFactory().getCityDao();
    private ModelConverter<City, CityDto> cityConverter = SimpleConverterFactory.getConverter().getCityConverter();

    @Override
    public CityDto create(CityDto cityDto) {
        City city = cityConverter.toEntity(cityDto);
        return cityConverter.toDto(cityDao.create(city, Query.CITY_CREATE.getQuery()));
    }

    @Override
    public boolean delete(Integer key) {
        return cityDao.delete(key, Query.CITY_DELETE.getQuery());
    }

    @Override
    public boolean update(CityDto cityDto) {
        return cityDao.update(cityConverter.toEntity(cityDto), Query.CITY_UPDATE.getQuery());
    }

    @Override
    public CityDto read(Integer key) {
        return cityConverter.toDto(cityDao.read(key, Query.CITY_READ.getQuery()));
    }

    @Override
    public List<CityDto> getAll() {
        return cityConverter.toDtoList(cityDao.getAll(Query.CITY_GET_ALL.getQuery()));
    }

    @Override
    public List<CityDto> create(List<CityDto> cityDtoList) {
        List<City> cityList = cityConverter.toEntityList(cityDtoList);
        return cityConverter.toDtoList(cityDao.create(cityList, Query.CITY_CREATE_LIST.getQuery()));
    }

    @Override
    public boolean update(List<CityDto> cityDtoList) {
        return cityDao.update(cityConverter.toEntityList(cityDtoList), Query.CITY_UPDATE_LIST.getQuery());
    }
}
