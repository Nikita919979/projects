package com.epam.rd.edu.petproject.dto;

import com.epam.rd.edu.petproject.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CityDto extends AbstractDto {

  private Integer id;
  private String name;

  public CityDto(City city) {
    this.id = city.getId();
    this.name = city.getName();
  }

  public CityDto(CityDto cityDto) {
    this.id = cityDto.id;
    this.name = cityDto.name;
  }

  @Override
  public CityDto clone() {
    return new CityDto(this);
  }
}
