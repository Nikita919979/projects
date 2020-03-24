package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.model.AbstractEntity;
import com.epam.rd.edu.petProject.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericAbstractDao<E extends AbstractEntity> implements GenericDao<E, Integer> {
    private static final Logger log = LoggerFactory.getLogger(GenericAbstractDao.class);
    private ConnectionPool pool = ConnectionPool.getConnectionPool();
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    public abstract void setEntityParametersForCreate(E e, PreparedStatement preparedStatement) throws SQLException;

    public abstract void setEntityParametersForUpdate(E e, PreparedStatement preparedStatement) throws SQLException;

    public abstract E getEntity(ResultSet resultSet) throws SQLException;

    public abstract E getClone(E e);

    @Override
    public E create(E e, String sql) {
        E result = null;
        try (ConnectionPool.ConnectionDateWrapper connection = pool.getConnection()) {
            preparedStatement = connection.getConnection().prepareStatement(sql, preparedStatement.RETURN_GENERATED_KEYS);
            setEntityParametersForCreate(e, preparedStatement);
            preparedStatement.executeUpdate();

            try (ResultSet keysSet = preparedStatement.getGeneratedKeys()) {
                while (keysSet.next()) {
                    result = getClone(e);
                    result.setId(keysSet.getInt(1));
                }
            }
        } catch (SQLException ex) {
            String exStr = String.format("Сreation error of the entity:" + System.lineSeparator()
                    + "%s" + System.lineSeparator() + ex.getMessage(), e);
            throw new DaoException(exStr, ex);
        }

        return result;
    }

    @Override
    public boolean update(E e, String sql) {
        boolean result = true;

        try (ConnectionPool.ConnectionDateWrapper connection = pool.getConnection()) {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            setEntityParametersForUpdate(e, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            log.error(String.format("Update error of the entity:" + System.lineSeparator() + "%s" +
                    System.lineSeparator() + ex.getMessage(), e));
            result = false;
        }

        return result;
    }

    @Override
    public boolean delete(Integer key, String sql) {
        boolean result = true;

        try (ConnectionPool.ConnectionDateWrapper connection = pool.getConnection()) {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            log.error(String.format("Delete error of the entityID:" + System.lineSeparator() + "%s" +
                    System.lineSeparator() + ex.getMessage(), key));
            result = false;
        }

        return result;
    }

    @Override
    public E read(Integer key, String sql) {
        E entity;

        try (ConnectionPool.ConnectionDateWrapper connection = pool.getConnection()) {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                entity = getEntity(resultSet);
            }
        } catch (SQLException ex) {
            String exStr = String.format("Read error of the entityID:" + System.lineSeparator() + "%s" +
                    System.lineSeparator() + ex.getMessage(), key);
            throw new DaoException(exStr, ex);
        }

        return entity;
    }

    @Override
    public List<E> getAll(String sql) {
        List<E> entityList = new ArrayList<>();

        try (ConnectionPool.ConnectionDateWrapper connection = pool.getConnection()) {
            statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                E e = getEntity(resultSet);
                entityList.add(e);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return entityList;
    }

    @Override
    public List<E> create(List<E> objList, String sql) {
        List<E> resultList = new ArrayList<>();
        E result;
        ConnectionPool.ConnectionDateWrapper connection = null;

        try {
            connection = pool.getConnection();
            connection.getConnection().setAutoCommit(false);
            preparedStatement = connection.getConnection().prepareStatement(sql, preparedStatement.RETURN_GENERATED_KEYS);
            for (E e : objList) {
                setEntityParametersForCreate(e, preparedStatement);
                preparedStatement.executeUpdate();
                try (ResultSet keysSet = preparedStatement.getGeneratedKeys()) {
                    while (keysSet.next()) {
                        result = getClone(e);
                        result.setId(keysSet.getInt(1));
                        resultList.add(result);
                    }
                }
            }
            connection.getConnection().commit();
        } catch (SQLException ex) {
            connectionRollback(connection.getConnection());
            throw new DaoException(ex);
        } finally {
            connection.close();
        }

        return resultList;
    }

    @Override
    public boolean update(List<E> objList, String sql) {
        boolean result = true;
        ConnectionPool.ConnectionDateWrapper connection = null;

        try {
            connection = pool.getConnection();
            connection.getConnection().setAutoCommit(false);
            preparedStatement = connection.getConnection().prepareStatement(sql, preparedStatement.RETURN_GENERATED_KEYS);
            for (E e : objList) {
                setEntityParametersForUpdate(e, preparedStatement);
                preparedStatement.executeUpdate();
            }
            connection.getConnection().commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            connectionRollback(connection.getConnection());
            result = false;
        } finally {
            connection.close();
        }

        return result;
    }

    @Override
    public E readByOneStringField(String str, String sql) {
        E entity;

        try (ConnectionPool.ConnectionDateWrapper connection = pool.getConnection()) {
            preparedStatement = connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, str);
            preparedStatement.executeQuery();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                entity = getEntity(resultSet);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return entity;
    }

    private void connectionRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }
}
