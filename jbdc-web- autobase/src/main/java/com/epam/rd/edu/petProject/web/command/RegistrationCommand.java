package com.epam.rd.edu.petProject.web.command;

import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.service.impl.UserDtoService;
import com.epam.rd.edu.petProject.utils.HashUtil;

public class RegistrationCommand extends AbstractFrontCommand {

    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        UserDto userDto = createUser();
        userDto.setPassword(HashUtil.messageToHash(userDto.getPassword()));
        userDtoService.create(userDto);
        forward(pagePathManager.getPath("signInPage"));
    }

    private UserDto createUser() {
        return UserDto.builder()
                .name(request.getParameter("name"))
                .familyName(request.getParameter("f_name"))
                .login(request.getParameter("login"))
                .password(request.getParameter("pass"))
                .role(User.Role.DRIVER)
                .email(request.getParameter("email"))
                .build();
    }
}