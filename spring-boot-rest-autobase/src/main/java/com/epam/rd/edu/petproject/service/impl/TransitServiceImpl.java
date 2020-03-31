package com.epam.rd.edu.petproject.service.impl;

import com.epam.rd.edu.petproject.converter.ModelConverter;
import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.exception.EntityNotFoundException;
import com.epam.rd.edu.petproject.model.Transit;
import com.epam.rd.edu.petproject.repository.TransitRepository;
import com.epam.rd.edu.petproject.service.TransitService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransitServiceImpl implements TransitService {

  private TransitRepository transitRepo;
  private ModelConverter<Transit, TransitDto> transitConverter;

  @Override
  public TransitDto create(TransitDto transitDto) {
    Transit transit = transitConverter.toEntity(transitDto);
    return transitConverter.toDto(transitRepo.save(transit));
  }

  @Override
  public void delete(UUID key) {
    transitRepo.deleteById(key);
  }

  @Override
  public void update(TransitDto transitDto) {
    transitRepo.save(transitConverter.toEntity(transitDto));
  }

  @Override
  public TransitDto read(UUID key) {
    try {
      return transitConverter.toDto(transitRepo.findById(key).get());
    } catch (NoSuchElementException ex) {
      throw new EntityNotFoundException();
    }
  }

  @Override
  public List<TransitDto> getAll() {
    return transitConverter.toDtoList(transitRepo.findAll());
  }

  @Override
  public List<TransitDto> createAll(List<TransitDto> transitDtoList) {
    List<Transit> transitList = transitConverter.toEntityList(transitDtoList);
    return transitConverter
        .toDtoList(transitRepo.saveAll(transitList));
  }

  @Override
  public void updateAll(List<TransitDto> transitDtoList) {
    transitRepo.saveAll(transitConverter.toEntityList(transitDtoList));
  }
}
