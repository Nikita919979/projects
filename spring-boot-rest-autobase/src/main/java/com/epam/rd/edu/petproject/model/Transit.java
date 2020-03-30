package com.epam.rd.edu.petproject.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Transit extends AbstractEntity<UUID> {

  @Id
  @Column(name = "car_uuid", length = 36)
  private UUID uuid = UUID.randomUUID();
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
    this.uuid = transit.uuid;
    this.status = transit.status;
    this.city_from = transit.city_from;
    this.city_to = transit.city_to;
    this.car = transit.car;
    this.user = transit.user;
    this.driver = transit.driver;
  }

  @Override
  public Transit clone() {
    return new Transit(this);
  }

  public enum Status implements Serializable {
    OPENED, CLOSED, CANCELED, INPROGRESS
  }

}
