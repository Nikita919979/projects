package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class NewUserCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        UserDto userDto = getUserDto();
        userDtoService.create(userDto);
        forward(pagePathManager.getPath("getAllUsersPage"));
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .role(User.Role.valueOf(request.getParameter("role")))
                .password(request.getParameter("pass"))
                .name(request.getParameter("name"))
                .login(request.getParameter("login"))
                .familyName(request.getParameter("f_name"))
                .email(request.getParameter("email"))
                .build();
    }
}

