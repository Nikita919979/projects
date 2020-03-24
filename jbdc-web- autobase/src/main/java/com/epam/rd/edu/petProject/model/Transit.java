package com.epam.rd.edu.petProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Transit extends AbstractEntity<Integer> {
    private Integer id;
    private Transit.Status status;
    private City city_from;
    private City city_to;
    private Car car;
    private User user;
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
