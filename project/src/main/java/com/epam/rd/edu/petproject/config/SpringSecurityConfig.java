package com.epam.rd.edu.petproject.config;

import com.epam.rd.edu.petproject.model.User;
import com.epam.rd.edu.petproject.security.UserDetailsServiceImpl;
import com.epam.rd.edu.petproject.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new HashUtil();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String admin = User.Role.ADMIN.name();
    String dispatcher = User.Role.DISPATCHER.name();
    String driver = User.Role.DRIVER.name();

    http
        .userDetailsService(userDetailsService)
        .httpBasic()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/users/**").hasRole(admin)
        .antMatchers("/transits/**").hasAnyRole(admin, dispatcher)
        .antMatchers("/cars/**").hasAnyRole(admin, dispatcher, driver)
        .antMatchers("/orders/**").hasAnyRole(admin, dispatcher, driver);
  }
}
