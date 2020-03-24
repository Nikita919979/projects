package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.*;
import com.epam.rd.edu.petProject.dto.*;

public class SimpleConverterFactory {
    private static final SimpleConverterFactory converter = new SimpleConverterFactory();

    public static SimpleConverterFactory getConverter() {
        return converter;
    }

    public ModelConverter<Car, CarDto> getCarConverter() {
        return new CarConverter();
    }

    public ModelConverter<User, UserDto> getUserConverter() {
        return new UserConverter();
    }

    public ModelConverter<Transit, TransitDto> getTransitConverter() {
        return new TransitConverter();
    }

    public ModelConverter<City, CityDto> getCityConverter() {
        return new CityConverter();
    }

    public ModelConverter<Order, OrderDto> getOrderConverter() {
        return new OrderConverter();
    }
}
