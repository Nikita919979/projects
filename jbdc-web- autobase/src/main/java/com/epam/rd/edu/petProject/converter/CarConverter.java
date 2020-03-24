package com.epam.rd.edu.petProject.converter;

import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.dto.CarDto;

import java.util.ArrayList;
import java.util.List;

public class CarConverter implements ModelConverter<Car, CarDto> {

    @Override
    public CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .carNumber(car.getCarNumber())
                .carTechnicalPassport(car.getCarTechnicalPassport())
                .model(car.getModel())
                .releaseDate(car.getReleaseDate())
                .fully_Functional(car.isFully_Functional())
                .build();
    }

    @Override
    public Car toEntity(CarDto car) {
        return Car.builder()
                .id(car.getId())
                .carNumber(car.getCarNumber())
                .carTechnicalPassport(car.getCarTechnicalPassport())
                .model(car.getModel())
                .releaseDate(car.getReleaseDate())
                .fully_Functional(car.isFully_Functional())
                .build();
    }

    @Override
    public List<CarDto> toDtoList(List<Car> carList) {
        List<CarDto> result = new ArrayList<>();
        for (Car car : carList) {
            result.add(toDto(car));
        }
        return result;
    }

    @Override
    public List<Car> toEntityList(List<CarDto> carList) {
        List<Car> result = new ArrayList<>();
        for (CarDto carDto : carList) {
            result.add(toEntity(carDto));
        }
        return result;
    }

}
