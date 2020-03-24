package com.epam.rd.edu.petproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@Table(name = "orders")
public class Order extends AbstractEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "order_city_from")
  private City city_from;
  @ManyToOne
  @JoinColumn(name = "order_city_to")
  private City city_to;
  @Column(name = "car_model")
  @Enumerated(EnumType.STRING)
  private Car.CarModel carModel;
  @ManyToOne
  @JoinColumn(name = "order_user")
  private User user;

  public Order(Order transit) {
    this.id = transit.id;
    this.city_from = transit.city_from;
    this.city_to = transit.city_to;
    this.carModel = transit.carModel;
    this.user = transit.user;
  }

  @Override
  public Order clone() {
    return new Order(this);
  }

}
