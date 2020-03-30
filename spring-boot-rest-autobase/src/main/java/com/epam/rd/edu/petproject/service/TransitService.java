package com.epam.rd.edu.petproject.service;

import com.epam.rd.edu.petproject.dto.TransitDto;
import java.util.List;
import java.util.UUID;

public interface TransitService {

  TransitDto create(TransitDto transitDto);

  void delete(UUID key);

  void update(TransitDto transitDto);

  TransitDto read(UUID key);

  List<TransitDto> getAll();

  List<TransitDto> createAll(List<TransitDto> transitDtoList);

  void updateAll(List<TransitDto> transitDtoList);
}
