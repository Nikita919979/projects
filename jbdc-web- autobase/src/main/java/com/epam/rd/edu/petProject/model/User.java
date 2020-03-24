package com.epam.rd.edu.petProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User extends AbstractEntity<Integer> {

    private Integer id;
    private String name;
    private String familyName;
    private String login;
    private String password;
    private Role role;
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
