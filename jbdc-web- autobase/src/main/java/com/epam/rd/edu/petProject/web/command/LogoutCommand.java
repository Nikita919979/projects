package com.epam.rd.edu.petProject.web.command;

import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.service.impl.UserDtoService;

import javax.servlet.http.HttpSession;

public class LogoutCommand extends AbstractFrontCommand {

    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        HttpSession session = request.getSession(true);
        session.setAttribute("login", null);
        session.setAttribute("name", null);
        session.setAttribute("f_name", null);
        session.setAttribute("role", null);

        forward(pagePathManager.getPath("startPage"));
    }
}