package com.epam.rd.edu.petproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "family_name")
  private String familyName;
  @Column(name = "username")
  private String login;
  @Column(name = "password")
  private String password;
  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(name = "email")
  private String email;

  public User(User user) {
    this.id = user.id;
    this.name = user.name;
    this.familyName = user.familyName;
    this.login = user.login;
    this.password = user.password;
    this.role = user.role;
  }

  public enum Role implements Serializable {
    ADMIN, DISPATCHER, DRIVER
  }

  @Override
  public User clone() {
    return new User(this);
  }

}
