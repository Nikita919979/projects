package com.epam.rd.edu.petproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class City extends AbstractEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private Integer id;
  @Column(name = "name")
  private String name;

  public City(City city) {
    this.id = city.id;
    this.name = city.name;
  }

  @Override
  public City clone() {
    return new City(this);
  }

}
