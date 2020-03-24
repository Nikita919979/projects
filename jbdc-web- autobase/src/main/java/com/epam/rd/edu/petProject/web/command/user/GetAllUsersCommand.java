package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllUsersCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        List<UserDto> userDtoList = userDtoService.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("usersList", userDtoList);
        forward(pagePathManager.getPath("getAllUsersPage"));
    }
}