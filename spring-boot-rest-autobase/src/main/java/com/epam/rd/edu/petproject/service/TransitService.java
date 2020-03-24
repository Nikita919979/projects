package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.TransitDto;
import java.util.List;

public interface TransitService {

  TransitDto create(TransitDto transitDto);

  void delete(Integer key);

  void update(TransitDto transitDto);

  TransitDto read(Integer key);

  List<TransitDto> getAll();

  List<TransitDto> createAll(List<TransitDto> transitDtoList);

  void updateAll(List<TransitDto> transitDtoList);
}
