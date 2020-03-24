package com.epam.rd.edu.petProject.web.command;

import com.epam.rd.edu.petProject.exception.WrongPassException;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.service.impl.UserDtoService;
import com.epam.rd.edu.petProject.utils.HashUtil;

import javax.servlet.http.HttpSession;

public class LoginCommand extends AbstractFrontCommand {

    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        String userLogin = request.getParameter("login");
        String userPass = request.getParameter("pass");

        UserDto userDto = userDtoService.getByLogin(userLogin);
        if (!HashUtil.messageToHash(userPass).equals(userDto.getPassword())) {
            throw new WrongPassException();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("login", userDto.getLogin());
        session.setAttribute("role", userDto.getRole());
        forward(pagePathManager.getPath("startPage"));
    }
}