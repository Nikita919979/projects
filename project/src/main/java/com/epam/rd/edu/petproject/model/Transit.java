package com.epam.rd.edu.petproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "transits")
public class Transit extends AbstractEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transit_id")
  private Integer id;
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Transit.Status status;
  @ManyToOne
  @JoinColumn(name = "transit_city_from")
  private City city_from;
  @ManyToOne
  @JoinColumn(name = "transit_city_to")
  private City city_to;
  @ManyToOne
  @JoinColumn(name = "transit_car")
  private Car car;
  @ManyToOne
  @JoinColumn(name = "transit_user")
  private User user;
  @ManyToOne
  @JoinColumn(name = "transit_driver")
  private User driver;

  public Transit(Transit transit) {
    this.id = transit.id;
    this.status = transit.status;
    this.city_from = transit.city_from;
    this.city_to = transit.city_to;
    this.car = transit.car;
    this.user = transit.user;
    this.driver = transit.driver;
  }

  public enum Status implements Serializable {
    OPENED, CLOSED, CANCELED, INPROGRESS
  }

  @Override
  public Transit clone() {
    return new Transit(this);
  }

}
