package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.dto.AbstractDto;

import java.util.List;

public interface Service<E extends AbstractDto, PK> {

    E create(E e);

    boolean delete(PK key);

    boolean update(E e);

    E read(PK key);

    List<E> getAll();

    List<E> create(List<E> objList);

    boolean update(List<E> objList);

}
