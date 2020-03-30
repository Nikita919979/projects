package com.epam.rd.edu.petproject.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City extends AbstractEntity<UUID> {

  @Id
  @Column(name = "car_uuid", length = 36)
  private UUID uuid = UUID.randomUUID();
  @Column(name = "name")
  private String name;

  public City(City city) {
    this.uuid = city.uuid;
    this.name = city.name;
  }

  @Override
  public City clone() {
    return new City(this);
  }

}
