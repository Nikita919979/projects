package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetUpdateUserPageCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        List<User.Role> userList = Arrays.asList(User.Role.values());
        UserDto userDto = userDtoService.read(Integer.parseInt(request.getParameter("user_id")));
        session.setAttribute("userDto", userDto);
        session.setAttribute("roleList", userList);
        forward(pagePathManager.getPath("updateUserPage"));
    }
}

