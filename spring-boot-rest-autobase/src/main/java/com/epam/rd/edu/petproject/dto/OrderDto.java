package com.epam.rd.edu.petproject.dto;

import com.epam.rd.edu.petproject.model.Car;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDto extends AbstractDto {

  private UUID uuid;
  private CityDto city_from;
  private CityDto city_to;
  private Car.CarModel carModel;
  private UserDto user;

  public OrderDto(OrderDto transit) {
    this.uuid = transit.uuid;
    this.city_from = transit.city_from;
    this.city_to = transit.city_to;
    this.carModel = transit.carModel;
    this.user = transit.user;
  }

  @Override
  public OrderDto clone() {
    return new OrderDto(this);
  }

}
