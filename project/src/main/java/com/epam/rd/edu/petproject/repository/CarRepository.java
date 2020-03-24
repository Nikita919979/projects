package com.epam.rd.edu.petproject.repository;

import com.epam.rd.edu.petproject.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {

}
