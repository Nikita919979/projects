package com.epam.rd.edu.petproject.converter;

import com.epam.rd.edu.petproject.dto.AbstractDto;
import com.epam.rd.edu.petproject.model.AbstractEntity;
import java.util.List;

public interface ModelConverter<E extends AbstractEntity, D extends AbstractDto> {

  D toDto(E entity);

  E toEntity(D dto);

  List<D> toDtoList(Iterable<E> entityList);

  List<E> toEntityList(Iterable<D> dtoList);

}
