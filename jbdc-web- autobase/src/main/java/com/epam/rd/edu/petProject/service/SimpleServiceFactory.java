package com.epam.rd.edu.petProject.service;

import com.epam.rd.edu.petProject.service.impl.*;

public class SimpleServiceFactory {
    private static final SimpleServiceFactory factory = new SimpleServiceFactory();

    public static SimpleServiceFactory getFactory() {
        return factory;
    }

    public CarDtoServiceInterface getCarService() {
        return new CarDtoService();
    }

    public CityDtoServiceInterface getCityService() {
        return new CityDtoService();
    }

    public TransitDtoServiceInterface getTransitService() {
        return new TransitDtoService();
    }

    public UserDtoServiceInterface getUserService() {
        return new UserDtoService();
    }

    public OrderDtoServiceInterface getOrderService() {
        return new OrderDtoService();
    }
}
