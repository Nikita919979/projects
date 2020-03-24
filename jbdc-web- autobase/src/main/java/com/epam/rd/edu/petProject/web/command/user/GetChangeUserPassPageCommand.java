package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;

public class GetChangeUserPassPageCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        session.setAttribute("user_id", request.getParameter("user_id"));
        forward(pagePathManager.getPath("updateUserPassPage"));
    }
}

