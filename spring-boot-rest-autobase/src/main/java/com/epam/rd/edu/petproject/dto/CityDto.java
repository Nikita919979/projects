package com.epam.rd.edu.petproject.dto;

import com.epam.rd.edu.petproject.model.City;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CityDto extends AbstractDto {

  private UUID uuid;
  private String name;

  public CityDto(City city) {
    this.uuid = city.getUuid();
    this.name = city.getName();
  }

  public CityDto(CityDto cityDto) {
    this.uuid = cityDto.uuid;
    this.name = cityDto.name;
  }

  @Override
  public CityDto clone() {
    return new CityDto(this);
  }
}
