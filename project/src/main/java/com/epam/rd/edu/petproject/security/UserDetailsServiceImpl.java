package com.epam.rd.edu.petproject.security;

import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername username =" + username);
    UserDto user = userService.getByLogin(username);
    UserBuilder builder = null;

    if (user != null) {
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.password(user.getPassword());
      builder.roles(user.getRole().name());
    } else {
      throw new UsernameNotFoundException("User not found");
    }

    return builder.build();
  }
}
