package com.epam.rd.edu.petproject.repository;

import com.epam.rd.edu.petproject.model.City;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, UUID> {

}
