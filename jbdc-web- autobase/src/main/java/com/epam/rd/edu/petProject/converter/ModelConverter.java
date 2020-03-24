package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.AbstractEntity;
import com.epam.rd.edu.petProject.dto.AbstractDto;

import java.util.List;

public interface ModelConverter<E extends AbstractEntity, D extends AbstractDto> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtoList(List<E> entityList);

    List<E> toEntityList(List<D> dtoList);

}
