package com.epam.rd.edu.petProject.dto;

import com.epam.rd.edu.petProject.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto extends AbstractDto {
    private Integer id;
    private String name;
    private String familyName;
    private String login;
    private String password;
    private User.Role role;
    private String email;

    public UserDto(UserDto userDto) {
        this.id = userDto.id;
        this.name = userDto.name;
        this.familyName = userDto.familyName;
        this.login = userDto.login;
        this.password = userDto.password;
        this.role = userDto.role;
        this.email = userDto.email;
    }

    @Override
    public UserDto clone() {
        return new UserDto(this);
    }
}
