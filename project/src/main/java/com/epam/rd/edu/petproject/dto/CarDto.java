package com.epam.rd.edu.petproject.dto;

import com.epam.rd.edu.petproject.model.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto extends AbstractDto {

  private Integer id;
  private Car.CarModel model;
  private String carNumber;
  private String carTechnicalPassport;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
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
