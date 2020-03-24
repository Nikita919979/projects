package com.epam.rd.edu.petProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Order extends AbstractEntity<Integer> {
    private Integer id;
    private City city_from;
    private City city_to;
    private Car.CarModel carModel;
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
