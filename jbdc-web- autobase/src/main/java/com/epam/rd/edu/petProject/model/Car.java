package com.epam.rd.edu.petProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Car extends AbstractEntity<Integer> {

    private Integer id;
    private CarModel model;
    private String carNumber;
    private String carTechnicalPassport;
    private LocalDate releaseDate;
    private boolean fully_Functional;

    public enum CarModel implements Serializable {
        MAN, VAN, VOLVO, MERCEDES
    }

    public Car(Car car) {
        this.id = car.id;
        this.model = car.model;
        this.carNumber = car.carNumber;
        this.carTechnicalPassport = car.carTechnicalPassport;
        this.releaseDate = car.releaseDate;
        this.fully_Functional = car.fully_Functional;
    }

    @Override
    public Car clone() {
        return new Car(this);
    }

}
