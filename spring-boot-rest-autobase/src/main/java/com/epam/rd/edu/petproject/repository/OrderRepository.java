package com.epam.rd.edu.petproject.repository;

import com.epam.rd.edu.petproject.model.Order;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, UUID> {

}
