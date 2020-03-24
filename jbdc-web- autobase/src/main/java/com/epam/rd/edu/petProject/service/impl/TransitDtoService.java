package com.epam.rd.edu.petProject.service.impl;

import com.epam.rd.edu.petProject.converter.ModelConverter;
import com.epam.rd.edu.petProject.converter.SimpleConverterFactory;
import com.epam.rd.edu.petProject.dao.Query;
import com.epam.rd.edu.petProject.dao.SimpleDaoFactory;
import com.epam.rd.edu.petProject.dao.TransitDao;
import com.epam.rd.edu.petProject.model.Transit;
import com.epam.rd.edu.petProject.service.Service;
import com.epam.rd.edu.petProject.dto.TransitDto;
import com.epam.rd.edu.petProject.service.TransitDtoServiceInterface;

import java.util.List;

public class TransitDtoService implements TransitDtoServiceInterface {

    private TransitDao transitDao = SimpleDaoFactory.getDaoFactory().getTransitDao();
    private ModelConverter<Transit, TransitDto> transitConverter = SimpleConverterFactory.getConverter().getTransitConverter();

    @Override
    public TransitDto create(TransitDto transitDto) {
        Transit transit = transitConverter.toEntity(transitDto);
        return transitConverter.toDto(transitDao.create(transit, Query.TRANSIT_CREATE.getQuery()));
    }

    @Override
    public boolean delete(Integer key) {
        return transitDao.delete(key, Query.TRANSIT_DELETE.getQuery());
    }

    @Override
    public boolean update(TransitDto transitDto) {
        return transitDao.update(transitConverter.toEntity(transitDto), Query.TRANSIT_UPDATE.getQuery());
    }

    @Override
    public TransitDto read(Integer key) {
        return transitConverter.toDto(transitDao.read(key, Query.TRANSIT_READ.getQuery()));
    }

    @Override
    public List<TransitDto> getAll() {
        return transitConverter.toDtoList(transitDao.getAll(Query.TRANSIT_GET_ALL.getQuery()));
    }

    @Override
    public List<TransitDto> create(List<TransitDto> transitDtoList) {
        List<Transit> transitList = transitConverter.toEntityList(transitDtoList);
        return transitConverter.toDtoList(transitDao.create(transitList, Query.TRANSIT_CREATE_LIST.getQuery()));
    }

    @Override
    public boolean update(List<TransitDto> transitDtoList) {
        return transitDao.update(transitConverter.toEntityList(transitDtoList), Query.TRANSIT_UPDATE_LIST.getQuery());
    }
}
