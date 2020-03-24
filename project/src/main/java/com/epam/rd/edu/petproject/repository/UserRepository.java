package com.epam.rd.edu.petproject.repository;

import com.epam.rd.edu.petproject.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findByLogin(String login);

  Optional<User> findByEmail(String email);
}
