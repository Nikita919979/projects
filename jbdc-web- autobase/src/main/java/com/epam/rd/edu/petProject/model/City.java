package com.epam.rd.edu.petProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class City extends AbstractEntity<Integer> {

    private Integer id;
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
