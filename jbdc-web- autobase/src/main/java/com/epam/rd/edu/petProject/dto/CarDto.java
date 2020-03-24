package com.epam.rd.edu.petProject.dto;

import com.epam.rd.edu.petProject.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto extends AbstractDto {
    private Integer id;
    private Car.CarModel model;
    private String carNumber;
    private String carTechnicalPassport;
    private LocalDate releaseDate;
    private boolean fully_Functional;

    public CarDto(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.carNumber = car.getCarNumber();
        this.carTechnicalPassport = car.getCarTechnicalPassport();
        this.releaseDate = car.getReleaseDate();
        this.fully_Functional = car.isFully_Functional();
    }

    public CarDto(CarDto carDto) {
        this.id = carDto.id;
        this.model = carDto.model;
        this.carNumber = carDto.carNumber;
        this.carTechnicalPassport = carDto.carTechnicalPassport;
        this.releaseDate = carDto.releaseDate;
        this.fully_Functional = carDto.fully_Functional;
    }

    @Override
    public CarDto clone() {
        return new CarDto(this);
    }
}
