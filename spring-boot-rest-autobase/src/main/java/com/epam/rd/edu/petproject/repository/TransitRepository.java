package com.epam.rd.edu.petproject.repository;

import com.epam.rd.edu.petproject.model.Transit;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TransitRepository extends CrudRepository<Transit, UUID> {

}
