package com.epam.rd.edu.petproject.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity<UUID> {

  @Id
  @Type(type="uuid-char")
  @Column(name = "user_uuid", length = 36)
  private UUID uuid = UUID.randomUUID();
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
    this.uuid = user.uuid;
    this.name = user.name;
    this.familyName = user.familyName;
    this.login = user.login;
    this.password = user.password;
    this.role = user.role;
  }

  @Override
  public User clone() {
    return new User(this);
  }

  public enum Role implements Serializable {
    ADMIN, DISPATCHER, DRIVER
  }
}
