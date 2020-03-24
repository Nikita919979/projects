package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.dto.TransitDto;

import java.util.List;

public interface TransitDtoServiceInterface {

    TransitDto create(TransitDto transitDto);

    boolean delete(Integer key);

    boolean update(TransitDto transitDto);

    TransitDto read(Integer key);

    List<TransitDto> getAll();

    List<TransitDto> create(List<TransitDto> transitDtoList);

    boolean update(List<TransitDto> transitDtoList);
}
