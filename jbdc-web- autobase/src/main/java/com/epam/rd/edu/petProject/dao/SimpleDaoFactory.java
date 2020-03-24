package com.epam.rd.edu.petProject.dao;

public class SimpleDaoFactory {
    private static final SimpleDaoFactory factory = new SimpleDaoFactory();
    private final UserDao userDao = new UserDao();
    private final CarDao carDao = new CarDao();
    private final CityDao cityDao = new CityDao();
    private final TransitDao transitDao = new TransitDao();
    private final OrderDao orderDao = new OrderDao();

    public static SimpleDaoFactory getDaoFactory() {
        return factory;
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public TransitDao getTransitDao() {
        return transitDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}
