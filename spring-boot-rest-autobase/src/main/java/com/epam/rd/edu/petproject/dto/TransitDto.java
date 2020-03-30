package com.epam.rd.edu.petproject.dto;

import com.epam.rd.edu.petproject.model.Transit;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TransitDto extends AbstractDto {

  private UUID uuid;
  private Transit.Status status;
  private CityDto city_from;
  private CityDto city_to;
  private CarDto car;
  private UserDto user;
  private UserDto driver;

  public TransitDto(TransitDto transitDto) {
    this.uuid = transitDto.uuid;
    this.status = transitDto.status;
    this.city_from = transitDto.city_from;
    this.city_to = transitDto.city_to;
    this.car = transitDto.car;
    this.user = transitDto.user;
    this.driver = transitDto.driver;
  }

  @Override
  public TransitDto clone() {
    return new TransitDto(this);
  }
}
