package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class UpdateUserCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        userDtoService.update(getUserDto());
        forward(pagePathManager.getPath("getAllUsersPage"));
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .role(User.Role.valueOf(request.getParameter("role")))
                .password(request.getParameter("password"))
                .login(request.getParameter("login"))
                .email(request.getParameter("email"))
                .familyName(request.getParameter("familyName"))
                .name(request.getParameter("name"))
                .id(Integer.parseInt(request.getParameter("user_id")))
                .build();
    }
}

