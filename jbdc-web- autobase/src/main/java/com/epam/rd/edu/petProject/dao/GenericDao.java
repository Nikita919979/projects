package com.epam.rd.edu.petProject.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<E, PK> {

    E create(E e, String sql) throws IOException, SQLException;

    boolean delete(PK key, String sql);

    boolean update(E e, String sql);

    E read(PK key, String sql);

    List<E> getAll(String sql);

    List<E> create(List<E> objList, String sql);

    boolean update(List<E> objList, String sql);

    E readByOneStringField(String str, String sql);
}
