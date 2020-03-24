package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class DeleteUserCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        userDtoService.delete(Integer.parseInt(request.getParameter("user_id")));
        forward(pagePathManager.getPath("getAllUsersPage"));
    }
}

