package com.epam.rd.edu.petproject.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends AbstractEntity<UUID> {

  @Id
  @Type(type="uuid-char")
  @Column(name = "car_uuid", columnDefinition = "VARCHAR(36)")
  private UUID uuid = UUID.randomUUID();
  @Column(name = "model")
  @Enumerated(EnumType.STRING)
  private CarModel model;
  @Column(name = "number")
  private String carNumber;
  @Column(name = "technical_passport")
  private String carTechnicalPassport;
  @Column(name = "release_date")
  private LocalDate releaseDate;
  @Column(name = "fully_Functional")
  private boolean fully_Functional;

  public Car(Car car) {
    this.uuid = car.uuid;
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

  public enum CarModel implements Serializable {
    MAN, VAN, VOLVO, MERCEDES
  }

}
